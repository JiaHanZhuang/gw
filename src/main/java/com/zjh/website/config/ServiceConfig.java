package com.zjh.website.config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zjh
 */
@Configuration
@Import({DruidDataSourceConfig.class})
@ComponentScan("com.zjh.website.service")
public class ServiceConfig {

}
