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

import com.namyang.nyorder.auth.service.CommCodeMngService;
import com.namyang.nyorder.auth.vo.CommCodeMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드 관리 
 * 파일명  : CommCodeMngController.java
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
public class CommCodeMngController {
	
	@Autowired
	CommCodeMngService commCodeMngService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : authMng
	 * @작성일 : 2022. 2. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/auth/commCodeMng.do")
	public ModelAndView authMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/commCodeMng");
		return mv;
	}
	
	/**
	 * @Method Name : commCodeMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/commCodeMngList.do")
	@ResponseBody
	public List<CommCodeMngVO> commCodeMngList(CommCodeMngVO param, HttpServletRequest request) {
		return commCodeMngService.commCodeMngList(param);
	}
	
	/**
	 * @Method Name : commGrpCodeCnt
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : kjin
	 * @Method 설명 : 그룹코드 존재 여부 카운트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/commGrpCodeCnt.do")
	@ResponseBody
	public Map<String, Object> commGrpCodeCnt(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return commCodeMngService.commGrpCodeCnt(param);
	}
	
	/**
	 * @Method Name : userGrpMngUpd
	 * @작성일 : 2022. 2. 25.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 관리 수정
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/auth/commCodeMngUpd.do")
	@ResponseBody
	public Map<String, Object> userGrpMngUpd(HttpServletRequest request, @RequestBody Map<String, Object> param) throws Exception {
		return commCodeMngService.commCodeMngUpd(param);
	}
	
	
}
