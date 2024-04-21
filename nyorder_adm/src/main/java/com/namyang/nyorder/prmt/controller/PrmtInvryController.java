package com.namyang.nyorder.prmt.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.service.PrmtInvryService;
import com.namyang.nyorder.prmt.vo.PrmtInvryVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점별 판촉물 재고 현황
 * 파일명  : PrmtInvryController.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 11.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 11.        JUNGAE		최조 프로그램 작성
 *
 */
@Slf4j
@Controller
public class PrmtInvryController {
	
	@Autowired
	private PrmtInvryService prmtInvryService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : prmtInvry
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 대리점별 판촉물 재고 현황 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtInvry.do")
	public ModelAndView prmtRqstMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prmt/prmtInvry");
		return mv;
	}
	
	/**
	 * @Method Name : selectPrmtInvryList
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 대리점별 판촉물 재고 현황 조회
	 * @param param
	 * @return List<CommCodeVO>
	 */
	@RequestMapping(value="/prmt/selectPrmtInvryList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<PrmtInvryVO> selectPrmtPrdList(PrmtInvryVO param, HttpServletRequest request) {
		return prmtInvryService.selectPrmtInvryList(param);
	}
}
