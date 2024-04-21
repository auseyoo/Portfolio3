package com.namyang.nyorder.prmt.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.CommCodeVO;
import com.namyang.nyorder.comm.vo.PrdClsVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.service.PrmtAgenRqstService;
import com.namyang.nyorder.prmt.service.PrmtEmplMngService;
import com.namyang.nyorder.prmt.service.PrmtPrdService;
import com.namyang.nyorder.prmt.service.PrmtRqstMngService;
import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 소요량 요청 관리 (본사) 
 * 파일명  : PrmtAgenRqstHoffController.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 7.        JUNGAE		최조 프로그램 작성
 *
 */
@Slf4j
@Controller
public class PrmtAgenRqstHoffController {
	
	@Autowired
	private PrmtAgenRqstService prmtAgenRqstService;
	
	@Autowired
	private PrmtRqstMngService prmtRqstMngService;
	
	@Autowired
	private PrmtEmplMngService prmtEmplMngService;
	
	@Autowired
	private CommCodeService commCodeService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : prmtPrd
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청 관리 (본사) 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtAgenRqstHoff.do")
	public ModelAndView prmtRqstMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prmt/prmtAgenRqstHoff");

		// 지점 코드
		mv.addObject("bhfcList", prmtEmplMngService.getBhfcList());
		
		// 시,방판 구분코드
		CommCodeVO commCodeParam = new CommCodeVO();
		commCodeParam.setCommGrpCd("SALE_CD");
		mv.addObject("saleCd", commCodeService.selectCommCodeList(commCodeParam));
		
		// 요청기간 정보
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); 
		Calendar c1 = Calendar.getInstance(); 
		String reqYm = sdf.format(c1.getTime());

		PrmtRqstMngVO param2 = new PrmtRqstMngVO();
		param2.setDvyfgRqstMonth(reqYm);
		mv.addObject("rqstMonth", prmtRqstMngService.selectDvyfgRqstMonth(param2));
		
		return mv;
	}
	
	/**
	 * @Method Name : savePrmtAgenRqstHoff
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청관리(본사) 저장
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/savePrmtAgenRqstHoff.do")
	@ResponseBody
	public String savePrmtAgenRqstHoff(@RequestBody List<PrmtAgenRqstVO> list, HttpServletRequest request) {
		return prmtAgenRqstService.savePrmtAgenRqst(list);
	}
}
