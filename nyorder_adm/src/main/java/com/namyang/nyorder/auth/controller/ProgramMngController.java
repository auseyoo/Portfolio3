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

import com.namyang.nyorder.auth.service.ProgramMngService;
import com.namyang.nyorder.auth.vo.ProgramMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 프로그램 권한관리 
 * 파일명  : ProgramMngController.java
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
public class ProgramMngController {
	
	@Autowired
	ProgramMngService programMngService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : authMng
	 * @작성일 : 2022. 2. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/auth/programMng.do")
	public ModelAndView authMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("groupLvList", programMngService.getGropLevel());
		mv.addObject("getParentMenuList", programMngService.getParentMenuList());
		mv.setViewName("auth/programMng");
		return mv;
	}
	
	/**
	 * @Method Name : programMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/auth/programMngList.do")
	@ResponseBody
	public List<ProgramMngVO> programMngList(ProgramMngVO param, HttpServletRequest request) {
		return programMngService.programMngList(param);
	}
	
	/**
	 * @Method Name : userGrpMngUpd
	 * @작성일 : 2022. 2. 25.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 수정
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/auth/programMngUpd.do")
	@ResponseBody
	public Map<String, Object> programMngUpd(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return programMngService.programMngUpd(param);
	}
	
	/**
	 * @Method Name : programMngReg
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 등록
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/auth/programMngReg.do")
	@ResponseBody
	public Map<String, Object> programMngReg(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return programMngService.programMngReg(param);
	}
	
	
	
}
