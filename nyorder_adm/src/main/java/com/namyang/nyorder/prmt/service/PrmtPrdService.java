package com.namyang.nyorder.prmt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

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
public interface PrmtPrdService {
	
	/**
	 * @Method Name : selectPrmtRqstMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtPrdVO> selectPrmtPrdList(PrmtPrdVO param);
	
	/**
	 * @Method Name : savePrmtPrd
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 여부 저장
	 * @param param
	 * @return
	 */
	public String savePrmtPrd(List<PrmtPrdVO> list);
	
	/**
	 * @Method Name : copyPrmtPrd
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 전월복사
	 * @param param
	 * @return
	 */
	public String copyPrmtPrd(PrmtPrdVO param);
	
	/**
	 * @Method Name : uploadPrmtPrd
	 * @작성일 : 2022. 3. 17.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 엑셀업로드
	 * @param param
	 * @return
	 */
	public Map<String, Object> uploadPrmtPrd(PrmtPrdVO param, MultipartFile paramFile) throws Exception ;
}