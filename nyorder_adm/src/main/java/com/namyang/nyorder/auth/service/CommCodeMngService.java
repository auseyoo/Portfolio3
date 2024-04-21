package com.namyang.nyorder.auth.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.auth.vo.CommCodeMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드 관리 
 * 파일명  : CommCodeMngService.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 공통코드그룹 작성
 *
 */
public interface CommCodeMngService {
	
	/**
	 * @Method Name : commCodeMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 관리 리스트
	 * @param param
	 * @return
	 */
	public List<CommCodeMngVO> commCodeMngList(CommCodeMngVO param);
	
	/**
	 * @Method Name : commGrpCodeCnt
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : kjin
	 * @Method 설명 : 그룹코드 존재 여부 카운트
	 * @param param
	 * @return
	 */
	public Map<String, Object> commGrpCodeCnt(Map<String, Object> param);
	
	/**
	 * @Method Name : commCodeMngUpd
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 관리 저장
	 * @param param
	 * @return
	 */
	public Map<String, Object> commCodeMngUpd(Map<String, Object> param) throws Exception;
	
	
}