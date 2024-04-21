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
public class PrmtEmplMngVO extends CommVO {
	
	private String prmtEmplSeq;
	private String prmtEmplCd;
	private String emplNm;
	private String cntrYn;
	private String cntrNm;
	private String telNo;
	private String brthdy;
	private String zipCd;
	private String addr1;
	private String addr2;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	
	// 근무이력에서 조회
	private String prmtEmplHisSeq;
	private String emplSecCd;
	private String emplSecNm;
	private String bhfcSeq;
	private String bhfcNm;
	private String cntrDt;
	private String cntrEndDt;
	private String newHoffHop;
	private String recntrHoffHop;
	private String chpdyCt;
	private String endRmk;
	private String rmk;
	
	
}
