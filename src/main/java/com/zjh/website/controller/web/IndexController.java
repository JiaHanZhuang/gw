package com.zjh.website.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *<p>Title: IndexController.java</p>
 *<p>Description: 前端官网跳转控制器</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */

@Controller
@RequestMapping("/aode")
public class IndexController {


    @RequestMapping("about")
    public String aboutPage(){
        return "/web/about";
    }

    @RequestMapping("contact")
    public String contactPage(){
        return "/web/contact";
    }

    @RequestMapping("gallery")
    public String galleryPage(){
        return "/web/gallery";
    }

    @RequestMapping("index")
    public String index(){return "/web/index";}

}
