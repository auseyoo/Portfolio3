package com.namyang.nyorder.prmt.dao;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.prmt.vo.PrmtHopMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 팝촉홉수관리
 * 파일명  : PrmtHopMngMapper.java
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
public interface PrmtHopMngMapper {
	
	/**
	 * @Method Name : prmtHopMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수관리 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtHopMngVO> prmtHopMngList(PrmtHopMngVO param);
	
	/**
	 * @Method Name : insertPrmtHop
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수관리 등록
	 * @param param
	 * @return
	 */
	public int insertPrmtHop(Map<String, Object> param);
	
	/**
	 * @Method Name : updatePrmtHop
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수관리 업데이트
	 * @param param
	 * @return
	 */
	public int updatePrmtHop(Map<String, Object> param);
	
	/**
	 * @Method Name : selectPrdMst
	 * @작성일 : 2022. 3. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 제품코드 전체 목록
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectPrdMst(Map<String, Object> param);
	
	
}
