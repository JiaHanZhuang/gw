package com.zjh.website.config;

import com.zjh.website.filter.RequestFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author zjh
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcWebConfig.class};
    }

    @Override
    /**
     * 指定映射地址
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    /**
     * 配置过滤器
     * @return 过滤器组
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        RequestFilter requestFilter = new RequestFilter();
        return new Filter[]{ characterEncodingFilter,requestFilter };
    }
}
