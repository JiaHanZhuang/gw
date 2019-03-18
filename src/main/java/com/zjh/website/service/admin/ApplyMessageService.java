package com.zjh.website.service.admin;


import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.utils.HttpMessageAndObject;
import com.zjh.website.utils.PageUtil;


/**
 * @author 庄家瀚
 */

public interface ApplyMessageService {

    /**
     * 分页条件查询
     * @param applyMessage 报名信息条件
     * @param key 查询信息
     * @param pageNumber 页数
     * @param pageSize  一页多少条数据
     * @return 分页数据
     */
    HttpMessageAndObject<PageUtil<ApplyMessage>> findApplyMessage(ApplyMessage applyMessage, String key, Integer pageNumber, Integer pageSize);

}
