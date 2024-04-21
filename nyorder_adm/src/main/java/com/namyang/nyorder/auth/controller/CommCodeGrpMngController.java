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

import com.namyang.nyorder.auth.service.CommCodeGrpMngService;
import com.namyang.nyorder.auth.vo.CommCodeGrpMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드그룹 권한관리 
 * 파일명  : CommCodeGrpMngController.java
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
public class CommCodeGrpMngController {
	
	@Autowired
	CommCodeGrpMngService commCodeGrpMngService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : authMng
	 * @작성일 : 2022. 2. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드그룹 관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/auth/commCodeGrpMng.do")
	public ModelAndView authMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/commCodeGrpMng");
		return mv;
	}
	
	/**
	 * @Method Name : commCodeGrpMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드그룹 관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/commCodeGrpMngList.do")
	@ResponseBody
	public List<CommCodeGrpMngVO> commCodeGrpMngList(CommCodeGrpMngVO param, HttpServletRequest request) {
		return commCodeGrpMngService.commCodeGrpMngList(param);
	}
	
	/**
	 * @Method Name : userGrpMngUpd
	 * @작성일 : 2022. 2. 25.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드그룹 관리 수정
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/auth/commCodeGrpMngUpd.do")
	@ResponseBody
	public Map<String, Object> userGrpMngUpd(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return commCodeGrpMngService.commCodeGrpMngUpd(param);
	}
	
	
}
