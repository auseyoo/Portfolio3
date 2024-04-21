package com.namyang.nyorder.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.auth.dao.CommCodeMngMapper;
import com.namyang.nyorder.auth.vo.CommCodeMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.error.exception.BusinessException;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통코드 관리 
 * 파일명  : CommCodeMngServiceImpl.java
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
public class CommCodeMngServiceImpl implements CommCodeMngService {
	
	@Autowired
	private CommCodeMngMapper commCodeMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<CommCodeMngVO> commCodeMngList(CommCodeMngVO param) {
		
		List<CommCodeMngVO> rsList = commCodeMngMapper.commCodeMngList(param);
		return rsList;
	}
	
	@Override
	public Map<String, Object> commGrpCodeCnt(Map<String, Object> param) {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		int selCnt = commCodeMngMapper.selectGrpCodeCnt(param);
		
		result.put("selCnt", selCnt);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> commCodeMngUpd(Map<String, Object> param) throws Exception {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		
		List<Map<String, Object>> list;
		int updateCnt = 0;
		int insertCnt = 0;
		int delCnt = 0;
		if (StringUtil.isNotEmpty(param.get("updateRows")))
		{
			list = JsonUtils.fromJson(StringUtil.nvl(param.get("updateRows")), List.class);
			
			if (StringUtil.isNotEmpty(list))
			{
				for (Map<String, Object> map : list)
				{
					map.put("admSeq", StringUtil.nvl(userInfo.getAdmSeq()));
					updateCnt += commCodeMngMapper.updateCommCodeMng(map);
				}
			}
		}
		
		if (StringUtil.isNotEmpty(param.get("addRows")))
		{
			list = JsonUtils.fromJson(StringUtil.nvl(param.get("addRows")), List.class);
			
			if (StringUtil.isNotEmpty(list))
			{
				for (Map<String, Object> map : list)
				{
					int cnt = commCodeMngMapper.selectGrpCodeCnt(map);
					if (cnt > 0)
					{
						throw new BusinessException("Warn.W003");	// 그룹코드 중복시 ajax return message
					}
					
					insertCnt += commCodeMngMapper.insertCommCodeMng(map);
				}
			}
		}
		
		if (StringUtil.isNotEmpty(param.get("removeRows")))
		{
			list = JsonUtils.fromJson(StringUtil.nvl(param.get("removeRows")), List.class);
			
			if (StringUtil.isNotEmpty(list))
			{
				for (Map<String, Object> map : list)
				{
					delCnt += commCodeMngMapper.deleteCommCodeMng(map);
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		result.put("insertCnt", insertCnt);
		result.put("delCnt", delCnt);
		
		return result;
	}
	
}
