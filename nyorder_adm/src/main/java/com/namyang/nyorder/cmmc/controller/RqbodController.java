package com.namyang.nyorder.cmmc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.cmmc.service.RcbodService;
import com.namyang.nyorder.cmmc.service.RqbodService;
import com.namyang.nyorder.cmmc.vo.NttVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/cmmc")
public class RqbodController {

	
	@Autowired 
	RqbodService rqbodService;
	
	@Resource
	UserInfo userInfo;
	
	private Logger logger = LoggerFactory.getLogger(RqbodController.class);

	/**
	 * @Method Name : rqbod
	 * @작성일 : 2022. 2. 28.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션 - 요청 게시판 화면 띄우기
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value="/rqbod.do", method= RequestMethod.GET)
	public ModelAndView rqbod(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("cmmc/rqbod.view");
		return mv;
	}
	/**
	 * @Method Name : selectRqbodList
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 커뮤니케이션 - 요청 게시판 리스트 조회
	 * @param VO
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="/selectRqbodList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectRqbodList(NttVO vo, HttpServletRequest request) {
		vo.setAdmSeq(userInfo.getAdmSeq());
		return rqbodService.selectRqbodList(vo);
	}
	
	/**
	 * @Method Name : updateCfmYn
	 * @작성일 : 2022. 3. 3.
	 * @작성자 : GAIN
	 * @Method 설명 : 확인/미확인 상태 업데이트
	 * @param VO
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value="/updateCfmYn.do", method=RequestMethod.POST)
	@ResponseBody
	public String updateCfmYn(@RequestBody NttVO VO)throws Exception {
		return rqbodService.updateCfmYn(VO);
	}
	
}
