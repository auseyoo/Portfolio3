package com.namyang.nyorder.prmt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.prmt.service.PrmtEmplMngService;
import com.namyang.nyorder.prmt.vo.PrmtEmplHisVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplMngVO;
import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 판촉사원관리
 * 파일명  : PrmtEmplMngController.java
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
public class PrmtEmplMngController {
	
	@Autowired
	private PrmtEmplMngService prmtEmplMngService;
	
	@Autowired
	private CommCodeService commCodeService;
	
	@Resource(name="userInfo")
	private UserInfo userInfo;
	
	/**
	 * @Method Name : prmtEmplMng
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtEmplMng.do")
	public ModelAndView prmtEmplMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bhfcList", prmtEmplMngService.getBhfcList());
		mv.setViewName("prmt/prmtEmplMng");
		return mv;
	}
	
	/**
	 * @Method Name : prmtEmplMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/prmtEmplMngList.do")
	@ResponseBody
	public List<PrmtEmplMngVO> prmtEmplMngList(PrmtEmplMngVO param, HttpServletRequest request) {
		return prmtEmplMngService.prmtEmplMngList(param);
	}
	
	/**
	 * @Method Name : prmtEmplReg
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리 등록
	 * @param request
	 * @return
	 */
	@GetMapping(value="/prmt/prmtEmplReg.do")
	public ModelAndView prmtEmplReg(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bhfcList", prmtEmplMngService.getBhfcList());
		mv.addObject("emplSeclList", prmtEmplMngService.selectEmplSecCommCodeList());
		mv.setViewName("prmt/prmtEmplReg");
		return mv;
	}
	
	/**
	 * @Method Name : prmtEmplMngSave
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : prmtEmplMngSave
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/prmt/prmtEmplMngSave.do")
	@ResponseBody
	public Map<String, Object> prmtEmplMngSave(HttpServletRequest request, @RequestBody Map<String, Object> param) throws Exception {
		return prmtEmplMngService.prmtEmplMngSave(param);
	}
	
	/**
	 * @Method Name : prmtEmplReg
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리 상세
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/prmtEmplDtl.do")
	public ModelAndView prmtEmplDtl(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bhfcList", prmtEmplMngService.getBhfcList());
		mv.addObject("emplSeclList", prmtEmplMngService.selectEmplSecCommCodeList());
		mv.addObject("prmtEmplInfo", prmtEmplMngService.getPrmtEmplInfo(param));
		mv.setViewName("prmt/prmtEmplDtl");
		return mv;
	}
	
	/**
	 * @Method Name : prmtEmplHisList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리 상세 근무이력
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/prmt/prmtEmplHisList.do")
	@ResponseBody
	public List<PrmtEmplHisVO> prmtEmplHisList(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		return prmtEmplMngService.getPrmtEmplHisList(param);
	}
	
	/**
	 * @Method Name : prmtEmplDtlUpt
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원관리 상세 저장
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/prmt/prmtEmplDtlUpt.do")
	@ResponseBody
	public Map<String, Object> prmtEmplDtlUpt(HttpServletRequest request, @RequestBody Map<String, Object> param) throws Exception {
		return prmtEmplMngService.prmtEmplDtlUpt(param);
	}
	
	
	
}
