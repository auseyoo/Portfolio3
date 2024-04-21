package com.namyang.nyorder.agen.controller;

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

import com.namyang.nyorder.agen.service.AgenUseConfmService;
import com.namyang.nyorder.agen.vo.AgenUseConfmVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점 사용승인
 * 파일명  : AgenUseConfmController.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
@Controller
public class AgenUseConfmController {
	
	@Autowired
	AgenUseConfmService agenUseConfmService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : agenUseConfm
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/agen/agenUseConfm.do")
	public ModelAndView agenUseConfm(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("agen/agenUseConfm");
		return mv;
	}
	
	/**
	 * @Method Name : agenUseConfmList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/agen/agenUseConfmList.do")
	@ResponseBody
	public List<AgenUseConfmVO> agenUseConfmList(AgenUseConfmVO param, HttpServletRequest request) {
		return agenUseConfmService.agenUseConfmList(param);
	}
	
	/**
	 * @Method Name : userGrpMngUpd
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 수정
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/agen/agenUseConfmUpd.do")
	@ResponseBody
	public Map<String, Object> agenUseConfmUpd(HttpServletRequest request, @RequestBody Map<String, Object> param) {
		return agenUseConfmService.agenUseConfmUpd(param);
	}
	
	
	
}
