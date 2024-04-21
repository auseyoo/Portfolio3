package com.namyang.nyorder.auth.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.auth.vo.ProgramMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 프로그램 권한관리 
 * 파일명  : ProgramMngService.java
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
public interface ProgramMngService {
	
	/**
	 * @Method Name : getGropLevel
	 * @작성일 : 2022. 2. 25.
	 * @작성자 : kjin
	 * @Method 설명 : 그룹 Level 목록 뽑기
	 * @return
	 */
	public List<ProgramMngVO> getGropLevel();
	
	/**
	 * @Method Name : getParentMenuList
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : kjin
	 * @Method 설명 : 상위그룹 리스트
	 * @return
	 */
	public List<ProgramMngVO> getParentMenuList();
	
	/**
	 * @Method Name : programMngList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한관리 리스트
	 * @param param
	 * @return
	 */
	public List<ProgramMngVO> programMngList(ProgramMngVO param);
	
	/**
	 * @Method Name : programMngUpd
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한 저장
	 * @param param
	 * @return
	 */
	public Map<String, Object> programMngUpd(Map<String, Object> param);
	
	/**
	 * @Method Name : programMngReg
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : kjin
	 * @Method 설명 : 프로그램 권한 등록
	 * @param param
	 * @return
	 */
	public Map<String, Object> programMngReg(Map<String, Object> param);
	
	
}