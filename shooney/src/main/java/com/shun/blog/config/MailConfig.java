package com.shun.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("onm10114@gmail.com");
		mailSender.setPassword("q1w2e3r4@");
		mailSender.setPort(587);
		mailSender.setDefaultEncoding("UTF-8");
		return mailSender;
		
//		<bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
//		<property name="host" value="smtp.gmail.com" />
//		<property name="port" value="587" />
//		<property name="defaultEncoding" value="UTF-8" />
//		<property name="username" value="imedisyndev@gmail.com" />
//		<property name="password" value="imedisyn@" />
//		<property name="javaMailProperties">
//			<props>
//				<prop key="mail.transport.protocol">smtp</prop>
//				<prop key="mail.smtp.auth">true</prop>
//				<prop key="mail.smtp.starttls.enable">true</prop>
//				<prop key="mail.debug">true</prop>
//				<prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
//			</props>
//		</property>
	//</bean>
	}
}
