package com.namyang.nyorder.cmmc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.cmmc.dao.RqbodMapper;
import com.namyang.nyorder.cmmc.vo.NttVO;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class RqbodServiceImpl implements RqbodService{

	@Autowired
	private RqbodMapper rqbodMapper;

	/**
	 * 요청 게시판 리스트 조회
	 */
	@Override
	public Map<String, Object> selectRqbodList(NttVO VO) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", rqbodMapper.selectRqbodList(VO));
		return result;
	}

	/**
	 * 확인/미확인 업데이트
	 */
	@Override
	public String updateCfmYn(NttVO VO) {
		VO.setNttSeq(VO.getNttSeq());
		
		int result = rqbodMapper.updateCfmYn(VO);
		return "확인되었습니다";
	}

	
	
}
