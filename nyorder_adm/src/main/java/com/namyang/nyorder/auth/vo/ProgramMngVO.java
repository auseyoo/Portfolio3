package com.namyang.nyorder.auth.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템 (Admin)
 * 업무명  : 프로그램 권한관리 
 * 파일명  : ProgramMngController.java
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
public class ProgramMngVO extends CommVO {
	
	private String menuId;
	private String menuSeq;
	private String menuParentSeq;
	private String menuNm;
	private String menuDesc;
	private String menuAdmType;
	private String menuOrdr;
	private String menuLv;
	private String menuUrl;
	private String useYn;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String updAdmNm;
	private String sort;
	private String menuFullDethNm;
	
	//private List<ProgramMngVO> children = new ArrayList<ProgramMngVO>();
	
	
}
