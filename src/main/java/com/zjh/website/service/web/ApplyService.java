package com.zjh.website.service.web;


import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.utils.HttpMessage;

import java.util.Map;

/**
 * @author zjh
 * @date 2018.10.09
 */

public interface ApplyService {

    /**
     * 获取令牌token
     * @return token
     */
    Map<String,String> getToken();

    /**
     * 添加报名信息
     * @return HttpMessage
     */
    HttpMessage apply(ApplyMessage applyMessage);

}
