package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrmtRqstMngVO extends CommVO {
	
	private String prmtRqstSeq;
	private String saleCd;
	private String dvyfgRqstMonth;
	private String agenClsDt;
	private String bhfcClsDt;
	private String hoffClsDt;
	private String dcsnYn;
	private String saleTxt;
}
