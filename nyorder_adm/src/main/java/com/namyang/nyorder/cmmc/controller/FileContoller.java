package com.namyang.nyorder.cmmc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.cmmc.service.FileService;
import com.namyang.nyorder.cmmc.vo.FileVO;
import com.namyang.nyorder.config.error.exception.BusinessException;
import com.namyang.nyorder.util.DownloadFileView;
import com.namyang.nyorder.util.StringUtil;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 파일 Contoller
 * 파일명  : FileContoller.java
 * 작성자  : YESOL
 * 작성일  : 2022. 3. 10.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 10.    YESOL     최조 프로그램 작성
 *
 ****************************************************/
@Controller
public class FileContoller {
	
	@Value(value = "${file.upload.path}")
	private String path;
	
	@Value(value = "${file.sample.path}")
	private String samplePath;
		
	@Autowired
	private FileService fileService;
	 
	/**
	 * @Method Name : selectAtclList
	 * @작성일 : 2022. 3. 10.
	 * @작성자 : YESOL
	 * @Method 설명 : 파일 조회
	 * @param param
	 * @return
	 * @throws Exception List<FileVO>
	 */
	@RequestMapping(value="/file/selectAtclList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<FileVO> selectAtclList(@RequestBody FileVO param) throws Exception {
		return fileService.selectAtclList(param);
	}
	
	
	@RequestMapping(value="/file/downloadFile.do", method= RequestMethod.POST)
	public ModelAndView downloadFiles(@RequestBody FileVO param, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(new DownloadFileView(path), "file", fileService.selectAtclDtlList(param)); 
		return mv;
	}
	
	@RequestMapping(value="/file/rmvAtclDtl.do", method=RequestMethod.POST)
	@ResponseBody
	public String rmvAtclDtl(@RequestBody FileVO param) throws Exception{
		return fileService.rmvAtclDtl(param);
	}
	
	/**
	 * @Method Name : downloadFilesSample
	 * @작성일 : 2022. 3. 21.
	 * @작성자 : kjin
	 * @Method 설명 : 엑셀 양식 다운로드
	 * 				서버에 미리 저장된 파일 다운로드
	 * @param request
	 * @param ui : 파일명 (확장자까지)
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/file/{ui}/downloadFile.do")
	public ModelAndView downloadFilesSample(HttpServletRequest request, @PathVariable String ui) throws Exception {
		
		if ( StringUtil.isEmpty(ui) )
		{
			throw new BusinessException("File.004");
		}
		
		FileVO fv = new FileVO();
		fv.setFileNm(ui);
		fv.setFileLc("");
		fv.setFileExt(ui.substring(ui.lastIndexOf(".") + 1));
		ModelAndView mv = new ModelAndView(new DownloadFileView(samplePath), "file", fv); 
		return mv;
	}
	
	
}
