package com.namyang.nyorder.prmt.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.dao.PrmtRqstMngMapper;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 기간 관리 
 * 파일명  : PrmtRqstMngServiceImpl.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 4.        JUNGAE        최조 프로그램 작성
 *
 */
@Service
public class PrmtRqstMngServiceImpl implements PrmtRqstMngService {
	
	@Autowired
	private PrmtRqstMngMapper prmtRqstMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<PrmtRqstMngVO> selectPrmtRqstMngList(PrmtRqstMngVO param) {
		param.setDvyfgRqstMonth(param.getDvyfgRqstMonth().replace("-", ""));
		List<PrmtRqstMngVO> rsList = prmtRqstMngMapper.selectPrmtRqstMngList(param);
		return rsList;
	}
	
	@Override
	@Transactional
	public String savePrmtRqstMng(List<PrmtRqstMngVO> list) {
		for(PrmtRqstMngVO param : list) {
			param.setDvyfgRqstMonth(param.getDvyfgRqstMonth().replace("-", ""));
			param.setEmplSeq(userInfo.getAdmSeq());
			prmtRqstMngMapper.merPrmtRqstMng(param);
		}
		return "정상적으로 저장되었습니다.";
	}
	
	@Override
	public PrmtRqstMngVO selectDvyfgRqstMonth(PrmtRqstMngVO param) {
		
		param.setDvyfgRqstMonth(param.getDvyfgRqstMonth().replace("-", ""));
		
		if(StringUtil.isEmpty(param.getSaleCd()))
			param.setSaleCd("SI");
		
		return prmtRqstMngMapper.selectDvyfgRqstMonth(param);
	}
}
