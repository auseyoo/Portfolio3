package com.namyang.nyorder.auth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.auth.service.UserMngService;
import com.namyang.nyorder.auth.vo.UserMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 사용자별 권한관리 
 * 파일명  : UserMngController.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
@Controller
public class UserMngController {
	
	@Autowired
	UserMngService userMngService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : authMng
	 * @작성일 : 2022. 2. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 사용자별 권한관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/auth/userMng.do")
	public ModelAndView authMng(ModelAndView mv) {
		mv.setViewName("auth/userMng");
		return mv;
	}
	
	/**
	 * @Method Name : userMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 사용자별 권한관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/userMngList.do")
	@ResponseBody
	public List<UserMngVO> userMngList(UserMngVO param, HttpServletRequest request) {
		return userMngService.userMngList(param);
	}
	
	/**
	 * @Method Name : userMngUpd
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 사용자별 권한관리 수정
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/userMngUpd.do")
	@ResponseBody
	public Map<String, Object> userMngUpd(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return userMngService.userMngUpd(param);
	}
	
	
}
