package com.zjh.website.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *<p>Title: ServiceConfig.java</p>
 *<p>Description: Service配置</p>
 *<p>CreateDate: 2019年2月25日</p>
 * @author zjh
 * @version v1.0
 */
@Configuration
@Import({DruidDataSourceConfig.class})
@ComponentScan("com.zjh.website.service")
public class ServiceConfig {

}
