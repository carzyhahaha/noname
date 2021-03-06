package com.noname.adapter;

import com.noname.interceptor.CORSInterceptor;
import com.noname.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new CORSInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new PaginationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
