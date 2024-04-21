package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 판촉관리 > 판촉사원관리
 * 파일명  : PrmtEmplMngController.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.    kjin     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class PrmtHopMngVO extends CommVO {
	
	private String prmtHopSeq; 
	private String prdSeq;
	private String prdSapCd; 
	private String prdNm;
	private String puchSeq;
	private String prmtType;
	private String prmtTypeNm;
	private String lcls;
	private String lclsNm; 
	private String mcls;
	private String mclsNm;
	private String scls;
	private String sclsNm;
	private String dcls;
	private String dclsNm; 
	private String hop1;
	private String hop2;
	private String hop3;
	private String hop4;
	private String hop5;
	private String hop6;
	private String hop7;
	
}
