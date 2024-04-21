package com.namyang.nyorder.comm.vo;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Component
@SessionScope
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String admCd;
	private String password;	
	private String auth;
	private int enabled;
	
	private String admSeq;	
	private String admNm;
	private String bsnGrpCd;
	private String bhfcCd;
	private String grpCd;
	private String bhfcNm;
	private String menuAduType;
	
	private String hexPassword;
	private String tmpPwdYn;

}
