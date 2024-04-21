package com.namyang.nyorder.std.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 표준 제품 관리 VO
 * 파일명  : StdPrdMngVO.java
 * 작성자  : YESOL
 * 작성일  : 2022. 1. 11.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 1. 11.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class StdPrdMngDetailVO extends StdPrdMngVO{
	private String prdDtlSeq;
	private String agenSeq;
	private String puchSeq;
	private String prdSeq;
	private String prdDtlCd;
	private String stdPrdNm;
	private String shtnNm;
	private String abrvNm;
	private String saleOrdUseCd;
	private String prdOrdr;
	private String ordOrdr;
	private String stdPrdYn;
	private String stdPrdYnTx;
	private String brcd;
	private String iddyBrcd;
	private String etcBrcd;
	private String suiteNm;
	private String useYn;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String puchCd;
}
