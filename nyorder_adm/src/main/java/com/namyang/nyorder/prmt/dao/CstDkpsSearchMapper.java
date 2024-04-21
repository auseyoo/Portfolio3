package com.namyang.nyorder.prmt.dao;

import java.util.List;

import com.namyang.nyorder.prmt.vo.CstDkpsSearchVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 애음자 음용 현황조회
 * 파일명  : CstDkpsSearchMapper.java
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
public interface CstDkpsSearchMapper {
	
	/**
	 * @Method Name : cstDkpsSearchList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 애음자 음용 현황조회 리스트
	 * @param param
	 * @return
	 */
	public List<CstDkpsSearchVO> cstDkpsSearchList(CstDkpsSearchVO param);
	
	
	
}
