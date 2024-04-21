package com.namyang.nyorder.std.service;

import com.namyang.nyorder.std.vo.StdPrdMngDetailVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 기준벙보관리 - 표준 제품 관리 Service
 * 파일명  : StdPrdMngService.java
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
public interface StdPrdMngService {

	/**
	 * @Method Name : selectStdPrdMngDetail
	 * @작성일 : 2022. 1. 11.
	 * @작성자 : YESOL
	 * @Method 설명 : 표준 제품 상세 조회
	 * @param param
	 * @return List<StdPrdMngDetailVO>
	 */
	public StdPrdMngDetailVO selectStdPrdMngDetail(StdPrdMngDetailVO param);

}
