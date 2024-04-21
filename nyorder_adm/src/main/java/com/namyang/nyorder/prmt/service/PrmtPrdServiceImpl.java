package com.namyang.nyorder.prmt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.prmt.dao.PrmtPrdMapper;
import com.namyang.nyorder.prmt.vo.PrmtPrdVO;
import com.namyang.nyorder.prmt.vo.PrmtRqstMngVO;
import com.namyang.nyorder.util.ExcelReadUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 자재 관리 
 * 파일명  : PrmtPrdServiceImpl.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 7.        JUNGAE        최조 프로그램 작성
 *
 */
@Service
public class PrmtPrdServiceImpl implements PrmtPrdService {
	
	@Autowired
	private PrmtPrdMapper prmtPrdMapper;
	
	@Autowired
	private ExcelReadUtil excelReadUtil;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Value(value = "${file.upload.maxSize}")
	private String maxSize;

	@Value(value = "${file.upload.path}")
	private String path;
	
	@Override
	public List<PrmtPrdVO> selectPrmtPrdList(PrmtPrdVO param) {
		param.setRqstMonth(param.getRqstMonth().replace("-", ""));
		List<PrmtPrdVO> rsList = prmtPrdMapper.selectPrmtPrdList(param);
		return rsList;
	}
	
	@Override
	@Transactional
	public String savePrmtPrd(List<PrmtPrdVO> list) {
		
		// 기존에 등록된 판촉물 요청 자재 삭제처리
		String selYM = list.get(0).getRqstMonth();
		PrmtPrdVO delParam = new PrmtPrdVO();
		delParam.setRqstMonth(selYM);
		delParam.setEmplSeq(userInfo.getAdmSeq());
		prmtPrdMapper.delPrmtPrd(delParam);
		
		list.remove(0);
		
		// 판촉물 요청 자재 등록
		if(list.size() > 0) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("emplSeq", userInfo.getAdmSeq());
			params.put("list", list);
			prmtPrdMapper.insPrmtPrd(params);				
		}
		
		return "정상적으로 저장되었습니다.";
	}
	
	@Override
	@Transactional
	public String copyPrmtPrd(PrmtPrdVO param) {
		param.setEmplSeq(userInfo.getAdmSeq());
		prmtPrdMapper.insCopyPrmtPrd(param);
		
		return "정상적으로 복사되었습니다.";
	}

	@Override
	public Map<String, Object> uploadPrmtPrd(PrmtPrdVO param, MultipartFile paramFile) throws Exception {

		// 엑셀 헤더 정보 구성
		String[] headerInfo = {"제품코드", "소요량등록"};
		
		// 엑셀 파일을 읽어 데이터 가져오기
		List<HashMap<String, Object>> list = excelReadUtil.uploadExcel(paramFile, headerInfo, 2);
		//System.out.println(list);
		
		// 추출한 데이터 db저장하기
		ArrayList<String> sFailedPrdCd = new ArrayList<String>();
		String rqstMonth = param.getRqstMonth().replace("-", "");
		
		for(int i=0;i<list.size();i++) {
			Map<String, Object> info = list.get(i);
			
			if(!(info.get("소요량등록").equals("1") || info.get("소요량등록").equals("2")))
			{
				// 소요량등록이 1 또는 2가 아닐때 실패케이스로 처리
				sFailedPrdCd.add(info.get("제품코드").toString());
				continue;
			}
			
			PrmtPrdVO paramInfo = new PrmtPrdVO();
			paramInfo.setPrdSapCd(info.get("제품코드").toString());
			paramInfo.setPrmtRegFlag(info.get("소요량등록").toString());
			
			// 제품코드로 제품정보 조회하기
			PrmtPrdVO prdInfo = null; 
			prdInfo = prmtPrdMapper.selectPrmtPrdSeq(paramInfo);
			
			if(prdInfo == null) {
				// 제품코드가 제품정보에 존재하지 않을때 실패케이스로 처리
				sFailedPrdCd.add(info.get("제품코드").toString());
				continue;
			}

			// 판촉물 요청 자재로 등록, 해제 처리하기
			PrmtPrdVO paramMerInfo = new PrmtPrdVO();
			paramMerInfo.setPuchSeq(prdInfo.getPuchSeq());
			paramMerInfo.setPrdSeq(prdInfo.getPrdSeq());
			paramMerInfo.setRqstMonth(rqstMonth);
			paramMerInfo.setPrmtRegFlag(info.get("소요량등록").toString());
			paramMerInfo.setEmplSeq(userInfo.getAdmSeq());
			prmtPrdMapper.merUploadedPrmtPrd(paramMerInfo);
		}
		
		int iTotCnt = list.size();
		int iFailCnt = sFailedPrdCd.size();
		int iSuccCnt = (list.size() - iFailCnt);
		String sCompFlag = (iFailCnt == 0 ? "Y" : "N");
		String sFailMsg = "";
		if(iFailCnt > 0) {
			sFailMsg = sFailedPrdCd.get(0);
			
			if(iFailCnt > 1) {
				sFailMsg += "외 " + (iFailCnt - 1) + "건";
			}
		}
		
		Map<String, Object> result 	= new HashMap<String, Object>();
		result.put("totCnt", iTotCnt);
		result.put("succCnt", iSuccCnt);
		result.put("failCnt", iFailCnt);
		result.put("failMsg", sFailMsg);
		result.put("compFlag", sCompFlag);
		return result;
	}
}
