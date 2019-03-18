package com.zjh.website.controller.admin;


import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.pojo.ApplyMessageNumber;
import com.zjh.website.service.admin.AdminService;
import com.zjh.website.service.admin.ApplyMessageService;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;
import com.zjh.website.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *<p>Title: AdminApplyMessageController.java</p>
 *<p>Description: 管理员端报名信息管理控制器</p>
 *<p>CreateDate: 2019年3月4日</p>
 * @author zjh
 * @version v1.0
 */

@Controller
@RequestMapping("/aode/admin")
public class AdminApplyMessageController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ApplyMessageService applyMessageService;

    private Logger logger = LoggerFactory.getLogger(AdminApplyMessageController.class);

    /**
     * 查询部门人数
     * @param department 部门名称数组
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findDepartmentNumber")
    public HttpMessageAndObject<List<ApplyMessageNumber>> findDepartmentNumber(@RequestParam(value = "department[]") String[] department){
        logger.info(department.toString());
        //实例化消息类
        HttpMessageAndObject<List<ApplyMessageNumber>> httpMessage = new HttpMessageAndObject<>("200");
        //准备接受查询结果
        List<ApplyMessageNumber> list;
        try {
           list = adminService.findDepartmentNumber(department);
            httpMessage.setObj(list);
        } catch (Exception e) {
            e.printStackTrace();
            httpMessage.setHttpCode("500");
            httpMessage.setMessage("数据获取失败");
        }
        return httpMessage;
    }

    /**
     * 根据性别查找人数
     * @param sex 性别数组
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findSexNumber")
    public HttpMessageAndObject<List<ApplyMessageNumber>> findSexNumber(@RequestParam(value = "sex[]")int[] sex) {
        //实例化消息类
        HttpMessageAndObject<List<ApplyMessageNumber>> httpMessage = new HttpMessageAndObject<>("200");
        //准备接受查询结果
        List<ApplyMessageNumber> list;
        try {
            list = adminService.findSexNumber(sex);
            httpMessage.setObj(list);
        }catch (Exception e) {
            e.printStackTrace();
            httpMessage.setHttpCode("500");
            httpMessage.setMessage("数据获取失败");
        }
        return httpMessage;
    }

    /**
     * 查找某部门的性别人数
     * @param department 部门名称数组
     * @param sex 性别数组
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findDepartmentSex")
    public HttpMessageAndObject<List<ApplyMessageNumber>> findDepartmentSex(String department,@RequestParam(value = "sex[]")int[] sex) {
        //实例化消息类
        HttpMessageAndObject<List<ApplyMessageNumber>> httpMessage = new HttpMessageAndObject<>("200");
        //准备接受查询结果
        List<ApplyMessageNumber> list;
        try {
            list = adminService.findDepartmentSexNumber(department,sex);
            httpMessage.setObj(list);
        }catch (Exception e) {
            e.printStackTrace();
            httpMessage.setHttpCode("500");
            httpMessage.setMessage("数据获取失败");
        }
        return httpMessage;
    }

    /**
     * 条件查询
     * @param applyMessage 报名信息
     * @param key 查询信息
     * @param pageNumber 页数
     * @param pageSize 内容数
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findApplyMessage")
    public HttpMessageAndObject<PageUtil<ApplyMessage>> findApplyMessage(
            ApplyMessage applyMessage,
            @RequestParam(required  = false) String key,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        logger.info("===============================>"+applyMessage.toString());
        return applyMessageService.findApplyMessage(applyMessage,key,pageNumber,pageSize);
    }
}
