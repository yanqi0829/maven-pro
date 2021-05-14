package com.example.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean  //将自定义的DruidDataSource加到容器中，不用springboot创建
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 主要实现WEB监控的配置处理
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        // 现在要进行druid监控的配置处理操作
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        // 白名单,多个用逗号分割， 如果allow没有配置或者为空，则允许所有访问
        bean.addInitParameter("allow", "127.0.0.1,172.29.32.54");
        // 黑名单,多个用逗号分割 (共同存在时，deny优先于allow)
        bean.addInitParameter("deny", "192.168.1.110");
        // 控制台管理用户名
        bean.addInitParameter("loginUsername", "admin");
        // 控制台管理密码
        bean.addInitParameter("loginPassword", "admin");
        // 是否可以重置数据源，禁用HTML页面上的“Reset All”功能
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean() ;
        bean.setFilter(new WebStatFilter());
        //所有请求进行监控处理
        bean.addUrlPatterns("/*");
        //这些东西不需要统计
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return bean ;
    }
}

