package com.namyang.nyorder.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomFailureHandler  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

	// 실패로직 핸들링

        exception.printStackTrace();

        writePrintErrorResponse(request, response, exception);
    }

    private void writePrintErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> requestMap = new HashMap<>();

            requestMap = getExceptionMessage(exception);
           
            //response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));
            
            //response.sendRedirect("/login.do");
            request.setAttribute("param", requestMap);
            
            request.getRequestDispatcher("/login.do").forward(request, response);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Object> getExceptionMessage(AuthenticationException exception) {
    	HashMap<String, Object> resultMap = new HashMap<>();
    	
        if (exception instanceof BadCredentialsException) {
        	//비밀번호불일치
        	resultMap.put("status", "01");
        	resultMap.put("message", "password not match");
        } else if (exception instanceof UsernameNotFoundException) {
        	//계정없음
        	resultMap.put("status", "02");
        	resultMap.put("message", "no user");
        } else if (exception instanceof AccountExpiredException) {
        	//계정만료
        	resultMap.put("status", "03");
        	resultMap.put("message", "AccountExpiredException");
        } else if (exception instanceof CredentialsExpiredException) {
        	//비밀번호만료
        	resultMap.put("status", "04");
        	resultMap.put("message", "CredentialsExpiredException");
        } else if (exception instanceof DisabledException) {
        	//계정비활성화
        	resultMap.put("status", "05");
        	resultMap.put("message", "DisabledException");
        } else if (exception instanceof LockedException) {
        	//계정잠김
        	resultMap.put("status", "06");
        	resultMap.put("message", "LockedException");
        } else {
        	resultMap.put("status", "99");
        	resultMap.put("message", "no");
        }
        
        return resultMap;
    }

}
