package com.namyang.nyorder.prmt.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.service.BasicService;
import com.namyang.nyorder.comm.vo.BasicVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.vo.PrmtCstSearchVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉 애음자 조회
 * 파일명  : PrmtCstSearchController.java
 * 작성자  : YESOL
 * 작성일  : 2022. 2. 21.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 21.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Controller
public class PrmtCstSearchController {
	@Autowired
	BasicService basicService;

	@Resource(name="userInfo")
	UserInfo userInfo;
	
	
	/**
	 * @Method Name : prmtCstSearch
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : YESOL
	 * @Method 설명 : 판촉 애음자 조회 - 페이지 이동
	 * @param mv
	 * @return ModelAndView
	 */
	@RequestMapping(value="/prmt/prmtCstSearch.do")
	public ModelAndView prmtCstSearch(PrmtCstSearchVO param, ModelAndView mv) throws Exception {
		BasicVO basicVO = new BasicVO();
		/*
		 * basicVO.setQueryId(
		 * "com.namyang.nyorder.prmt.dao.PrmtCstSearchMapper.selectEmplList");
		 * basicVO.setParamClass(param); mv.addObject("emplList",
		 * basicService.basicSelectList(basicVO));
		 */
		
		mv.setViewName("prmt/prmtCstSearch.view");
		return mv;
	}
	
	/**
	 * @Method Name : selectPrmtCstSearchList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : YESOL
	 * @Method 설명 : 판촉 애음자 조회 - 리스트 조회
	 * @param param
	 * @param request
	 * @return List<PrmtCstSearchVO>
	 */
	@RequestMapping(value="/prmt/selectPrmtCstSearchList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<PrmtCstSearchVO> selectPrmtCstSearchList(PrmtCstSearchVO param, HttpServletRequest request) throws Exception {
		BasicVO basicVO = new BasicVO();
		basicVO.setQueryId("com.namyang.nyorder.prmt.dao.PrmtCstSearchMapper.selectPrmtCstSearchList");		
		basicVO.setParamClass(param);	
		
		return basicService.basicSelectList(basicVO);
	}
}
