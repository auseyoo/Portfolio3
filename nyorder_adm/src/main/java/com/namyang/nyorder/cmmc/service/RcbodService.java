package com.namyang.nyorder.cmmc.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.cmmc.vo.RcbodVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 커뮤니케이션관리 - 자료실
 * 파일명  : RcbodService.java
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
public interface RcbodService {

	/**
	 * @Method Name : selectRcbodList
	 * @작성일 : 2022. 2. 28.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 리스트 조회
	 * @param vO
	 * @return List<RcbodVO>
	 */
	public List<RcbodVO> selectRcbodList(RcbodVO vO);

	/**
	 * @Method Name : selectRcbodDtlList
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 내용 조회
	 * @param vO
	 * @return List<RcbodVO>
	 */
	public List<RcbodVO> selectRcbodDtlList(RcbodVO VO);

	/**
	 * @Method Name : saveRcbod
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 저장
	 * @param list
	 * @return String
	 */
	public String saveRcbod(RcbodVO vo) throws Exception;

	/**
	 * @Method Name : selectAgenList
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 대리점 코드 리스트
	 * @param vO
	 * @return Map<String,Object>
	 */
	public List<RcbodVO> selectAgenList(RcbodVO vO);

	/**
	 * @Method Name : updateRcbod
	 * @작성일 : 2022. 3. 2.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 - 삭제
	 * @param list
	 * @return String
	 */
	public String delRcbodList(List<RcbodVO> list);

	

}
