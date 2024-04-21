package com.namyang.nyorder.cmmc.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namyang.nyorder.cmmc.dao.RcbodMapper;
import com.namyang.nyorder.cmmc.vo.RcbodVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.StringUtil;

import lombok.extern.slf4j.Slf4j;



/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 커뮤니케이션관리 - 자료실
 * 파일명  : RcbodServiceImpl.java
 * 작성자  : GAIN
 * 작성일  : 2022. 2. 28.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 28.    GAIN     최조 프로그램 작성
 *
 ****************************************************/
@Slf4j
@Service
public class RcbodServiceImpl implements RcbodService{

	@Autowired
	private RcbodMapper rcbodMapper;

	@Resource(name="userInfo")
	private UserInfo userInfo;
	
	/**
	 * @Method Name : selectRcbodList
	 * @작성일 : 2022. 2. 28.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션관리 - 자료실 리스트 조회
	 * @param vO
	 * @return Map<String,Object>
	 */	
	public List<RcbodVO> selectRcbodList(RcbodVO VO) {
		return rcbodMapper.selectRcbodList(VO);
	}

	/* 자료실 게시판 해당 게시판 글 조회 */
	public List<RcbodVO> selectRcbodDtlList(RcbodVO param) {
		return rcbodMapper.selectRcbodDtlList(param);
	}

	/**
	 * 저장
	 */
	@Transactional
	public String saveRcbod(RcbodVO vo) throws Exception {
		vo.setAdmSeq(userInfo.getAdmSeq());
		// 마스터 등록
		if(StringUtil.isEmpty(vo.getRcbodSeq())) {
			rcbodMapper.insertRcbodMst(vo);
		}
		int ordr = 1;
		// 상세 초기화
		rcbodMapper.delRcbodDtl(vo);
		for(RcbodVO one : vo.getContList()) {
			one.setAdmSeq(userInfo.getAdmSeq());
			one.setOrdr(ordr);
			one.setRcbodSeq(vo.getRcbodSeq());
			if(!StringUtil.isEmpty(one.getAgenCd())) {
				rcbodMapper.insertRcbodDtl(one);
				ordr++;
			}
		}
		return "저장되었습니다";
	}

	/**
	 * 대리점 코드 리스트
	 */
	public List<RcbodVO> selectAgenList(RcbodVO vo) {
		return rcbodMapper.selectAgenList(vo);
	}

	/**
	 * 삭제
	 */
	@Transactional
	public String delRcbodList(List<RcbodVO> list) {
		for(RcbodVO param : list) {
			param.setAdmSeq(userInfo.getAdmSeq());
			rcbodMapper.delRcbod(param);
		}
		return "정상적으로 삭제되었습니다";
	}
	
}
