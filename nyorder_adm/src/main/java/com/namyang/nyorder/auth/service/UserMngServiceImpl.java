package com.namyang.nyorder.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.auth.dao.UserMngMapper;
import com.namyang.nyorder.auth.vo.UserMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 사용자그룹 권한관리 
 * 파일명  : UserMngServiceImpl.java
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
public class UserMngServiceImpl implements UserMngService {
	
	@Autowired
	private UserMngMapper userMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<UserMngVO> userMngList(UserMngVO param) {
		return userMngMapper.userMngList(param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> userMngUpd(Map<String, Object> param) {
		
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
					/**
					 *  화면에서 받은 권한정보
					 *  type1=Y 이면 SYS (시스템관리자)
					 *  type2=Y 이면 HOFF_PRMT_EMPL (본사영업사원)
					 *  type3=Y 이면 AGEN_EMPL (지점영업사원)
					 */
					String type1 = StringUtil.nvl(map.get("type1"));
					String type2 = StringUtil.nvl(map.get("type2"));
					String type3 = StringUtil.nvl(map.get("type3"));
					String admType = "";
					if ("Y".equals(type1))
					{
						admType = "SYS";
					}
					else if ("Y".equals(type2))
					{
						admType = "HOFF_PRMT_EMPL";
					}
					else if ("Y".equals(type3))
					{
						admType = "AGEN_EMPL";
					}
					
					if (!"".equals(admType))
					{
						map.put("menuAdmType", admType);
						updateCnt += userMngMapper.userMngUpd(map);
					}
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	
}
