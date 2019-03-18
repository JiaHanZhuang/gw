package com.zjh.website.service.admin.impl;

import com.zjh.website.dao.ApplyDao;
import com.zjh.website.pojo.ApplyMessage;
import com.zjh.website.service.admin.ApplyMessageService;
import com.zjh.website.utils.HttpMessage;
import com.zjh.website.utils.HttpMessageAndObject;
import com.zjh.website.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄家瀚
 */
@Service
public class ApplyMessageServiceImpl implements ApplyMessageService {

    @Autowired
    private ApplyDao applyDao;
    private Logger logger =  LoggerFactory.getLogger(ApplyMessageServiceImpl.class);

    @Override
    public HttpMessageAndObject<PageUtil<ApplyMessage>> findApplyMessage(ApplyMessage applyMessage, String key, Integer pageNumber, Integer pageSize) {
        HttpMessageAndObject<PageUtil<ApplyMessage>> httpMessage = new HttpMessageAndObject<PageUtil<ApplyMessage>>("200");
        //准备分页
        Pageable pageable=PageRequest.of(pageNumber-1, pageSize);
        PageUtil<ApplyMessage> pageUtil = new PageUtil<>();
        //实例化一个类
        Specification<ApplyMessage> specification = new Specification<ApplyMessage>() {
            @Override
            /**
             * root : 要查询的类型
             * criteriaQuery : 添加条件
             * criteriaBuilder : 构造条件
             */
            public Predicate toPredicate(Root<ApplyMessage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个条件集合
                List<Predicate> predicates = new ArrayList<Predicate>();
                //判断条件中是否含有部门
                if(applyMessage.getDepartment()!=null) {
                    /** cb.equal（）相当于判断后面两个参数是否一致
                     *root相当于我们的实体类的一个路径，使用get可以获取到我们的字段，department为String类型
                     * 所以不用as(xxxx.class)
                     *如果为Int,就是as(Integer.class) 第二个参数为前台传过来的参数，这句话就相当于
                     * 数据库字段的值department = 前台传过来的值applyMessage.getDepartment()
                     */
                    predicates.add(criteriaBuilder.equal(root.get("department"),applyMessage.getDepartment()));
                }
                //判断条件中是否含有性别
                if(applyMessage.getSex()!=null) {
                    predicates.add(criteriaBuilder.equal(root.get("sex").as(Integer.class),applyMessage.getSex()));
                }
                //判断条件中是否含有通过标识
                if (applyMessage.getMark()!=null) {
                    predicates.add(criteriaBuilder.equal(root.get("mark").as(Integer.class),applyMessage.getMark()));
                }
                //判断是否有查询条件
                if (key!=null) {
                    predicates.add(criteriaBuilder.like(root.get("name"),"%"+applyMessage.getName()+"%"));
                    predicates.add(criteriaBuilder.like(root.get("profession"),"%"+applyMessage.getProfession()+"%"));
                    predicates.add(criteriaBuilder.like(root.get("phone"),"%"+applyMessage.getPhone()+"%"));
                    predicates.add(criteriaBuilder.like(root.get("email"),"%"+applyMessage.getEmail()+"%"));
                    predicates.add(criteriaBuilder.like(root.get("qq"),"%"+applyMessage.getQq()+"%"));
                }
                //创建一个条件的集合，长度为上面满足条件的个数
                Predicate[] pre = new Predicate[predicates.size()];
                //这句大概意思就是将上面拼接好的条件返回去
                return criteriaQuery.where(predicates.toArray(pre)).getRestriction();
            }
        };
        Page<ApplyMessage> page = applyDao.findAll(specification,pageable);
        pageUtil.setContent(page.getContent()).setHasFirstPage(page.isFirst())
                .setHasContent(page.hasContent()).setHasLastPage(page.isLast())
                .setHasNextPage(page.hasNext()).setHasPerviousPage(page.hasPrevious())
                .setNumberOfElements(page.getNumberOfElements())
                .setPageNumber(page.getNumber()).setPageSize(page.getSize())
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages());
        httpMessage.setObj(pageUtil);
        return httpMessage;
    }
}
