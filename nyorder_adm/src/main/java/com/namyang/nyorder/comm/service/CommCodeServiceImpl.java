package com.namyang.nyorder.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namyang.nyorder.comm.dao.CommCodeMapper;
import com.namyang.nyorder.comm.vo.CommCodeVO;
import com.namyang.nyorder.comm.vo.PrdClsVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통 코드 관리 Service Implement
 * 파일명  : CommCodeServiceImpl.java
 * 작성자  : YESOL
 * 작성일  : 2021. 12. 31.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2021. 12. 31.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Service
public class CommCodeServiceImpl implements CommCodeService {

	@Autowired
	CommCodeMapper commCodeMapper;
	
	/**
	 *	공통 코드 리스트 조회
	 */
	public List<CommCodeVO> selectCommCodeList(CommCodeVO param) {
		return commCodeMapper.selectCommCodeList(param);
	}

	/**
	 * 제품분류조회
	 */
	public List<PrdClsVO> selectPrdCls(PrdClsVO param) {
		return commCodeMapper.selectPrdCls(param);
	}
	

}
