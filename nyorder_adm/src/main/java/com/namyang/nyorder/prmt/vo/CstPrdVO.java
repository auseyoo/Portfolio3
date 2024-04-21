package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 애음자 제품 VO
 * 파일명  : CstPrdVO.java
 * 작성자  : YESOL
 * 작성일  : 2022. 1. 28.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 1. 28.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class CstPrdVO extends CommVO {
	private String cstPrdSeq;
	private String agenSeq;
	private String areaSeq;
	private String cstSeq;
	private String prdSeq;
	private String ptrmEmplSeq;
	private String prtmRmk;
	private String untpc;
	private String prtmDt;
	private String inptDt;
	private String cntrMonth;
	private String hop;
	private String hopUntpc;
	private String stpgYn;
	private String stpgDt;
	private String stpgRson;
	private String dlvSecCd;
	private String cstBefCd;
	private String prdBefCd;
	
	private String cstNm;
	private String mobNo;
	private String prdNm;
	private String statDt;
	private String endDt;
	
}
