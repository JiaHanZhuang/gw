package com.zjh.website.service.admin;


import com.zjh.website.pojo.Admin;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;

/**
 * @author zjh
 */

public interface AdminService {


    /**
     * 进行管理员用户的注册添加
     * @param admin
     * @return
     */
    HttpMessage saveAdmin(Admin admin,String code);

    /**
     * 进行管理员登录
     * @param admin
     * @return
     */
    HttpMessageAndObject loginUp(Admin admin);

}
