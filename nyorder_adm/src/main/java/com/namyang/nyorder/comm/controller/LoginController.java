package com.namyang.nyorder.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.service.BasicService;
import com.namyang.nyorder.comm.service.LoginService;
import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.BasicVO;
import com.namyang.nyorder.comm.vo.MenuVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : Login Controller
 * 파일명  : LoginController.java
 * 작성자  : YESOL
 * 작성일  : 2022. 1. 4.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일			 변경자		   변경내역
 * --------------------------------------------------
 * 2022. 1. 4.	YESOL	 최조 프로그램 작성
 *
 ****************************************************/
@Slf4j
@Controller
public class LoginController {
		
	@Autowired
	private LoginService loginService;
	
	
//	@Value("#{messageProperties['alert.test2']}")
//	private String alertTest;
//	
//	@Value("#{messageProperties}")
//	Properties properties;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	BasicService basicService;
	
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv) throws Exception {
		
		//log.debug("alertTest====== ::" + messageSource.getMessage("alert.test2", new String[]{"Leica","지성"}, Locale.getDefault()));
		//log.debug("alertTest====== ::" + properties.getProperty("alert.test2"));
		
		String serverType = System.getProperty("server.type");		
		
		mv.setViewName("/login");
		return mv;
	}
	
	@RequestMapping("/changePassword.do")
	public ModelAndView changePassword(HttpServletRequest request, AccountVO loginVO, @SessionAttribute("userInfo") AccountVO account) {
		
		ModelAndView mv = new ModelAndView();		
		
		//loginService.setSessionInfo(account);		
		
		mv.addObject("userInfo", account);		
		mv.setViewName("/changePassword");
		return mv;
	}
	
	@RequestMapping(value="/savePassword.do")
	public String savePassword(AccountVO loginVO) throws Exception {
		int cnt = loginService.savePassword(loginVO);				
		return "redirect:/comm/main.do";
	}
	


}
