package com.namyang.nyorder.prmt.service;

import java.util.List;

import com.namyang.nyorder.prmt.vo.PrmtInvryVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점별 판촉물 재고 현황
 * 파일명  : PrmtInvryService.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 11.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.		JUNGAE        최조 공통코드그룹 작성
 *
 */
public interface PrmtInvryService {

	/**
	 * @Method Name : selectPrmtInvryList
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 대리점별 판촉물 재고 현황 리스트
	 * @param param
	 * @return List<PrmtInvryVO>
	 */
	public List<PrmtInvryVO> selectPrmtInvryList(PrmtInvryVO param);
}