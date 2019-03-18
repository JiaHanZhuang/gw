package com.zjh.website.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *<p>Title: AdminBasicController.java</p>
 *<p>Description: 后台登录基本控制器</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */
@Controller
@RequestMapping("/aode/admin")
public class AdminBasicController {

    /**
     * 跳转到登录界面
     * @return 登录页面路径
     */
    @RequestMapping("login")
    public String loginPage(){
        return "/admin/login";
    }

    @RequestMapping("main")
    public String main(){
        return "/admin/main";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/aode/admin/login";
    }

}
