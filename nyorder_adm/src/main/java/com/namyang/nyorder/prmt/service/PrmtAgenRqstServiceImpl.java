package com.namyang.nyorder.prmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.dao.PrmtAgenRqstMapper;
import com.namyang.nyorder.prmt.dao.PrmtRqstMngMapper;
import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 자재 관리 
 * 파일명  : PrmtPrdServiceImpl.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 7.        JUNGAE        최조 프로그램 작성
 *
 */
@Service
public class PrmtAgenRqstServiceImpl implements PrmtAgenRqstService {
	
	@Autowired
	private PrmtAgenRqstMapper prmtAgenRqstMapper;
	
	@Autowired
	private PrmtRqstMngMapper prmtRqstMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
		
	@Override
	public List<PrmtAgenRqstVO> selectPrmtAgenRqstList(PrmtAgenRqstVO param) {
		param.setSaleCd(param.getSaleCd());
		param.setDvyfgRqstMonth(param.getDvyfgRqstMonth().replace("-", ""));
		List<PrmtAgenRqstVO> rsList = prmtAgenRqstMapper.selectPrmtAgenRqstList(param);
		return rsList;
	}

	@Override
	public String savePrmtAgenRqst(List<PrmtAgenRqstVO> list) {
		for(PrmtAgenRqstVO param : list) {
			param.setEmplSeq(userInfo.getAdmSeq());
			prmtAgenRqstMapper.updPrmtAgenRqst(param);	
		}		
		
		/*
		 * [판촉물 요청 기간 관리] 화면에 표시되는 확정상태 수정하기
		 * : 구분(시,방판)_특정월에 한건 이상의 본사확정수량이 저장되면 "확정상태"를 Y로 설정하기 
		 * */
		if(list.size() > 0) {
			PrmtAgenRqstVO data = list.get(0);
			
			if(data.getUpdPart().equals("H")) {
				PrmtRqstMngVO param = new PrmtRqstMngVO();
				param.setSaleCd(data.getSaleCd());
				param.setDvyfgRqstMonth(data.getDvyfgRqstMonth());
				param.setEmplSeq(data.getEmplSeq());
				prmtRqstMngMapper.updPrmtRqstMngDcsn(param);	
			}	
		}
		
		return "정상적으로 저장되었습니다.";
	}
}
