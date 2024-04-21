package com.namyang.nyorder.config.error.exception;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;

import com.namyang.nyorder.util.MessageUtils;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : BusinessException
 * 파일명  : BusinessException.java
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
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private transient String errorCd;
	private transient String errorMessage;
	public static final String EXCEPTION_DEFAULT_CODE = "-1";
	protected transient Logger log;

	public BusinessException() {
		this.log = LoggerFactory.getLogger(this.getClass());
	}

	public BusinessException(String msg) {
		this(msg, "-1");
	}

	public BusinessException(String msg, String errorCd) {
		this(msg, new String[0], errorCd);
	}

	public BusinessException(String msg, String[] args, String errorCd) {
		super(MessageUtils.getMessage(msg, args));
		this.log = LoggerFactory.getLogger(this.getClass());
		this.errorCd = errorCd;
		this.errorMessage = MessageUtils.getMessage(msg, args);
		this.log.error(this.errorMessage);
	}

	public BusinessException(String msg, String[] args) {
		super(MessageUtils.getMessage(msg, args));
		this.log = LoggerFactory.getLogger(this.getClass());
		this.errorCd = "-1";
		this.errorMessage = MessageUtils.getMessage(msg, args);
		this.log.error(this.errorMessage);
	}

	public BusinessException(Throwable texception) {
		this(texception.getMessage(), texception);
	}

	public BusinessException(String msg, Throwable texception) {
		this(msg, "-1", texception);
	}

	public BusinessException(String msg, String errorCode, Throwable texception) {
		this(msg, new String[0], errorCode, texception);
	}

	public BusinessException(String msg, String[] args, String errorCode, Throwable texception) {
		super(MessageUtils.getMessage(msg, args), texception);
		this.log = LoggerFactory.getLogger(this.getClass());
		this.log.error(texception.toString());
		this.errorMessage = MessageUtils.getMessage(msg, args);
		this.errorCd = errorCode;
		if (!(texception instanceof DuplicateKeyException) && texception instanceof SQLException) {
			SQLException sqlException = (SQLException) texception;
			this.errorCd = String.valueOf(sqlException.getErrorCode()).toString();
		}

	}

	public String getErrorCode() {
		return this.errorCd != null && !this.errorCd.equals("") ? this.errorCd : "-1";
	}

	public String getErrorMessage() {
		return this.errorMessage != null && !this.errorMessage.equals("") ? this.errorMessage : "";
	}
}
