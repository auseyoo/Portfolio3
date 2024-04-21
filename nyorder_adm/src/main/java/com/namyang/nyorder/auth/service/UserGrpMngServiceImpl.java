package com.namyang.nyorder.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.auth.dao.UserGrpMngMapper;
import com.namyang.nyorder.auth.vo.UserGrpMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 사용자그룹 권한관리 
 * 파일명  : UserGrpMngServiceImpl.java
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
public class UserGrpMngServiceImpl implements UserGrpMngService {
	
	@Autowired
	private UserGrpMngMapper userGrpMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<UserGrpMngVO> userGrpMngList(UserGrpMngVO param) {
		return userGrpMngMapper.userGrpMngList(param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> userGrpMngUpd(Map<String, Object> param) {
		
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
					 *  type2=Y 이면 HOFF (본사영업사원)
					 *  type3=Y 이면 BHFC (지점영업사원)
					 *  type2=Y 이고 type3=Y 이면 HOFF_BHFC (본사+지점영업사원)
					 */
					String type1 = StringUtil.nvl(map.get("type1"));
					String type2 = StringUtil.nvl(map.get("type2"));
					String type3 = StringUtil.nvl(map.get("type3"));
					String admType = "";
					
					if ("Y".equals(type1))
					{
						admType = "SYS";
					}
					else if ("N".equals(type1) && "Y".equals(type2) && "Y".equals(type3))
					{
						admType = "HOFF_BHFC";
					}
					else if ("Y".equals(type2))
					{
						admType = "HOFF";
					}
					else if ("Y".equals(type3))
					{
						admType = "BHFC";
					}
					
					if (!"".equals(admType))
					{
						map.put("menuAdmType", admType);
						updateCnt += userGrpMngMapper.userGrpMngUpd(map);
					}
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	
}
