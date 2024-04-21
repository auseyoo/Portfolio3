package com.namyang.nyorder.agen.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.agen.dao.AgenUseConfmMapper;
import com.namyang.nyorder.agen.vo.AgenUseConfmVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 대리점 사용승인
 * 파일명  : AgenUseConfmServiceImpl.java
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
public class AgenUseConfmServiceImpl implements AgenUseConfmService {
	
	@Autowired
	private AgenUseConfmMapper agenUseConfmMapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<AgenUseConfmVO> agenUseConfmList(AgenUseConfmVO param) {
		
		List<AgenUseConfmVO> rsList = agenUseConfmMapper.agenUseConfmList(param);
		return rsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> agenUseConfmUpd(Map<String, Object> param) {
		
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
					String agenEmplSeq = StringUtil.nvl(map.get("agenEmplSeq"));
					if (!"".equals(agenEmplSeq))
					{
						String[] agenEmplSeqs = agenEmplSeq.split("-");
						String agenSeq = "";
						String emplSeq = "";
						if (null != agenEmplSeqs && agenEmplSeqs.length > 1)
						{
							agenSeq = agenEmplSeqs[0];
							emplSeq = agenEmplSeqs[1];
							map.put("agenSeq", agenSeq);
							map.put("emplSeq", emplSeq);
							
							String confmYn = StringUtil.nvl(map.get("confmYn"));
							if (!"".equals(confmYn))
							{
								if ("0".equals(confmYn))
								{
									map.put("confmYn", "");
								}
								updateCnt += agenUseConfmMapper.updateConfm(map);
							}
							
							String emplPwd = StringUtil.nvl(map.get("emplPwd"));
							if (!"".equals(emplPwd))
							{
								String encEmplPwd = passwordEncoder.encode(emplPwd);
								map.put("encEmplPwd", encEmplPwd);
								map.put("admSeq", StringUtil.nvl(userInfo.getAdmSeq()));
								
								// 마지막 패스워드 여부 전체 N 으로 변경 후 등록
								agenUseConfmMapper.updateLstPwd(map);
								updateCnt += agenUseConfmMapper.insertPwd(map);
							}
						}
					}
				}
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	
}
