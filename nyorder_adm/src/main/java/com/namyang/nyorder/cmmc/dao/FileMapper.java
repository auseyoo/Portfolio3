package com.namyang.nyorder.cmmc.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.namyang.nyorder.cmmc.vo.FileVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 파일 DAO
 * 파일명  : FileDao.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 10.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 10.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
public interface FileMapper {
	
	/**
	 * @Method Name : addNttAtclMst
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 첨부파일 마스터 등록
	 * @return Integer
	 */
	public Integer addNttAtclMst(FileVO param);

	/**
	 * @Method Name : addNttAtclDtl
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 첨부파일 상세 등록
	 * @param file
	 * @return Integer
	 */
	public Integer addNttAtclDtl(FileVO vo);

	/**
	 * @Method Name : selectAtclList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 목록 조회
	 * @param param
	 * @return List<FileVO>
	 */
	public List<FileVO> selectAtclList(FileVO param);
	
	/**
	 * @Method Name : selectAtclList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 단건 조회
	 * @param param
	 * @return List<FileVO>
	 */
	public FileVO selectAtclDtlList(FileVO param);
	
	/**
	 * @Method Name : rmvAtclDtl
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : YESOL
	 * @Method 설명 : 단건 파일 삭제 
	 * @param param
	 * @return Integer
	 */
	public Integer rmvAtclDtl(FileVO param);
}
