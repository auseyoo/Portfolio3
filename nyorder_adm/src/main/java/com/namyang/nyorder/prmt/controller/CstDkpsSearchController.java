package com.namyang.nyorder.prmt.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.prmt.service.CstDkpsSearchService;
import com.namyang.nyorder.prmt.service.PrmtEmplMngService;
import com.namyang.nyorder.prmt.vo.CstDkpsSearchVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 애음자 음용 현황조회
 * 파일명  : CstDkpsSearchController.java
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
public class CstDkpsSearchController {
	
	@Autowired
	private CstDkpsSearchService cstDkpsSearchService;
	
	@Autowired
	private PrmtEmplMngService prmtEmplMngService;
	
	@Resource(name="userInfo")
	private UserInfo userInfo;
	
	/**
	 * @Method Name : cstDkpsSearch
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 애음자 음용 현황조회
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/cstDkpsSearch.do")
	public ModelAndView cstDkpsSearch(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bhfcList", prmtEmplMngService.getBhfcList());
		
		mv.setViewName("prmt/cstDkpsSearch");
		return mv;
	}
	
	/**
	 * @Method Name : cstDkpsSearchList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 애음자 음용 현황조회 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/cstDkpsSearchList.do")
	@ResponseBody
	public List<CstDkpsSearchVO> cstDkpsSearchList(CstDkpsSearchVO param, HttpServletRequest request) {
		return cstDkpsSearchService.cstDkpsSearchList(param);
	}
	
	
	
}
