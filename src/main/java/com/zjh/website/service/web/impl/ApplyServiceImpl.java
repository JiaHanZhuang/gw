package com.zjh.website.service.web.impl;

import com.zjh.website.dao.ApplyDao;
import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.service.web.ApplyService;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.TokenProccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {

    private Logger logger = LoggerFactory.getLogger(ApplyServiceImpl.class);

    @Autowired
    private ApplyDao applyDao;

    @Override
    public Map<String, String> getToken() {
        //获取token工厂
        TokenProccessor tokenProccessor = TokenProccessor.getInstance();
        //生成token
        String token = tokenProccessor.makeToken();
        //存储token
        Map<String, String> map = new HashMap<String, String>();
        map.put("token",token);
        logger.info("发出token："+token);
        return map;
    }

    @Override
    public HttpMessage apply(ApplyMessage applyMessage) {
        HttpMessage message = new HttpMessage("200");
        boolean flag ;
        //查询是否已经报名
        try {
            ApplyMessage temp = applyDao.findApplyMessageByEmailOrPhone(applyMessage.getEmail(),applyMessage.getPhone());
            //防止空指针
            if(temp!= null) {
                //检测是否已报名
                if(temp.getId() != null) {
                    message.setMessage("已报名，请勿重复报名");
                } else {
                    applyDao.save(applyMessage);
                    logger.info("存储成功");
                    message.setMessage("报名成功");
                }
            } else {
                applyDao.save(applyMessage);
                logger.info("存储成功");
                message.setMessage("报名成功");
            }
        } catch (Exception e) {
            logger.error("执行过程中出现了错误：");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public HttpMessage applyResult(ApplyMessage applyMessage) {
        HttpMessage message = new HttpMessage("200");
        logger.info("提交的信息是："+applyMessage.toString());
        String department = applyMessage.getDepartment();
        //查询结果
        Integer flag= applyDao.findApplyResult(applyMessage.getName(),applyMessage.getPhone(),department);
        logger.info("查询信息是："+ flag);
        //判空
        if(flag == null) {
            message.setMessage("您所查询的结果不存在");
        } else {
            if(flag == 1) {
                message.setMessage("恭喜您！通过了"+applyMessage.getDepartment()+"的面试，详情请关注邮件信息");
            } else {
                message.setMessage("很遗憾，您并未被录取");
            }
        }
        return message;
    }
}
