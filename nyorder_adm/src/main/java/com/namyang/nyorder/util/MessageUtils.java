package com.namyang.nyorder.util;

import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : MessageSource get Util
 * 파일명  : MessageUtils.java
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
public class MessageUtils {
	
	private static MessageSourceAccessor msAcc;

	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		MessageUtils.msAcc = msAcc;
	}

	public static String getMessage(String code) {
		String returnCode = "";

		try {
			returnCode = msAcc.getMessage(code, LocaleContextHolder.getLocale());
			return returnCode;
		} catch (NoSuchMessageException var3) {
			return code;
		} catch (Exception var4) {
			return code;
		}
	}

	public static String getMessage(String code, Object[] objs) {
		String returnCode = "";

		try {
			returnCode = msAcc.getMessage(code, objs, LocaleContextHolder.getLocale());
			return returnCode;
		} catch (NoSuchMessageException var4) {
			return code;
		} catch (Exception var5) {
			return code;
		}
	}

	public static String getMessage(String code, Object[] objs, Locale locale) {
		String returnCode = "";

		try {
			returnCode = msAcc.getMessage(code, objs, locale);
			return returnCode;
		} catch (NoSuchMessageException var5) {
			return code;
		} catch (Exception var6) {
			return code;
		}
	}
}

