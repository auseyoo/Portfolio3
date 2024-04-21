package com.namyang.nyorder.comm.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.PrdClsVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통 코드 관리 Controller
 * 파일명  : CommCodeController.java
 * 작성자  : YESOL
 * 작성일  : 2022. 1. 4.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 1. 4.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Slf4j
@Controller
public class CommCodeController {
	@Autowired
	CommCodeService commCodeService;

	@Resource(name="userInfo")
	UserInfo userInfo;
	
	/**
	 * @Method Name : selectPrdCls
	 * @작성일 : 2022. 01. 20.
	 * @작성자 : YESOL
	 * @Method 설명 : 제품 분류 조회
	 * @param param
	 * @return List<CommCodeVO>
	 */
	@RequestMapping(value="/comm/selectPrdCls.do", method = RequestMethod.POST)
	@ResponseBody
	public List<PrdClsVO> selectPrdCls(PrdClsVO param) {
		return commCodeService.selectPrdCls(param);
	}
}
