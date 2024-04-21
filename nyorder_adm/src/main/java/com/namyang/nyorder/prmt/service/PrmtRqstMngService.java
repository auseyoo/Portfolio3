package com.namyang.nyorder.prmt.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 기간 관리 
 * 파일명  : PrmtRqstMngService.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.		JUNGAE        최조 공통코드그룹 작성
 *
 */
public interface PrmtRqstMngService {
	
	/**
	 * @Method Name : selectPrmtRqstMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtRqstMngVO> selectPrmtRqstMngList(PrmtRqstMngVO param);
	
	/**
	 * @Method Name : savePrmtRqstMng
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 수정
	 * @param param
	 * @return
	 */
	public String savePrmtRqstMng(List<PrmtRqstMngVO> list);
	
	/**
	* @Method Name : selectDvyfgRqstMonth
	* @작성일 : 2022. 2. 25.
	* @작성자 : JUNGAE
	* @Method 설명 : 판촉물 소요량 요청월 조회
	* @param param
	* @return List<HashMap>
	*/
	public PrmtRqstMngVO selectDvyfgRqstMonth(PrmtRqstMngVO param);	
}