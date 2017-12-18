package org.superbiz.struts;


import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }



    @Bean
    public FilterRegistrationBean filterDispatcher() {
        return createFilterDispatcher();
    }

    private FilterRegistrationBean createFilterDispatcher() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new FilterDispatcher());
        registration.setName("struts2");
        registration.setOrder(Integer.MAX_VALUE - 2);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("actionPackages", "com.lq");
        return registration;
    }

    @Bean
    public FilterRegistrationBean actionContextCleanUpFilter() {
        return createActionContextCleanUp();
    }

    private FilterRegistrationBean createActionContextCleanUp() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new ActionContextCleanUp());
        registration.setName("struts-cleanup");
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean pageFilterFilter() {
        return createPageFilter();
    }

    private FilterRegistrationBean createPageFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new PageFilter());
        registration.setName("sitemesh");
        registration.setOrder(Integer.MAX_VALUE);
        registration.addUrlPatterns("/*");
        return registration;
    }

}


