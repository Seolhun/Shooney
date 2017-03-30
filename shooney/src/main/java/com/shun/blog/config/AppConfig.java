package com.shun.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.shun.blog.config.security.converter.RoleToUserProfileConverter;
import com.shun.mongodb.config.MongoConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.shun")
@Import({ MongoConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	private static final int MAX_UPLOAD_SIZE=(1024 * 1024 * 200);
	private static final int MAX_UPLOAD_SIZE_PER_FILE=(1024 * 1024 * 50);
	
	// Resource InterCeptor Error Config Part
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	// MessageSource Error Config Part
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// Locale Language Config Part
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	//Locale Resolver
	@Bean
	public LocaleResolver localeResolver() {
		LocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		return localeResolver;
	}

	// Multipart Config Part
	@Bean(name="multipartResolver")
	public MultipartResolver resolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE); //40M
		multipartResolver.setMaxUploadSizePerFile(MAX_UPLOAD_SIZE_PER_FILE);//10M
		multipartResolver.setMaxInMemorySize(MAX_UPLOAD_SIZE * 2);
		multipartResolver.setResolveLazily(true);
		multipartResolver.setPreserveFilename(true);
		return multipartResolver;
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

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
