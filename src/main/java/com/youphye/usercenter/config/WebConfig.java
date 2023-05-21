package com.youphye.usercenter.config;

import com.youphye.usercenter.interceptor.RoleCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName: WebConfig
 * @Package: com.youphye.usercenter.config
 * @Description: 配置拦截器
 * @Author Tina Serria
 * @Create 2023/5/21 14:51
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Resource
	private RoleCheckInterceptor roleCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(roleCheckInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/**/login")
				.excludePathPatterns("/**/register");
	}
}
