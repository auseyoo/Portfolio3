package com.namyang.nyorder.prmt.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.prmt.vo.CstDkpsSearchVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 애음자 음용 현황조회
 * 파일명  : CstDkpsSearchService.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
public interface CstDkpsSearchService {
	
	/**
	 * @Method Name : cstDkpsSearchList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수 리스트
	 * @param param
	 * @return
	 */
	public List<CstDkpsSearchVO> cstDkpsSearchList(CstDkpsSearchVO param);
	
	
	
}