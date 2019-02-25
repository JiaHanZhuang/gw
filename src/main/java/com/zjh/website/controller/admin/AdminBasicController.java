package com.zjh.website.controller.admin;

import com.zjh.website.pojo.Admin;
import com.zjh.website.utils.HttpMessageAndObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    /**
//     * @param session
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "selectMessage",method = RequestMethod.GET)
//    public HttpMessageAndObject<Admin> selectMessage(HttpSession session){
//        Admin admin = (Admin) session.getAttribute("admin");
//        HttpMessageAndObject<Admin> httpMessage = new HttpMessageAndObject<Admin>("200");
//        httpMessage.setObj(admin);
//        return httpMessage;
//    }

}
