package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 판촉관리 > 애음자 음용 현황조회
 * 파일명  : CstDkpsSearchController.java
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
public class CstDkpsSearchVO extends CommVO {
	
	private String cstSeq;			
	private String bhfcSeq;			/*지점 seq*/
	private String bhfcCd;			/*지점 코드*/
	private String bhfcNm;			/*지점 명*/
	private String agenSeq;			/*대리점 seq*/
	private String agenCd;          /*대리점 코드*/
	private String agenNm;          /*대리점 명*/
	private String cstAgenCd;       /*애음자 코드*/
	private String cstNm;           /*애음자 명*/
	private String prmtDt;          /*가입일 (판촉일)*/
	private String prmtEmplSeq;     /*판촉사원 seq*/
	private String prmtEmplCd;      /*판촉사원 코드*/
	private String emplNm;          /*판촉사원 명*/
	private String cstPrdSeq;       /*애음자 제품 seq*/
	private String cntrMonth;       /*계약월*/
	private String stdrQty;         /*기준수량*/
	private String prdNm;           /*계약 제품*/
	private String sumQty;          /*계약 총수량*/
	private String shtnNm;          /*단축명*/
	
	private String sPrmtDt;         /*검색 가입 시작일*/
	private String ePrmtDt;         /*검색 가입 종료일*/
	
	
}
