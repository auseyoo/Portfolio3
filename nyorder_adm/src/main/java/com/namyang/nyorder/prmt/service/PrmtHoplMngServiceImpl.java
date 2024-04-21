package com.namyang.nyorder.prmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.prmt.dao.PrmtHopMngMapper;
import com.namyang.nyorder.prmt.vo.PrmtEmplHisVO;
import com.namyang.nyorder.prmt.vo.PrmtHopMngVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.error.exception.BusinessException;
import com.namyang.nyorder.util.CommonUtil;
import com.namyang.nyorder.util.DefaultConstants;
import com.namyang.nyorder.util.JsonUtils;
import com.namyang.nyorder.util.NumberUtil;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 팝촉홉수관리
 * 파일명  : PrmtHopMngServiceImpl.java
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
public class PrmtHoplMngServiceImpl implements PrmtHopMngService {
	
	@Autowired
	private PrmtHopMngMapper prmtHopMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<PrmtHopMngVO> prmtHopMngList(PrmtHopMngVO param) {
		
		List<PrmtHopMngVO> rsList = prmtHopMngMapper.prmtHopMngList(param);
		return rsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> prmtHopMngSave(Map<String, Object> param) throws Exception {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		int updateCnt = 0;
		
		List<Map<String, Object>> list;
		if (StringUtil.isNotEmpty(param.get("updateRows")))
		{
			list = JsonUtils.fromJson(StringUtil.nvl(param.get("updateRows")), List.class);
			
			if (StringUtil.isNotEmpty(list))
			{
				for (Map<String, Object> map : list)
				{
					map.put("regSeq", StringUtil.nvl(userInfo.getAdmSeq()));
					map.put("updSeq", StringUtil.nvl(userInfo.getAdmSeq()));
					
					if ("".equals(StringUtil.nvl(map.get("prmtHopSeq"))))
					{
						updateCnt += prmtHopMngMapper.insertPrmtHop(map);
					}
					else
					{
						updateCnt += prmtHopMngMapper.updatePrmtHop(map);
					}
				}
				result.put("listCnt", list.size());
			}
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	@Override
	public Map<String, Object> excelUpload(List<HashMap<String, Object>> list) throws Exception {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		int updateCnt = 0;
		int failCnt = 0;
		int totalCnt = 0;
		boolean rs = false;
		boolean isPrd = false;
		String noticePrdSapCd = "";
		
		// 제품코드 전체 리스트 조회
		List<Map<String, Object>> prdList = prmtHopMngMapper.selectPrdMst(null);
		if (StringUtil.isNotEmpty(prdList))
		{
			String p_prdSapCd = "";
			String p_prmtType = "";
			String p_hop1 = "";
			String p_hop2 = "";
			String p_hop3 = "";
			String p_hop4 = "";
			String p_hop5 = "";
			String p_hop6 = "";
			String p_hop7 = "";
			
			for (HashMap<String, Object> map : list)	// 엑셀의 리스트
			{
				p_prdSapCd = StringUtil.nvl(map.get("prdSapCd"));
				p_prmtType = StringUtil.nvl(map.get("prmtType"));
				p_hop1 = StringUtil.nvl(map.get("hop1"));
				p_hop2 = StringUtil.nvl(map.get("hop2"));
				p_hop3 = StringUtil.nvl(map.get("hop3"));
				p_hop4 = StringUtil.nvl(map.get("hop4"));
				p_hop5 = StringUtil.nvl(map.get("hop5"));
				p_hop6 = StringUtil.nvl(map.get("hop6"));
				p_hop7 = StringUtil.nvl(map.get("hop7"));
				
				if ("".equals(p_prdSapCd))	// 제품코드 null 체크
				{
					continue;
				}
				else	// 제품코드 null 이 아닌거 총 갯수로 구함
				{
					totalCnt++;
				}
				
				if ( !DefaultConstants.PRD_TYPES.contains(p_prmtType) )	// 판촉구분 값 체크
				{
					throw new BusinessException("alert.prmtExcUpld06");
				}
				
				// 홉수 숫자체크 및 자리수 체크
				if ( !NumberUtil.isNumLength(p_hop1, 8)
						|| !NumberUtil.isNumLength(p_hop2, 8)
						|| !NumberUtil.isNumLength(p_hop3, 8)
						|| !NumberUtil.isNumLength(p_hop4, 8)
						|| !NumberUtil.isNumLength(p_hop5, 8)
						|| !NumberUtil.isNumLength(p_hop6, 8)
						|| !NumberUtil.isNumLength(p_hop7, 8)
						)
				{
					throw new BusinessException("alert.prmtExcUpld07");
				}
				
				isPrd = false;
				String prmtHopSeq = "";
				String prdSapCd = "";
				String prmtType = "";
				// 엑셀의 제품코드와 제품마스터 테이블의 제품코드와 유효한지 체크
				for (Map<String, Object> prdMap : prdList)
				{
					prmtHopSeq = StringUtil.nvl(prdMap.get("prmtHopSeq"));
					prdSapCd = StringUtil.nvl(prdMap.get("prdSapCd"));
					prmtType = StringUtil.nvl(prdMap.get("prmtType"));
					if (prdSapCd.equals(p_prdSapCd) && prmtType.equals(p_prmtType))	// 유효한 제품코드 있을시~
					{
						isPrd = true;
						
						map.put("prdSeq", StringUtil.nvl(prdMap.get("prdSeq")));
						map.put("puchSeq", DefaultConstants.PUCH_SEQ_DEFAULT);
						map.put("regSeq", StringUtil.nvl(userInfo.getAdmSeq()));
						map.put("updSeq", StringUtil.nvl(userInfo.getAdmSeq()));
						if ("".equals(StringUtil.nvl(prmtHopSeq)))
						{
							updateCnt += prmtHopMngMapper.insertPrmtHop(map);
						}
						else
						{
							map.put("prmtHopSeq", prmtHopSeq);
							updateCnt += prmtHopMngMapper.updatePrmtHop(map);
						}
						break;
					}
				}
				
				if (!isPrd)		// 제품코드 유효하지 않을 시 카운트 증가
				{
					if (failCnt == 0)	// "alert.prmtExcUpld04" 실패 메세지 처리시 제품코드 첫번째 코드 담기 
					{
						noticePrdSapCd = p_prdSapCd;
					}
					failCnt++;
				}
			}
			
			if (failCnt > 0)	// 유효하지 않은 카운트 갯수를 구한 후 롤백 throw~
			{
				throw new BusinessException("alert.prmtExcUpld04", new String[] {"'" + noticePrdSapCd + "' 외" + failCnt + " 건"});
			}
			else	// 성공시~
			{
				rs = true;
			}
		}
		else
		{
			rs = false;
		}
		
		result.put("res", rs);					// 성공여부
		result.put("updateCnt", updateCnt);		// insert update 성공갯수
		result.put("failCnt", failCnt);			// 실패 갯수
		result.put("totalCnt", totalCnt);		// 총갯수 (제품코드 입력된 총 갯수)
		result.put("listCnt", list.size());		// 총갯수 (빈값 포함)
		
		return result;
	}
	
	
	
	
}
