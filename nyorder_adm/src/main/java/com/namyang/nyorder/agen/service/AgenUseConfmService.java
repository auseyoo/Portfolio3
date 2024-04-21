package com.namyang.nyorder.agen.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.agen.vo.AgenUseConfmVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점 사용승인
 * 파일명  : AgenUseConfmService.java
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
public interface AgenUseConfmService {
	
	/**
	 * @Method Name : agenUseConfmList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 권한관리 리스트
	 * @param param
	 * @return
	 */
	public List<AgenUseConfmVO> agenUseConfmList(AgenUseConfmVO param);
	
	/**
	 * @Method Name : agenUseConfmUpd
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 권한 저장
	 * @param param
	 * @return
	 */
	public Map<String, Object> agenUseConfmUpd(Map<String, Object> param);
	
	
}