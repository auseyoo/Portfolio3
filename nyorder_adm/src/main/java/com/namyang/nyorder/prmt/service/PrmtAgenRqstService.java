package com.namyang.nyorder.prmt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtPrdVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 자재 관리 
 * 파일명  : PrmtPrdService.java
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
public interface PrmtAgenRqstService {

	/**
	 * @Method Name : selectPrmtAgenRqstList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청관리(지침) 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtAgenRqstVO> selectPrmtAgenRqstList(PrmtAgenRqstVO param);
	
	/**
	 * @Method Name : savePrmtAgenRqst
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청관리 수량변경
	 * @param param
	 * @return
	 */
	public String savePrmtAgenRqst(List<PrmtAgenRqstVO> list);
}