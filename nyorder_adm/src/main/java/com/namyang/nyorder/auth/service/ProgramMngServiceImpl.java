package com.namyang.nyorder.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.auth.dao.ProgramMngMapper;
import com.namyang.nyorder.auth.vo.ProgramMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 프로그램 권한관리 
 * 파일명  : ProgramMngServiceImpl.java
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
public class ProgramMngServiceImpl implements ProgramMngService {
	
	@Autowired
	private ProgramMngMapper programMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<ProgramMngVO> getGropLevel() {
		return programMngMapper.getGropLevel();
	}
	
	@Override
	public List<ProgramMngVO> getParentMenuList() {
		return programMngMapper.getParentMenuList();
	}
	
	@Override
	public List<ProgramMngVO> programMngList(ProgramMngVO param) {
		
		List<ProgramMngVO> rsList = programMngMapper.programMngList(param);
		return rsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> programMngUpd(Map<String, Object> param) {
		
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
					updateCnt += programMngMapper.programMngUpd(map);
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	@Override
	public Map<String, Object> programMngReg(Map<String, Object> param) {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		
		if (!"".equals(StringUtil.nvl(param.get("pMenuParentSeq"))))
		{
			if ( "0".equals(StringUtil.nvl(param.get("pMenuParentSeq"))))	// 1레벨 등록시
			{
				param.put("pMenuLv", "1");
			}
			else
			{
				param.put("pMenuLv", programMngMapper.getMenuLevel(param));
			}
			
			param.put("pMenuAdmType", "SYS");
			param.put("pMenuOrdr", programMngMapper.getMenuOrdr(param));
			param.put("admSeq", userInfo.getAdmSeq());
			int updateCnt = programMngMapper.programMngReg(param);
			
			result.put("res", true);
			result.put("updateCnt", updateCnt);
		}
		else
		{
			result.put("res", false);
		}
		
		return result;
	}
	
	
}
