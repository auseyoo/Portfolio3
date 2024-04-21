package com.namyang.nyorder.prmt.vo;

import com.namyang.nyorder.comm.vo.CommVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrmtPrdVO extends CommVO {
		
	// 판촉물 소요량 요청 여부 정보
	private String prmtPrdSeq;
	private String rqstMonth;
	private String useYn;
	private String useYnTx;

	// 제품정보
	private String prdSeq;				// 제품 시퀀스
	private String puchSeq;				// 매입처 시퀀스
	private String puchNm;				// 매입처 명
	private String prdSapCd;			// 제품 코드
	private String prdNm;				// 제품명
	private String lcls;				// 대분류
	private String mcls;				// 중분류
	private String scls;				// 소분류
	private String dcls;				// 세분류
	private String lclsNm;				// 대분류명
	private String mclsNm;				// 중분류명
	private String sclsNm;				// 소분류명
	private String dclsNm;				// 세분류명
	private String prmtCd;				// 판촉물구분, 팜스에 미존재
	private String faltCt;				// 포장비, 팜스에 미존재
	private String brcd;				// 바코드(박스)
	private String iddyBrcd;			// 바코드(낱개)
	private String iddyUntYn;			// 낱봉주문
	private String faltQty;				// 내입량(포장수량)
	private String prdStrd;				// 규격
	private String puchYn;				// 메이저 매입처 여부
	private String taxtCd;				// 과세구분
	private String taxtNm;				// 과세구분명
	
	private String ordUseYn;			// 주문 가능 여부(Y/N)
	private String ordUseYnTx;			// 주문 가능 여부(주문가능, 주문불가)
	private String feeUntpc;			// 수수료 단가
	private String iddyUntpc;			// 낱개단가
	private String spprc;				// 공장도가(공급)
	private String vatSpprc;			// 공장도가(VAT)
	private String valdPd;				// 유통기간
	private String prdUnit;				// 단위
	private String rtgdPermTn;			// 반품허용유무
	private String untpcApplDt;			// 단가적용일
	private String shtnNm;				// 제품 약어(남양)
	private String prdType;				// 제품 타입(팜스)
	private String prdTypeNm;			// 제품 타입명
	private String ordPd;				// 주문 리드타임
	private String etcBrcd;				// 기타바코드 (팜스:바코드3)
	
	private String stdPrdNm;			// 제품명
	private String ordChk;				// 주문가능여부
	private String baseChk;				// 표준제품제외여부
	
	private String srcType;
	private String srcKeyword;
	private String srcPrdNm;
	private String srcPrdCd;
	
	private String prmtRegFlag;			// 판촉물 소요 자재 등록여부 1:등록, 2:해제
}
