package com.namyang.nyorder.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.namyang.nyorder.util.MessageUtils;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : message Source config
 * 파일명  : MessageSourceConfig.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 3.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 3.        kjin        최조 프로그램 작성
 *
 */
@Configuration
public class MessageSourceConfig {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
		String[] messageList = new String[] {"classpath:message/messages"};
		rrbms.setBasenames(messageList);
		rrbms.setDefaultEncoding("UTF-8");
		rrbms.setCacheSeconds(60);
		
		return rrbms;
	}
	
	@Bean
	public MessageSourceAccessor messageSourceAccessor(ReloadableResourceBundleMessageSource messageSource){
		return new MessageSourceAccessor(messageSource);
	}
	
	@Bean
	public MessageUtils message(){
		MessageUtils messageUtils = new MessageUtils();
		messageUtils.setMessageSourceAccessor(messageSourceAccessor(messageSource()));
		return messageUtils;
	}
	
	@Bean
	public SessionLocaleResolver localeResolver(){
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.KOREA);
		return slr;
	}
	
	
}
