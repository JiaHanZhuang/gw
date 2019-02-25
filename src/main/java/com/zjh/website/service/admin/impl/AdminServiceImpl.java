package com.zjh.website.service.admin.impl;


import com.zjh.website.dao.AdminDao;
import com.zjh.website.pojo.Admin;
import com.zjh.website.service.admin.AdminService;
import com.zjh.website.utils.HttpMessageAndObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zjh
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    private final Integer PASSWORDMINNUMBER = 5;
    private final Integer PASSWORDMAXNUMBER = 16;


    @Override
    public HttpMessageAndObject<Admin> saveAdmin(Admin admin) {
        HttpMessageAndObject<Admin> httpMessage = new HttpMessageAndObject<Admin>("200");
        //后台校验用户名
        if(!this.checkUsername(admin.getUsername())) {
            httpMessage.setMessage("该用户昵称已被使用");
            return httpMessage;
        }
        //后台检验密码长度
        if(!this.checkPasswordLength(admin.getPassword())){
            httpMessage.setMessage("密码长度不得小于5字符，不得大于16字符");
            return httpMessage;
        }
        //MD5加密密码
//        admin.setPassword(MD5Util.getMd5(admin.getPassword()));
        //注册用户
        admin = adminDao.save(admin);
        httpMessage.setObj(admin);
        return httpMessage;
    }

    @Override
    public HttpMessageAndObject loginUp(Admin admin) {
        HttpMessageAndObject<Admin> httpMessage = new HttpMessageAndObject<Admin>("200");
        //定义一个临时对象
        Admin temp = null;
        //搜索用户名是否存在
        temp = adminDao.findByUsername(admin.getUsername());
        if(temp!=null) {
            if(temp.getId()== null) {
                httpMessage.setMessage("该用户不存在");
            } else {
                //校验用户密码是否正确
//                admin.setPassword(MD5Util.getMd5(admin.getPassword()));
                temp = adminDao.findByUsernameAndPassword(admin.getUsername(),admin.getPassword());
                if(temp == null) {
                    httpMessage.setMessage("用户密码错误,请重新输入");
                } else {
                    httpMessage.setObj(temp);
                }
            }
        } else {
            httpMessage.setMessage("该用户名不存在");
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
