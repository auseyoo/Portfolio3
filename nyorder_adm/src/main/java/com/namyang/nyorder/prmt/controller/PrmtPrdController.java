package com.namyang.nyorder.prmt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.PrdClsVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.YamlPropertySourceFactory;
import com.namyang.nyorder.prmt.service.PrmtPrdService;
import com.namyang.nyorder.prmt.vo.PrmtPrdVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 판촉물 요청 자재 관리
 * 파일명  : PrmtPrdController.java
 * 작성자  : JUNGAE
 * 작성일  : 2022. 3. 7.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 7.        JUNGAE		최조 프로그램 작성
 *
 */
@Slf4j
@Controller
@PropertySource(value = "classpath:config/file-config.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class PrmtPrdController {
	
	@Autowired
	private PrmtPrdService prmtPrdService;
	
	@Autowired
	private CommCodeService commCodeService;
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Value(value = "${file.upload.maxSize}")
	private String maxSize;
	
	@Value(value = "${file.upload.path}")
	private String path;
	
	/**
	 * @Method Name : prmtPrd
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 관리 진입
	 * @param mv
	 * @return
	 */
	@GetMapping(value="/prmt/prmtPrd.do")
	public ModelAndView prmtRqstMng(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prmt/prmtPrd");
		mv.addObject("uploadMaxSize", maxSize);
		mv.addObject("lclsList", commCodeService.selectPrdCls(new PrdClsVO()));
		return mv;
	}
	
	/**
	 * @Method Name : selectPrmtPrdList
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 조회
	 * @param param
	 * @return List<CommCodeVO>
	 */
	@RequestMapping(value="/prmt/selectPrmtPrdList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<PrmtPrdVO> selectPrmtPrdList(PrmtPrdVO param, HttpServletRequest request) {
		param.setAgenSeq(userInfo.getAdmSeq());
		return prmtPrdService.selectPrmtPrdList(param);
	}
	
	/**
	 * @Method Name : savePrmtPrd
	 * @작성일 : 2022. 3. 7.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 여부 저장
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/savePrmtPrd.do")
	@ResponseBody
	public String savePrmtPrd(@RequestBody List<PrmtPrdVO> list, HttpServletRequest request) {
		return prmtPrdService.savePrmtPrd(list);
	}
	
	/**
	 * @Method Name : copyPrmtPrd
	 * @작성일 : 2022. 3. 8.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 전월복사
	 * @param param
	 * @param request
	 * @return
	 */
	@PostMapping(value="/prmt/copyPrmtPrd.do")
	@ResponseBody
	public String copyPrmtPrd(PrmtPrdVO param, HttpServletRequest request) {
		return prmtPrdService.copyPrmtPrd(param);
	}
	
	/**
	 * @Method Name : prmtPrdUploadPop
	 * @작성일 : 2022. 3. 16.
	 * @작성자 : JUNGAE
	 * @Method 설명 : 판촉물 요청 자재 양식업로드
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/prmt/prmtPrdUpload.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> prmtPrdUpload(PrmtPrdVO param, @RequestParam("file") MultipartFile pfile) throws Exception {
		Map<String, Object> result = prmtPrdService.uploadPrmtPrd(param, pfile);
		return result;
	}
}
