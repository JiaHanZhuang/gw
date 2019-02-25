package com.zjh.website.controller.web;


import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.service.web.ApplyService;
import com.zjh.website.utils.HttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 *<p>Title: ApplyController.java</p>
 *<p>Description: 报名信息提交控制器</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */

@Controller
@RequestMapping("/aode")
public class ApplyController {

    private Logger logger = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private ApplyService applyService;

    /**
     * 报名提交
     * @param applyMessage 报名信息
     * @param token 附带的token标示
     * @param request 用于获取session
     * @return 报名结果
     */
    @ResponseBody
    @RequestMapping("apply")
    public HttpMessage apply(ApplyMessage applyMessage, String token, HttpServletRequest request){
        logger.info(applyMessage.toString());
        HttpMessage message;
        //判断是否重复提交表单
        boolean flag = this.isRepeatSubmit(request,token);
        //未重复提交表单
        if(!flag) {
            //移除token
            request.getSession().removeAttribute("token");
            //储存报名信息
            message = applyService.apply(applyMessage);
        } else {
            //201标识代表用户重复提交表单
            return new HttpMessage("201");
        }
        return message;
    }

    /**
     * 获取标示token
     * @param session 用于存储token
     * @return
     */
    @RequestMapping("getToken")
    @ResponseBody
    public Map<String,String> getToken(HttpSession session){
        Map<String,String> map = applyService.getToken();
        session.setAttribute("token",map.get("token"));
        return map;
    }

    /**
     * 判断token是否存储或过期
     * @param request 用于获取session
     * @param token 唯一标示
     * @return
     */
    private boolean isRepeatSubmit(HttpServletRequest request, String token){
        String client_token = token;
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        return false;
    }

}
