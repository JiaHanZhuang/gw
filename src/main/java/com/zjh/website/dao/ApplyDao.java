package com.zjh.website.dao;

import com.zjh.website.pojo.ApplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *<p>Title: ApplyDao.java</p>
 *<p>Description: 报名信息dao</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */


public interface ApplyDao extends JpaRepository<ApplyMessage,Integer>, JpaSpecificationExecutor<ApplyMessage> {

    /**
     * 根据邮箱和手机号码进行查询
     * @param email 邮箱
     * @param phone 手机号码
     * @return
     */
    ApplyMessage findApplyMessageByEmailOrPhone(String email, String phone);


    /**
     * 根据姓名，手机号码，部门查询报名结果
     * @param name 姓名
     * @param phone 手机号码
     * @param department 部门
     * @return 0代表不通过，1代表通过
     */
    @Query(value = "SELECT a.mark from ApplyMessage  a where a.name = ?1 and a.phone = ?2 and a.department = ?3")
    Integer findApplyResult(String name, String phone, String department);

    /**
     * 查找该部门报名人数
     * @param department 部门名称
     * @return 部门人数
     */
    @Query(value = "SELECT count(a.id) from ApplyMessage a where a.department = ?1")
    Long findDepartmentNumber(String department);

    /**
     * 根据性别计算人数
     * @param sex 性别，0为女性，1为男性
     * @return 人数
     */
    @Query(value = "select count(a.id) from  ApplyMessage a where a.sex = ?1")
    Long findSexNumber(Integer sex);

    /**
     * 查找某部门的性别人数
     * @param department 部门名称
     * @param sex 性别，0为女性，1为男性
     * @return 人数
     */
    @Query(value = "select count (a.id) from ApplyMessage a where a.department = ?1 and a.sex = ?2")
    Long findDepartmentSexNumber(String department,Integer sex);

}
