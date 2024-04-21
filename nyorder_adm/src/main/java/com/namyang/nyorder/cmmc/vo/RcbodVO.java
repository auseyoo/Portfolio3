package com.namyang.nyorder.cmmc.vo;

import java.util.List;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 자료실
 * 파일명  : RcbodVO.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 11.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 11.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class RcbodVO  extends CommVO {
	//자료실
	private String rcbodSeq;
	private String rcbodSub;
	private String rcbodDtlSeq;
	private String invc;
	private String prdNm;
	private Integer qty;
	private String consge;
	private String addr;
	private int ordr;
	
	private List<RcbodVO> contList;
	
	private String srcType;
	private String srcKeyword;
	private String crudMode; 
}
