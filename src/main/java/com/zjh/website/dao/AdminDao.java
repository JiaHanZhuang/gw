package com.zjh.website.dao;


import com.zjh.website.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *<p>Title: AdminDao.java</p>
 *<p>Description: 管理员Dao</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */

public interface AdminDao extends JpaRepository<Admin,Integer> {
    /**
     * 根据用户名进行管理员用户搜索
     * @param username 用户名
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 用于用户登录
     * @param username 用户名
     * @param password  密码
     * @return
     */
    Admin findByUsernameAndPassword(String username, String password);
}
