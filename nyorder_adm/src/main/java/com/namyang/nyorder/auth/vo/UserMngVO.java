package com.namyang.nyorder.auth.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 사용자그룹 권한관리 
 * 파일명  : UserMngController.java
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
public class UserMngVO {
	
	private String admSeq;
	private String admCd;
	private String bsnGrpCd;
	private String bhfcCd;
	private String bhfcNm;
	private String admNm;
	private String grpCd;
	private String admAuthSeq;
	private String menuAdmType;
	private String type1;
	private String type2;
	private String type3;
	private String useYn;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String updAdmNm;
	
	
}
