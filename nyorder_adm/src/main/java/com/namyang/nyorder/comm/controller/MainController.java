package com.namyang.nyorder.comm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.namyang.nyorder.comm.service.LoginService;
import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.MenuVO;
import com.namyang.nyorder.comm.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Resource(name="userInfo")
	UserInfo userInfo;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/comm/main.do")
	public ModelAndView main(ModelAndView mv, HttpServletRequest request, @SessionAttribute("userInfo") AccountVO account) throws Exception {
		loginService.setSessionInfo(account);
		
		log.debug("session info AgenCd :: " + userInfo.getAdmCd() + ", admNm :: " + userInfo.getAdmNm() + ", menuAdmType :: " + userInfo.getMenuAduType());
		
		mv.setViewName("comm/mainList");		
		return mv;
	}
	
	@RequestMapping("/comm/menu/left.do")
	public String login(ModelMap model, HttpServletRequest request, @SessionAttribute("userInfo") AccountVO account) throws Exception {
				
		String reqUri = request.getAttribute("javax.servlet.forward.request_uri").toString();
		
		List<MenuVO> menuVOList = account.getMenuVOList();
		
		for(MenuVO menuVO : menuVOList) {			
			
			if(reqUri.equals(menuVO.getMenuUrl())){
				model.addAttribute("activeParentSeq", menuVO.getParentSeq());
				model.addAttribute("activeSeq", menuVO.getMenuSeq());
			}
		}
		
		model.addAttribute("adminMenu", menuVOList);
		
		//log.debug("session info AgenCd :: " + account.getAdmCd() + ", admNm :: " + account.getAdmNm());
		return "comm/layout/left.comm";
	}

}
