package com.namyang.nyorder.comm.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuVO extends CommVO{
	
	private String admSeq;
	private String menuSeq;
	private String menuId;
	private String menuNm;
	private String menuParentSeq;
	private String menuUrl;
	private String menuDesc;
	private String menuType;
	private String menuOrdr;
	private String level;
	private String menuCurrent;
	private String menuCurrentGbn;
	private String useYn;
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String parentSeq;
	
	private String roleYn;

	
}
