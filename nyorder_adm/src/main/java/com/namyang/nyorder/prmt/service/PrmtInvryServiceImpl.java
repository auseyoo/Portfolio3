package com.namyang.nyorder.prmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.dao.PrmtInvryMapper;
import com.namyang.nyorder.prmt.dao.PrmtRqstMngMapper;
import com.namyang.nyorder.prmt.vo.PrmtAgenRqstVO;
import com.namyang.nyorder.prmt.vo.PrmtInvryVO;
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
public class PrmtInvryServiceImpl implements PrmtInvryService {
	
	@Autowired
	private PrmtInvryMapper prmtInvryMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
		
	@Override
	public List<PrmtInvryVO> selectPrmtInvryList(PrmtInvryVO param) {
		param.setStdrDt(param.getStdrDt().replace("-", ""));
		List<PrmtInvryVO> rsList = prmtInvryMapper.selectPrmtInvryList(param);
		return rsList;
	}
}
