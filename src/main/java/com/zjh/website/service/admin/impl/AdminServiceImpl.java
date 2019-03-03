package com.zjh.website.service.admin.impl;


import com.zjh.website.dao.AdminDao;
import com.zjh.website.pojo.Admin;
import com.zjh.website.service.admin.AdminService;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;
import com.zjh.website.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;



/**
 * @author zjh
 */

@PropertySource("classpath:config/authCode.properties")
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${Admin.authCode}")
    private String autoCode;
    @Value("${Admin.username}")
    private String username;
    @Value("${Admin.password}")
    private String password;
    private final Integer PASSWORDMINNUMBER = 5;
    private final Integer PASSWORDMAXNUMBER = 16;


    @Override
    public HttpMessage saveAdmin(Admin admin,String code) {
        HttpMessage httpMessage = new HttpMessage("200");
        if(username.equals(admin.getUsername())) {
            httpMessage.setMessage("admin为最高级管理员用户，无法注册");
            return httpMessage;
        } else {
            if(autoCode.equals(code)) {
                //后台校验用户名
                if(!this.checkUsername(admin.getUsername())) {
                    httpMessage.setMessage("该用户账号已被使用");
                    return httpMessage;
                }
                //后台检验密码长度
                if(!this.checkPasswordLength(admin.getPassword())){
                    httpMessage.setMessage("密码长度不得小于5字符，不得大于16字符");
                    return httpMessage;
                }
                //MD5加密密码
                admin.setPassword(MD5Util.getMd5(admin.getPassword()));
                //注册用户
                try{
                    adminDao.save(admin);
                    httpMessage.setMessage("注册成功");
                } catch (Exception e) {
                    logger.error("出现了一个存储错误:");
                    e.printStackTrace();
                    httpMessage.setMessage("系统出现了异常，注册失败");
                }
            } else {
                httpMessage.setMessage("验证码不符合，无法注册新管理员");
            }
            return httpMessage;
        }
    }

    @Override
    public HttpMessageAndObject loginUp(Admin admin) {
        HttpMessageAndObject<Admin> httpMessage = new HttpMessageAndObject<Admin>("200");
        //登录的为默认账户
        if(username.equals(admin.getUsername())&&password.equals(admin.getPassword())) {
            httpMessage.setObj(admin);
        } else {
            //登录的不为默认账户
            //定义一个临时对象
            Admin temp = null;
            //搜索用户名是否存在
            temp = adminDao.findByUsername(admin.getUsername());
            if(temp!=null) {
                if(temp.getId()== null) {
                    httpMessage.setMessage("该用户不存在");
                } else {
                    //校验用户密码是否正确
                    String passwrod = MD5Util.getMd5(admin.getPassword());
                    temp = adminDao.findByUsernameAndPassword(admin.getUsername(),passwrod);
                    if(temp == null) {
                        httpMessage.setMessage("用户密码错误,请重新输入");
                    } else {
                        httpMessage.setObj(temp);
                    }
                }
            } else {
                httpMessage.setMessage("该用户名不存在");
            }
        }
        return httpMessage;
    }



    private Boolean checkUsername(String username) {
        //判断用户名是否已被注册过
        Admin admin = adminDao.findByUsername(username);
        if(admin!=null) {
            if(admin.getId()!=null) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private Boolean checkPasswordLength(String password){
        //判断密码长度
        if(password.length()<PASSWORDMINNUMBER||password.length()>PASSWORDMAXNUMBER) {
            return false;
        } else {
            return true;
        }
    }

}
