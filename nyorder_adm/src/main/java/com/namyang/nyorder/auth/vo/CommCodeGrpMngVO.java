package com.namyang.nyorder.auth.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 공통코드그룹 관리 
 * 파일명  : CommCodeGrpMngController.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.    kjin     최조 프로그램 작성
 *
 ****************************************************/
@Getter
@Setter
public class CommCodeGrpMngVO extends CommVO {
	
	private String commSeq;
	private String commGrpCd;
	private String commGrpNm;
	private String commCd;
	private String commNm;
	private String ordr;
	private String commDesc1;
	private String commDesc2;
	private String commDesc3;
	private String commDesc4;
	private String commDesc5;
	private String commDesc6;
	private String useYn;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String updAdmNm;
	
	
}
