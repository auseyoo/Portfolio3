package com.namyang.nyorder.prmt.dao;

import java.util.List;
import java.util.Map;

import com.namyang.nyorder.comm.vo.CommCodeVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplHisVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 판촉사원관리
 * 파일명  : PrmtEmplMngMapper.java
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
public interface PrmtEmplMngMapper {
	
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
	 * @작성일 : 2022. 2. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 대리점 사용승인 리스트
	 * @param param
	 * @return
	 */
	public List<PrmtEmplMngVO> prmtEmplMngList(PrmtEmplMngVO param);
	
	/**
	 * @Method Name : selectEmplSecCommCodeList
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사워구분 코드 가져오기
	 * @return
	 */
	public List<CommCodeVO> selectEmplSecCommCodeList();
	
	/**
	 * @Method Name : insertPrmtEmpl
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 등록
	 * @param param
	 * @return
	 */
	public int insertPrmtEmpl(PrmtEmplMngVO param);
	
	/**
	 * @Method Name : insertPrmtEmplHis
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 근무이력 등록
	 * @param param
	 * @return
	 */
	public int insertPrmtEmplHis(PrmtEmplHisVO param);
	
	/**
	 * @Method Name : getPrmtEmplInfo
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세정보
	 * @param param
	 * @return
	 */
	public PrmtEmplMngVO getPrmtEmplInfo(Map<String, Object> param);
	
	/**
	 * @Method Name : getPrmtEmplHisList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세 근무이력
	 * @param param
	 * @return
	 */
	public List<PrmtEmplHisVO> getPrmtEmplHisList(Map<String, Object> param);
	
	/**
	 * @Method Name : updatePrmtEmpl
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 상세 수정
	 * @param param
	 * @return
	 */
	public int updatePrmtEmpl(PrmtEmplMngVO param);
	
	/**
	 * @Method Name : updatePrmtEmplHis
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 근무이력 상세 수정
	 * @param param
	 * @return
	 */
	public int updatePrmtEmplHis(PrmtEmplHisVO param);
	
	/**
	 * @Method Name : updatePrmtEmplEnd
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : kjin
	 * @Method 설명 : 판촉사원 계약종료 N 으로 변경
	 * @param param
	 * @return
	 */
	public int updatePrmtEmplEnd(PrmtEmplMngVO param);
	
	
}
