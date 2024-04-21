package com.namyang.nyorder.prmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.namyang.nyorder.prmt.vo.PrmtHopMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 팝촉홉수관리
 * 파일명  : PrmtHopMngService.java
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
public interface PrmtHopMngService {
	
	/**
	 * @Method Name : prmtHopMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtHopMngVO> prmtHopMngList(PrmtHopMngVO param);
	
	/**
	 * 
	 * @Method Name : prmtHopMngSave
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 팝촉홉수 저장
	 * @param param
	 * @return
	 */
	public Map<String, Object> prmtHopMngSave(Map<String, Object> param) throws Exception;
	
	/**
	 * @Method Name : excelUpload
	 * @작성일 : 2022. 3. 18.
	 * @작성자 : kjin
	 * @Method 설명 : 엑셀 업로드
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> excelUpload(List<HashMap<String, Object>> list) throws Exception;
	
	
}