package com.namyang.nyorder.agen.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 대리점 사용승인
 * 파일명  : AgenUseConfmController.java
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
public class AgenUseConfmVO extends CommVO {
	
	private String agenSeq;
	private String emplSeq;
	private String agenEmplSeq;
	private String agenCd;
	private String agenNm;
	private String bizNo;
	private String jurNo;
	private String saleCd;
	private String agenPrst;
	private String bizCon;
	private String bizIndu;
	private String zipCd;
	private String addr1;
	private String addr2;
	private String telNo;
	private String prstTelNo;
	private String faxNo;
	private String agenEml;
	private String agenUseYn;
	private String clsYnCd;
	private String clsDe;
	private String giroNo;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String delgStatDt;
	private String delgEndDt;
	private String confmYn;
	private String confmDt;
	private String intrcpDt;
	private String emplPwd;
	
	
}
