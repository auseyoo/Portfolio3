package com.namyang.nyorder.cmmc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.namyang.nyorder.cmmc.vo.FileVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : File Service Interface
 * 파일명  : FileService.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 8.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 8.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
public interface FileService {
	
	/**
	 * @Method Name : upload
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 업로드
	 * @param files
	 * @return
	 * @throws Exception int
	 */
	public int upload (MultipartFile file, FileVO fileVO) throws Exception;

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
	 * @Method Name : selectAtclDtlList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 단건 조회 
	 * @param param
	 * @return FileVO
	 */
	public FileVO selectAtclDtlList(FileVO param);
	
	/**
	 * @Method Name : addNttAtclMst
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 그룹 생성
	 * @param param
	 * @return Integer
	 */
	public Integer addNttAtclMst ( FileVO param);

	/**
	 * @Method Name : rmvAtclDtl
	 * @작성일 : 2022. 3. 11.
	 * @작성자 : YESOL
	 * @Method 설명 : 단건 파일 삭제
	 * @param param
	 * @return String
	 */
	public String rmvAtclDtl(FileVO param);
	
}
