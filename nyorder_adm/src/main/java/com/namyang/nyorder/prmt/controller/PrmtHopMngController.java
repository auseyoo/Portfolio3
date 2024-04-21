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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.prmt.service.PrmtHopMngService;
import com.namyang.nyorder.prmt.vo.PrmtHopMngVO;
import com.namyang.nyorder.util.ExcelReadUtil;
import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.PrdClsVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 판촉홉수관리
 * 파일명  : PrmtHopMngController.java
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
public class PrmtHopMngController {
	
	@Autowired
	private PrmtHopMngService prmtHopMngService;
	
	@Autowired
	private CommCodeService commCodeService;
	
	@Autowired
	private ExcelReadUtil excelReadUtil;
	
	@Resource(name="userInfo")
	private UserInfo userInfo;
	
	/**
	 * @Method Name : prmtHopMng
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수관리
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtHopMng.do")
	public ModelAndView prmtHopMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("lclsList", commCodeService.selectPrdCls(new PrdClsVO()));
		mv.setViewName("prmt/prmtHopMng");
		return mv;
	}
	
	/**
	 * @Method Name : prmtHopMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수관리 리스트
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/prmtHopMngList.do")
	@ResponseBody
	public List<PrmtHopMngVO> prmtHopMngList(PrmtHopMngVO param, HttpServletRequest request) {
		return prmtHopMngService.prmtHopMngList(param);
	}
	
	/**
	 * @Method Name : prmtHopMngSave
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : prmtHopMngSave
	 * @param request
	 * @param param
	 * @return
	 */
	@PostMapping(value="/prmt/prmtHopMngSave.do")
	@ResponseBody
	public Map<String, Object> prmtHopMngSave(HttpServletRequest request, @RequestBody Map<String, Object> param) throws Exception {
		return prmtHopMngService.prmtHopMngSave(param);
	}
	
	/**
	 * @Method Name : excelUpload
	 * @작성일 : 2022. 3. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 
	 * @param file
	 * @param request
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/prmt/excelUpload.do")
	@ResponseBody
	public Map<String, Object> excelUpload(@RequestParam("file") MultipartFile file) throws Exception {
		
		// 엑셀 헤더 정보 구성
		String[] headerInfo = {"prdSapCd", "prmtType", "hop1", "hop2", "hop3", "hop4", "hop5", "hop6", "hop7"};
		
		// 엑셀 파일을 읽어 데이터 가져오기
		List<HashMap<String, Object>> list = excelReadUtil.uploadExcel(file, headerInfo, 2);
		log.debug("excel data :: " + list);
		
		return prmtHopMngService.excelUpload(list);
	}
	
	
}
