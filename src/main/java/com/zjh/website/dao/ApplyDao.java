package com.zjh.website.dao;

import com.zjh.website.pojo.ApplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *<p>Title: ApplyDao.java</p>
 *<p>Description: 报名信息dao</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */


public interface ApplyDao extends JpaRepository<ApplyMessage,Integer> {

    /**
     * 根据邮箱和手机号码进行查询
     * @param email 邮箱
     * @param phone 手机号码
     * @return
     */
    ApplyMessage findApplyMessageByEmailOrPhone(String email, String phone);

}
