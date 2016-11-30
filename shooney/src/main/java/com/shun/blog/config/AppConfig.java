package com.shun.blog.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.shun.blog.config.security.converter.RoleToUserProfileConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.shun.blog")
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;

	// 리소스 매핑 처리
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

	// Message Source(Error part)
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// Language 설정부분
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request로 넘어오는 language parameter를 받아서 locale로 설정한다.
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver() {
		// 세션 기준으로 로케일을 설정한다.
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 쿠키기준(세션이 끊겨도 브라우저에 설정된 쿠기기준)
		// CookieLocaleResolver localeResolver2=new CookieLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("ko_KR"));
		return localeResolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	// Dispatcher Servlet Part
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	// File upload part
	@Bean
	public CommonsMultipartResolver MultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSizePerFile(10000000);// 10,000,000(1000만) 바이트 == 10MB
		return resolver;
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
