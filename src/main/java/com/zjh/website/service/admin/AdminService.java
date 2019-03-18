package com.zjh.website.service.admin;


import com.zjh.website.pojo.Admin;
import com.zjh.website.pojo.ApplyMessageNumber;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;

import java.util.List;

/**
 * @author zjh
 */

public interface AdminService {


    /**
     * 进行管理员用户的注册添加
     * @param admin 注册信息
     * @param code 验证码
     * @return 结果
     */
    HttpMessage saveAdmin(Admin admin,String code);

    /**
     * 进行管理员登录
     * @param admin 登陆信息
     * @return 结果
     */
    HttpMessageAndObject loginUp(Admin admin);

    /**
     * 查询部门人数
     * @param department 部门名称数组
     * @return 部门人数集合
     */
    List<ApplyMessageNumber> findDepartmentNumber(String... department);

    /**
     * 查询性别人数
     * @param sex 性别数组【0为女性，1为男性】
     * @return 性别人数集合
     */
    List<ApplyMessageNumber> findSexNumber(int... sex);

    /**
     * 查询某部门的性别人数
     * @param department 部门名称
     * @param sex 性别数组
     * @return 人数集合
     */
    List<ApplyMessageNumber> findDepartmentSexNumber(String department,int... sex);

}
