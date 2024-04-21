package com.namyang.nyorder.auth.dao;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.auth.vo.CommCodeMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드 관리 
 * 파일명  : CommCodeMngMapper.java
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
public interface CommCodeMngMapper {
	
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
	 * @Method Name : selectGrpCodeCnt
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : kjin
	 * @Method 설명 : 그룹코드 존재 여부 카운트
	 * @param param
	 * @return
	 */
	public int selectGrpCodeCnt(Map<String, Object> param);
	
	/**
	 * @Method Name : updateCommCodeMng
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 권한 저장
	 * @param param
	 * @return
	 */
	public int updateCommCodeMng(Map<String, Object> param);
	
	/**
	 * @Method Name : insertCommCodeMng
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 권한 신규 등록
	 * @param param
	 * @return
	 */
	public int insertCommCodeMng(Map<String, Object> param);
	
	/**
	 * @Method Name : deleteCommCodeMng
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 공통코드 권한 삭제
	 * @param param
	 * @return
	 */
	public int deleteCommCodeMng(Map<String, Object> param);
	
	
}
