package com.namyang.nyorder.cmmc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.namyang.nyorder.cmmc.vo.RcbodVO;




/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 커뮤니케이션관리 - 자료실
 * 파일명  : RcbodMapper.java
 * 작성자  : GAIN
 * 작성일  : 2022. 2. 28.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 28.    GAIN     최조 프로그램 작성
 *
 ****************************************************/
@Mapper
public interface RcbodMapper {

	/**
	 * @Method Name : selectRcbodList
	 * @작성일 : 2022. 2. 28.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 리스트 조회
	 * @param vO
	 * @return List<RcbodVO>
	 */
	public List<RcbodVO> selectRcbodList(RcbodVO param);

	/**
	 * @Method Name : selectRcbodDtlList
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 내용 조회
	 * @param param
	 * @return RcbodVO
	 */
	public List<RcbodVO> selectRcbodDtlList(RcbodVO param);

	/**
	 * @Method Name : insertRcbodMst
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 저장	(마스터)
	 * @param param
	 * @return int
	 */
	public int insertRcbodMst(RcbodVO param);
	
	/**
	 * @Method Name : insertRcbodDtl
	 * @작성일 : 2022. 3. 14.
	 * @작성자 : YESOL
	 * @Method 설명 :  커뮤니케이션관리 - 자료실 저장(상세)
	 * @param param
	 * @return int
	 */
	public int insertRcbodDtl(RcbodVO param);

	/**
	 * @Method Name : selectAgenList
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 대리점 코드 리스트 구하기
	 * @param vO
	 * @return List<RcbodVO>
	 */
	public List<RcbodVO> selectAgenList(RcbodVO vO);

	/**
	 * @Method Name : updateRcbodMst
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 행삭제
	 * @param param
	 * @return int
	 */
	public int updateRcbodMst(RcbodVO param);	
	public int updateRcbodDtl(RcbodVO param);

	/**
	 * @Method Name : delRcbod
	 * @작성일 : 2022. 3. 14.
	 * @작성자 : YESOL
	 * @Method 설명 : 자료실 - 마스터 삭제
	 * @param param
	 * @return int
	 */
	public int delRcbod(RcbodVO param);

	/**
	 * @Method Name : delRcbodDtl
	 * @작성일 : 2022. 3. 14.
	 * @작성자 : YESOL
	 * @Method 설명 : 자료실 - 상세 삭제
	 * @param one
	 * @return int
	 */
	public int delRcbodDtl(RcbodVO one);	
	
	
	
}


