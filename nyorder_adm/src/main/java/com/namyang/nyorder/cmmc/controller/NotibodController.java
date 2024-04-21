package com.namyang.nyorder.cmmc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.cmmc.service.FileService;
import com.namyang.nyorder.cmmc.service.NotibodService;
import com.namyang.nyorder.cmmc.vo.FileVO;
import com.namyang.nyorder.cmmc.vo.NttVO;
import com.namyang.nyorder.comm.vo.UserInfo;
import com.namyang.nyorder.config.error.exception.BusinessException;
import com.namyang.nyorder.util.StringUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 커뮤니케이션관리 - 공지사항
 * 파일명  : Noti_bodController.java
 * 작성자  : GAIN
 * 작성일  : 2022. 2. 10.
 *
 * 설 명  :
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 10.    GAIN     최조 프로그램 작성
 * 
 ****************************************************/
@Slf4j
@Controller
@RequestMapping("/cmmc")
public class NotibodController {

	@Autowired
	private FileService fileService;
	
	@Autowired 
	private NotibodService notibodService;
	
	@Resource(name="userInfo")
	private UserInfo userInfo;
	
	private Logger logger = LoggerFactory.getLogger(NotibodController.class);

	
	/**
	 * @Method Name : notice_board
	 * @작성일 : 2022. 2. 10.
	 * @작성자 : GAIN
	 * @Method 설명 : 공지사항게시판 화면
	 * @param request
	 * @param VO
	 * @return ModelAndView
	 */
	@RequestMapping(value="/notibod.do", method= RequestMethod.GET)
	public ModelAndView notibod(HttpServletRequest request, NttVO VO) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("cmmc/notibod.view");
		return mv;
	}
	
	/**
	 * @Method Name : selectNotiList
	 * @작성일 : 2022. 2. 10.
	 * @작성자 : GAIN
	 * @Method 설명 : 공지사항게시판 공지사항 리스트 조회
	 * @param request
	 * @param VO
	 * @return List<NttVO>
	 */
	@RequestMapping(value="/selectNotiList.do", method= RequestMethod.POST)
	@ResponseBody
	public List<NttVO> selectNotiList(HttpServletRequest request, NttVO VO)throws Exception {

		return notibodService.selectNotiList(VO);
	}
	/**
	 * @Method Name : deleteNoti
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : GAIN
	 * @Method 설명 : 공지사항게시판 글 논리삭제
	 * @param vo
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value="/updateNotiYn.do", method=RequestMethod.POST)
	@ResponseBody
	public String updateNotiYn(@RequestBody NttVO vo)throws Exception {
			  
		return notibodService.updateNotiYn(vo);
	  }
	/**
	 * @Method Name : saveNotibod
	 * @작성일 : 2022. 3. 4.
	 * @작성자 : GAIN
	 * @Method 설명 : 공지사항 글 저장
	 * @param vo
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value="/saveNotibod.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveNotibod(NttVO vo,	@RequestParam("files") MultipartFile[] files) throws Exception {
		List<NttVO> resultCheck = notibodService.selectNotiList(new NttVO() {{
			setNotiYn("Y");
		}});
		if(resultCheck.size() >= 3 && StringUtils.equals(vo.getNotiYn(), "Y")) {
			if(StringUtil.isNotEmpty(vo.getNttSeq())) {
				Boolean dup = true;
				for(NttVO one : resultCheck) {
					if(StringUtils.equals(one.getNttSeq(), vo.getNttSeq())){
						dup = false;
					}
				}
				if(dup) {
					throw new BusinessException("alert.notibod04");
				}
			}else {
				throw new BusinessException("alert.notibod04");
			}
		}
		
		Boolean fileYn = false;
		vo.setEmplSeq(userInfo.getAdmSeq());
		FileVO fileVO = new FileVO();
		int cnt = 1;
		
		for(MultipartFile  f : files) {
			if(f.getSize() > 0) {
				if(!fileYn && StringUtil.isEmpty(vo.getNttAtclSeq())) {
					int nttAtclSeq = fileService.addNttAtclMst(new FileVO() {{
						setEmplSeq(userInfo.getAdmSeq());
					}});
					vo.setNttAtclSeq(String.valueOf(nttAtclSeq));
					fileYn = true;
				}else {
					fileYn = true;
				}
				fileVO.setNttAtclSeq(Integer.parseInt(vo.getNttAtclSeq()));
				fileVO.setEmplSeq(vo.getEmplSeq());
				fileVO.setOrdr(cnt);
				
				fileService.upload(f, fileVO);
			}
			cnt++;
		}
		if(StringUtil.isEmpty(vo.getNttSeq())) {
			return notibodService.insertNotibod(vo);
		}else {
			return notibodService.updateNotibod(vo);
		}
	}

}
