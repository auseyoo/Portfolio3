package com.namyang.nyorder.auth.dao;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.auth.vo.CommCodeGrpMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드그룹 권한관리 
 * 파일명  : CommCodeGrpMngMapper.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
public interface CommCodeGrpMngMapper {
	
	/**
	 * @Method Name : commCodeGrpMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 리스트
	 * @param param
	 * @return
	 */
	public List<CommCodeGrpMngVO> commCodeGrpMngList(CommCodeGrpMngVO param);
	
	/**
	 * @Method Name : commCodeGrpMngUpd
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한 저장
	 * @param param
	 * @return
	 */
	public int commCodeGrpMngUpd(Map<String, Object> param);
	
	
}
