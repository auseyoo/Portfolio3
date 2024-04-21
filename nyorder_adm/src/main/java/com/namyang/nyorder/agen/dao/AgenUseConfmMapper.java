package com.namyang.nyorder.agen.dao;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.agen.vo.AgenUseConfmVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점 사용승인 
 * 파일명  : AgenUseConfmMapper.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.        kjin        최조 프로그램 작성
 *
 */
public interface AgenUseConfmMapper {
	
	/**
	 * @Method Name : agenUseConfmList
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 리스트
	 * @param param
	 * @return
	 */
	public List<AgenUseConfmVO> agenUseConfmList(AgenUseConfmVO param);
	
	/**
	 * @Method Name : updateConfm
	 * @작성일 : 2022. 2. 22.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 여부 저장
	 * @param param
	 * @return
	 */
	public int updateConfm(Map<String, Object> param);
	
	/**
	 * @Method Name : updateLstPwd
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 임시비밀번호 저장 전 마지막 패스워드 여부 업데이트
	 * @param param
	 * @return
	 */
	public int updateLstPwd(Map<String, Object> param);
	
	/**
	 * @Method Name : insertPwd
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 임시비밀번호 저장
	 * @param param
	 * @return
	 */
	public int insertPwd(Map<String, Object> param);
	
	
}
