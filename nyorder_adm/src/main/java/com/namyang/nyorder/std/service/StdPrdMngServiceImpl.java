package com.namyang.nyorder.std.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.std.dao.StdPrdMngMapper;
import com.namyang.nyorder.std.vo.StdPrdMngDetailVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  :  기준벙보관리 - 표준 제품 관리 Service Implement
 * 파일명  : StdPrdMngServiceImpl.java
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
@Service
public class StdPrdMngServiceImpl implements StdPrdMngService {
	
	@Autowired
	private StdPrdMngMapper stdPrdMngMapper;
	
	@Resource(name="userInfo")
	private UserInfo userInfo;

	/**
	 * 표준 제품 상세 조회
	 */
	public StdPrdMngDetailVO selectStdPrdMngDetail(StdPrdMngDetailVO param) {
		return stdPrdMngMapper.selectStdPrdMngDetail(param);
	}

}
