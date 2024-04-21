package com.namyang.nyorder.prmt.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.namyang.nyorder.prmt.vo.PrmtPrdVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 자재 관리
 * 파일명  : PrmtPrdMapper.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 7.    JUNGAE     최조 프로그램 작성
 *
 ****************************************************/
@Mapper
public interface PrmtPrdMapper {

	/**
	 * @Method Name : selectPrmtRqstMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 조회
	 * @param param
	 * @return List<PrmtRqstMngVO>
	 */
	public List<PrmtPrdVO> selectPrmtPrdList(PrmtPrdVO param);
	
	/**
	 * @Method Name : insPrmtPrd
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 여부 저장
	 * @param param
	 * @return void
	 */
	public void insPrmtPrd(HashMap<String, Object> param);
	
	/**
	 * @Method Name : insCopyPrmtPrd
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 전월복사
	 * @param param
	 * @return void
	 */
	public void insCopyPrmtPrd(PrmtPrdVO param);
	
	/**
	 * @Method Name : delPrmtPrd
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 삭제처리
	 * @param param
	 * @return void
	 */
	public void delPrmtPrd(PrmtPrdVO param);
	
	/**
	 * @Method Name : selectPrmtPrdSeq
	 * @작성일 : 2022. 3. 17.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 업로드할 제품 조회
	 * @param param
	 * @return PrmtPrdVO
	 */
	public PrmtPrdVO selectPrmtPrdSeq(PrmtPrdVO param);
	
	/**
	 * @Method Name : merUploadedPrmtPrd
	 * @작성일 : 2022. 3. 17.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 업로드할 제품 저장
	 * @param param
	 * @return void
	 */
	public void merUploadedPrmtPrd(PrmtPrdVO param);
}

