package com.namyang.nyorder.prmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namyang.nyorder.prmt.dao.PrmtEmplMngMapper;
import com.namyang.nyorder.prmt.vo.PrmtEmplHisVO;
import com.namyang.nyorder.prmt.vo.PrmtEmplMngVO;
import com.namyang.nyorder.comm.vo.CommCodeVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.error.exception.BusinessException;
import com.namyang.nyorder.util.CommonUtil;
import com.namyang.nyorder.util.DateUtil;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉관리 > 판촉사원관리
 * 파일명  : PrmtEmplMngServiceImpl.java
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
public class PrmtEmplMngServiceImpl implements PrmtEmplMngService {
	
	@Autowired
	private PrmtEmplMngMapper prmtEmplMngMapper;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Override
	public List<Map<String, Object>> getBhfcList() {
		return prmtEmplMngMapper.getBhfcList();
	}
	
	@Override
	public List<PrmtEmplMngVO> prmtEmplMngList(PrmtEmplMngVO param) {
		
		List<PrmtEmplMngVO> rsList = prmtEmplMngMapper.prmtEmplMngList(param);
		return rsList;
	}
	
	@Override
	public List<CommCodeVO> selectEmplSecCommCodeList() {
		return prmtEmplMngMapper.selectEmplSecCommCodeList();
	}
	
	@Override
	public Map<String, Object> prmtEmplMngSave(Map<String, Object> param) throws Exception {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		int updateCnt = 0;
		
		param.put("regSeq", StringUtil.nvl(userInfo.getAdmSeq()));
		param.put("updSeq", StringUtil.nvl(userInfo.getAdmSeq()));
		
		PrmtEmplMngVO pemVo = new PrmtEmplMngVO();
		pemVo = CommonUtil.mapToClass(param, pemVo, PrmtEmplMngVO.class);
		updateCnt += prmtEmplMngMapper.insertPrmtEmpl(pemVo);
		
		if (StringUtil.isNotEmpty(pemVo.getPrmtEmplSeq()))
		{
			PrmtEmplHisVO pehVo = new PrmtEmplHisVO();
			pehVo = CommonUtil.mapToClass(param, pehVo, PrmtEmplHisVO.class);
			pehVo.setPrmtEmplSeq(pemVo.getPrmtEmplSeq());
			updateCnt += prmtEmplMngMapper.insertPrmtEmplHis(pehVo);
		}
		else	// PrmtEmplSeq null 이면 롤백 에러처리
		{
			throw new BusinessException("alert.rcbod04");
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	@Override
	public PrmtEmplMngVO getPrmtEmplInfo(Map<String, Object> param) {
		return prmtEmplMngMapper.getPrmtEmplInfo(param);
	}

	@Override
	public List<PrmtEmplHisVO> getPrmtEmplHisList(Map<String, Object> param) {
		return prmtEmplMngMapper.getPrmtEmplHisList(param);
	}
	
	@Override
	public Map<String, Object> prmtEmplDtlUpt(Map<String, Object> param) throws Exception {
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		int updateCnt = 0;
		
		String prmtEmplSeq = StringUtil.nvl(param.get("prmtEmplSeq"));
		String org_cntrDt = StringUtil.nvl(param.get("org_cntrDt")).replaceAll("-", "");			// 기존 계약일
		String org_cntrEndDt = StringUtil.nvl(param.get("org_cntrEndDt")).replaceAll("-", "");		// 기존 계약종료일
		String org_bhfcSeq = StringUtil.nvl(param.get("org_bhfcSeq"));								// 기존 지점
		String cntrDt = StringUtil.nvl(param.get("cntrDt")).replaceAll("-", "");					// 수정된 계약일
		String cntrEndDt = StringUtil.nvl(param.get("cntrEndDt")).replaceAll("-", "");				// 수정된 계약종료일
		String bhfcSeq = StringUtil.nvl(param.get("bhfcSeq"));										// 수정된 지점
		
		if (StringUtil.isEmpty(prmtEmplSeq))
		{
			throw new BusinessException("alert.rcbod04");
		}
		
		param.put("regSeq", StringUtil.nvl(userInfo.getAdmSeq()));
		param.put("updSeq", StringUtil.nvl(userInfo.getAdmSeq()));
		
		PrmtEmplMngVO pemVo = new PrmtEmplMngVO();
		pemVo = CommonUtil.mapToClass(param, pemVo, PrmtEmplMngVO.class);
		PrmtEmplHisVO pehVo = new PrmtEmplHisVO();
		pehVo = CommonUtil.mapToClass(param, pehVo, PrmtEmplHisVO.class);
		
		// 계약일이 만료되지 않은 상태에서 지점 변경 불가
		if (!org_bhfcSeq.equals(bhfcSeq))	// 지점이 바뀌면
		{
			if (!"".equals(org_cntrEndDt))
			{
				int int_org_cntrEndDt = Integer.valueOf(org_cntrEndDt);
				int int_nowDate = Integer.valueOf(DateUtil.getToday(DateUtil.PATTERN_SYSDATE));
				if (int_org_cntrEndDt >= int_nowDate)		// 계약종료일이 현재일보다 크거나 같으면
				{
					throw new BusinessException("Warn.W004");
				}
			}
		}
		
		// 계약일 변경시 판촉사워코드 재발급 (신규계약)
		if (!org_cntrDt.equals(cntrDt))
		{
			prmtEmplMngMapper.updatePrmtEmplEnd(pemVo);				// 기존 계약여부 'N' 으로 업데이트
			
			updateCnt += prmtEmplMngMapper.insertPrmtEmpl(pemVo);
			updateCnt += prmtEmplMngMapper.insertPrmtEmplHis(pehVo);
		}
		else
		{
			updateCnt += prmtEmplMngMapper.updatePrmtEmpl(pemVo);
			updateCnt += prmtEmplMngMapper.updatePrmtEmplHis(pehVo);
		}
		
		result.put("res", true);
		result.put("updateCnt", updateCnt);
		
		return result;
	}
	
	
	
	
}
