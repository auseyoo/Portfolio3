package com.namyang.nyorder.prmt.service;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.comm.vo.CommCodeVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplHisVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 판촉사원관리
 * 파일명  : PrmtEmplMngService.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
public interface PrmtEmplMngService {
	
	/**
	 * @Method Name : getBhfcList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 지점 리스트 가져오기
	 * @return
	 */
	public List<Map<String, Object>> getBhfcList();
	
	/**
	 * @Method Name : prmtEmplMngList
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 권한관리 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtEmplMngVO> prmtEmplMngList(PrmtEmplMngVO param);
	
	/**
	 * @Method Name : selectEmplSecCommCodeList
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 :  판촉사원구분코드 가져오기
	 * @return
	 */
	public List<CommCodeVO> selectEmplSecCommCodeList();
	
	/**
	 * 
	 * @Method Name : prmtEmplMngSave
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 등록
	 * @param param
	 * @return
	 */
	public Map<String, Object> prmtEmplMngSave(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Method Name : getPrmtEmplInfo
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public PrmtEmplMngVO getPrmtEmplInfo(Map<String, Object> param);
	
	/**
	 * 
	 * @Method Name : getPrmtEmplHisList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세 근무이력
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<PrmtEmplHisVO> getPrmtEmplHisList(Map<String, Object> param);
	
	/**
	 * @Method Name : prmtEmplDtlUpt
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세 저장
	 * @param param
	 * @return
	 */
	public Map<String, Object> prmtEmplDtlUpt(Map<String, Object> param) throws Exception;
	
	
}