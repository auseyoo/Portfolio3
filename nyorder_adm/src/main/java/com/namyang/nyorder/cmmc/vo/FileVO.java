package com.namyang.nyorder.cmmc.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 파일 VO
 * 파일명  : FileVO.java
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
@Getter
@Setter
public class FileVO extends CommVO{
	
	private int nttAtclDtlSeq;
	private int nttAtclSeq;
	private String fileNm;
	private String fileOriNm;
	private String fileLc;
	private String fileExt;
	private Long fileSize;
	private int ordr;
	private String useYn;
	
}
