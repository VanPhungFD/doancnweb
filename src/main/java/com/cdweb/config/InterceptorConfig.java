package com.cdweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cdweb.interceptor.SecurityInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	// tim bean SecurityInterceptor
	@Autowired
	SecurityInterceptor auth;
	/*
	 * chắn người dùng khi chưa đăng nhập req tới các url ..
	 * - Path1 : bao gồm url mapping tới trang người dùng
	 * -Path2 : bao gồm url mapping tới trang quản trị, 
	 * **/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(auth)
		.addPathPatterns("/account/change","/account/edit","/order/**")
		.addPathPatterns("/admin/**").excludePathPatterns("/admin/home/index");
	}

}
