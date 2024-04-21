package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrmtAgenRqstVO extends CommVO  {
	
	private String saleCd;
	
	private String prmtAgenSeq;
	private String bhfcSeq;
	private String bhfcNm;
	private String agenNm;
	private String puchSeq;
	private String prdSeq;
	private String prmtRqstSeq;
	private String dvyfgRqstMonth;
	private String agenBoxQty;
	private String agenIddyQty;
	private String agenOrdDtm;
	private String bhfcBoxQty;
	private String bhfcIddyQty;
	private String bhfcDcsnDtm;
	private String hoffBoxQty;
	private String hoffIddyQty;
	private String hoffDcsnDtm;
	private String prdSapCd;
	private String prdNm;
	private String freeYn;
	private String faltQty;
	private String iddyOrdYn;
	private String agenQty;
	private String bhfcQty;
	private String hoffQty;
	private String diffQtyAb;
	private String diffQtyAh;
	private String updSeq;
	private String updNm;
	private String updDtm;

	private String srcType;
	private String srcKeyword;
	private String srcPrdNm;
	private String srcPrdCd;
	
	// 수량변경시 지점, 본사 구분값 B:지점, H:본사
	private String updPart;
}
