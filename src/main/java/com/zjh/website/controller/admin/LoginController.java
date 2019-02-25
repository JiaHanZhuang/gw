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

    @ResponseBody
    @RequestMapping(value = "/loginUp",method = RequestMethod.POST)
    public HttpMessage loginIn(Admin admin, HttpSession session) {
        HttpMessageAndObject<Admin> httpMessage = adminService.saveAdmin(admin);
        if(httpMessage.getObj()!=null) {
            session.setAttribute("admin",httpMessage.getObj());
        }
        return httpMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    public HttpMessage loginUp(Admin admin, HttpSession session) {
        HttpMessageAndObject<Admin> httpMessae = adminService.loginUp(admin);
        session.setAttribute("admin",httpMessae.getObj());
        return httpMessae;
    }

    @RequestMapping("/main")
    public String main(){
        return "/admin/main";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/aode/admin/login";
    }

}
