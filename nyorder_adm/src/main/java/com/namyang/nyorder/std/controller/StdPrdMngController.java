package com.namyang.nyorder.std.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.std.service.StdPrdMngService;
import com.namyang.nyorder.std.vo.StdPrdMngDetailVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 기준벙보관리 - 표준 제품 관리 Controller
 * 파일명  : StdPrdMngController.java
 * 작성자  : YESOL
 * 작성일  : 2022. 1. 11.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 1. 11.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Slf4j
@Controller
public class StdPrdMngController {

	@Autowired
	private StdPrdMngService stdPrdMngService;
	
	@Autowired
	private CommCodeService commCodeService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;

	/**
	 * @Method Name : selectStdPrdMngList
	 * @작성일 : 2022. 1. 11.
	 * @작성자 : YESOL
	 * @Method 설명 : 표준 제품 상세 조회 
	 * @param param
	 * @return List<CommCodeVO>
	 */
	@RequestMapping(value="/std/selectStdPrdMngDetail.do", method = RequestMethod.POST)
	@ResponseBody
	public StdPrdMngDetailVO selectStdPrdMngDetail(@RequestBody StdPrdMngDetailVO param, HttpServletRequest request) {
		return stdPrdMngService.selectStdPrdMngDetail(param);
	}
}
