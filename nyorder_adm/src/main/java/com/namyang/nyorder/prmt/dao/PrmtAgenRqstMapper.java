package com.namyang.nyorder.prmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;

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
public interface PrmtAgenRqstMapper {

	/**
	 * @Method Name : selectPrmtAgenRqstList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청관리(지점) 조회
	 * @param param
	 * @return List<PrmtRqstMngVO>
	 */
	public List<PrmtAgenRqstVO> selectPrmtAgenRqstList(PrmtAgenRqstVO param);

	/**
	 * @Method Name : updPrmtAgenRqst
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청관리 수량변경
	 * @param param
	 * @return void
	 */
	public void updPrmtAgenRqst(PrmtAgenRqstVO param);
}

