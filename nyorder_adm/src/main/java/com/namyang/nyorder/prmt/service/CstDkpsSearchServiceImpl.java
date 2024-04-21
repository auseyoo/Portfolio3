package com.namyang.nyorder.prmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.prmt.dao.CstDkpsSearchMapper;
import com.namyang.nyorder.prmt.vo.CstDkpsSearchVO;
import com.namyang.nyorder.comm.vo.UserInfo;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 애음자 음용 현황조회
 * 파일명  : CstDkpsSearchServiceImpl.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 4.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
@Service
public class CstDkpsSearchServiceImpl implements CstDkpsSearchService {
	
	@Autowired
	private CstDkpsSearchMapper cstDkpsSearchMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<CstDkpsSearchVO> cstDkpsSearchList(CstDkpsSearchVO param) {
		
		List<CstDkpsSearchVO> rsList = cstDkpsSearchMapper.cstDkpsSearchList(param);
		return rsList;
	}
	
	
	
	
}
