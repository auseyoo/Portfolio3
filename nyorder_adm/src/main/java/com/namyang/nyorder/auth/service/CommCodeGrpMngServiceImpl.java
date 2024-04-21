package com.namyang.nyorder.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.auth.dao.CommCodeGrpMngMapper;
import com.namyang.nyorder.auth.vo.CommCodeGrpMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드그룹 관리 
 * 파일명  : CommCodeGrpMngServiceImpl.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
@Service
public class CommCodeGrpMngServiceImpl implements CommCodeGrpMngService {
	
	@Autowired
	private CommCodeGrpMngMapper commCodeGrpMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<CommCodeGrpMngVO> commCodeGrpMngList(CommCodeGrpMngVO param) {
		
		List<CommCodeGrpMngVO> rsList = commCodeGrpMngMapper.commCodeGrpMngList(param);
		return rsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> commCodeGrpMngUpd(Map<String, Object> param) {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		
		int updateCnt = 0;
		if (StringUtil.isNotEmpty(param.get("updateRows")))
		{
			String updateList = StringUtil.nvl(param.get("updateRows"));
			List<Map<String, Object>> list = null;
			list = JsonUtils.fromJson(updateList, List.class);
			
			if (StringUtil.isNotEmpty(list))
			{
				for (Map<String, Object> map : list)
				{
					updateCnt += commCodeGrpMngMapper.commCodeGrpMngUpd(map);
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
}
