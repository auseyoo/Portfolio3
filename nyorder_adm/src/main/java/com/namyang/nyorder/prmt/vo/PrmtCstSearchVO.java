package com.namyang.nyorder.prmt.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : PrmtCstSearchVO.java
 * 작성자  : YESOL
 * 작성일  : 2022. 2. 21.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 21.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class PrmtCstSearchVO extends CstPrdVO{
	private String cstPrtmSeq;
	private String cstPrdSeq;
	private String agenSeq;
	private String areaSeq;
	private String cstSeq;
	private String prdSeq;
	private String ptrmSecCd;
	private String ptrmSecNm;
	private String ptrmPymDt;	// 지급일
	private String ptrmPymSeq;	// 지급인(번호)
	private String ptrmPymNm;	// 지급인(이름)
	
	private String prtmSeq;
	private String prtmQty;
	private String prtmCt;
	private String prtmCstCt;
	
	private String prtmRmk;
	private String joinDt;
	
	private String weekQty;
	private String areaNm;
	private String emplNm;
	private String cntrCstStr;
	private String endDt;
	private String statDt;

	private String srcType;
	private String srcKeyword;
	private String srcMonth;
}
