package com.namyang.nyorder.prmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.service.PrmtRqstMngService;
import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 기간 관리
 * 파일명  : PrmtRqstMngController.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.        JUNGAE		최조 프로그램 작성
 *
 */
@Slf4j
@Controller
public class PrmtRqstMngController {
	
	@Autowired
	private PrmtRqstMngService prmtRqstMngService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : prmtRqstMng
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtRqstMng.do")
	public ModelAndView prmtRqstMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prmt/prmtRqstMng");
		return mv;
	}
	
	/**
	 * @Method Name : selectPrmtRqstMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 관리 조회
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/selectPrmtRqstMngList.do")
	@ResponseBody
	public List<PrmtRqstMngVO> selectPrmtRqstMngList(PrmtRqstMngVO param, HttpServletRequest request) {
		return prmtRqstMngService.selectPrmtRqstMngList(param);
	}
	
	/**
	 * @Method Name : savePrmtRqstMng
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 관리 수정
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/savePrmtRqstMng.do")
	@ResponseBody
	public String savePrmtRqstMng(@RequestBody List<PrmtRqstMngVO> list) {
		return prmtRqstMngService.savePrmtRqstMng(list);
	}
	
	/**
	 * @Method Name : selectDvyfgRqstMonth
	 * @작성일 : 2022. 2. 16.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청월 조회
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/prmt/selectDvyfgRqstMonth.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectDvyfgRqstMonth(PrmtRqstMngVO param, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", prmtRqstMngService.selectDvyfgRqstMonth(param));
		return result;
	}
}
