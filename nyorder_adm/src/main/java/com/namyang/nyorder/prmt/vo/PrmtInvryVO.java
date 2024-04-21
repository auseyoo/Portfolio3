package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrmtInvryVO extends CommVO  {
	
	private String invrySeq;
	private String agenSeq;
	private String agenCd;
	private String agenNm;
	private String prdDtlSeq;
	private String prdSeq;
	private String prdCd;
	private String prdNm;
	private String stdrDt;
	private String faltQty;
	private String yestdBoxQty;
	private String yestdIddyQty;
	private String wrhsBoxQty;
	private String wrhsIddyQty;
	private String dlvyBoxQty;
	private String dlvyIddyQty;
	private String invryChgBoxQty;
	private String invryChgIddyQty;
	private String invryBoxQty;
	private String invryIddyQty;

	private String srcPrdNm;
	private String srcPrdCd;
}
