package com.namyang.nyorder.config.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.namyang.nyorder.comm.service.BasicService;
import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.BasicVO;
import com.namyang.nyorder.comm.vo.MenuVO;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	BasicService basicService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	
		//인증된 사용자의 정보를 추출
    	AccountVO acount = (AccountVO)authentication.getPrincipal();
		System.out.println("acount getTmpPwdYn =>"+acount.getTmpPwdYn()+", ::" + acount.getAdmCd());
		
		String url="/comm/main.do";
		
		if("Y".equals(acount.getTmpPwdYn()) || Integer.parseInt(acount.getDtmDiff()) > 90){
			url="/changePassword.do";
		}		
		
		BasicVO menuParam = new BasicVO();
		MenuVO menuVO = new MenuVO();
		menuVO.setAdmSeq(acount.getAdmSeq());
		
		menuParam.setQueryId("com.namyang.nyorder.comm.dao.MenuMapper.selectLeftMenuList");		
		menuParam.setParamClass(menuVO);
		
		acount.setMenuVOList(basicService.basicSelectList(menuParam));
		
		request.getSession().setAttribute("userInfo", acount);
		
		//request.getSession().setAttribute("agenSeq", acount.getAgenSeq());
		//request.getSession().setAttribute("emplSeq", acount.getEmplSeq());
				
		response.sendRedirect(url);
    	
    }
    	

}
