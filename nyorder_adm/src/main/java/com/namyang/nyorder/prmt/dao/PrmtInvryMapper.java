package com.namyang.nyorder.prmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.prmt.vo.PrmtInvryVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점별 판촉물 재고 현황
 * 파일명  : PrmtInvryMapper.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 11.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 11.    JUNGAE     최조 프로그램 작성
 *
 ****************************************************/
@Mapper
public interface PrmtInvryMapper {

	/**
	 * @Method Name : selectPrmtInvryList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 대리점별 판촉물 재고 현황 조회
	 * @param param
	 * @return List<PrmtInvryVO>
	 */
	public List<PrmtInvryVO> selectPrmtInvryList(PrmtInvryVO param);
}

