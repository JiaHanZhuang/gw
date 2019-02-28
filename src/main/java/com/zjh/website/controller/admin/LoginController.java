package com.zjh.website.controller.admin;


import com.zjh.website.pojo.Admin;
import com.zjh.website.service.admin.AdminService;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *<p>Title: LoginController.java</p>
 *<p>Description: 后台登录控制器</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */

@Controller
@RequestMapping("/aode/admin")
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 注册新管理员
     * @param admin 注册信息
     * @param code 验证码
     * @return 注册结果信息
     */
    @ResponseBody
    @RequestMapping(value = "/loginUp",method = RequestMethod.POST)
    public HttpMessage loginIn(Admin admin,String code) {
        HttpMessage httpMessage = adminService.saveAdmin(admin,code);
        return httpMessage;
    }

    /**
     * 后台管理端登录
     * @param admin 登录密码与账户
     * @param session 存储session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    public HttpMessage loginUp(Admin admin, HttpSession session) {
        //查看session
        Object obj = session.getAttribute("admin");
        HttpMessageAndObject<Admin> httpMessae;
        //判断session中是否存储了Admin信息
        if(obj == null) {
            httpMessae = adminService.loginUp(admin);
            session.setAttribute("admin",httpMessae.getObj());
        } else {
            httpMessae = new HttpMessageAndObject<Admin>("200");
            httpMessae.setObj((Admin)obj);
        }
        return httpMessae;
    }

    /**
     * @param session 管理员存储session
     * @return 用户信息
     */
    @ResponseBody
    @RequestMapping(value = "selectMessage",method = RequestMethod.GET)
    public HttpMessageAndObject<Admin> selectMessage(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        HttpMessageAndObject<Admin> httpMessage = new HttpMessageAndObject<Admin>("200");
        httpMessage.setObj(admin);
        return httpMessage;
    }

}
