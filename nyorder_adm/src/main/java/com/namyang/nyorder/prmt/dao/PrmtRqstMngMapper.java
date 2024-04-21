package com.namyang.nyorder.prmt.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 기간 관리
 * 파일명  : PrmtRqstMngMapper.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.    JUNGAE     최조 프로그램 작성
 *
 ****************************************************/
@Mapper
public interface PrmtRqstMngMapper {
	
	/**
	 * @Method Name : selectPrmtRqstMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 조회
	 * @param param
	 * @return List<PrmtRqstMngVO>
	 */
	public List<PrmtRqstMngVO> selectPrmtRqstMngList(PrmtRqstMngVO param);
	
	/**
	 * @Method Name : merPrmtRqstMng
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 수정
	 * @param param
	 * @return void
	 */
	public void merPrmtRqstMng(PrmtRqstMngVO param);
	
	/**
	 * @Method Name : updPrmtRqstMngDcsn
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 기간 - 본사확정상태 수정
	 * @param param
	 * @return void
	 */
	public void updPrmtRqstMngDcsn(PrmtRqstMngVO param);
	
	/**
	 * @Method Name : selectDvyfgRqstMonth
	 * * @작성일 : 2022. 2. 24.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 소요량 요청월 조회
	 * @param param
	 * @return List<PrmtRqstMngVO>
	 */
	public PrmtRqstMngVO selectDvyfgRqstMonth(PrmtRqstMngVO param);
}