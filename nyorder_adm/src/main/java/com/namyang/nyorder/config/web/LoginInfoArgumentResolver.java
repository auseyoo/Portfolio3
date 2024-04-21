package com.namyang.nyorder.config.web;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.CommVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginInfoArgumentResolver  implements HandlerMethodArgumentResolver {
	
	private final HttpSession httpSession;
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
    	
    	Class<?> targetVo = parameter.getParameterType();
    	Object obj = null;		
			
		try {
			Constructor<?> constructor1 = targetVo.getConstructor();
			obj = constructor1.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	if(obj != null) {
    		if(obj instanceof CommVO) {        		
    			log.debug("supportsParameter  true #######::" + parameter.getParameterType());    			
    			return true;
        	}    		
    	} 
    	return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter
    		, ModelAndViewContainer modelAndViewContainer
    		, NativeWebRequest request
    		, WebDataBinderFactory webDataBinderFactory) throws Exception {
    	    	
    	Class<?> targetVo = parameter.getParameterType();    	
    	Method[] mets = targetVo.getMethods();    	    	    	
    	CommVO commVo = new CommVO();
    	
    	try {
			Constructor<?> constructor1 = targetVo.getConstructor();
			commVo = (CommVO) constructor1.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	AccountVO accountVO = (AccountVO) httpSession.getAttribute("userInfo");
    	
    	if(accountVO != null) {
	    	for(Method met : mets) {
	    		
	    		if(met.getName().equals("setSessionInfo")) {
	    			try {
						met.invoke(commVo, accountVO);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
		}
    	
    	Iterator<String> paramNames = request.getParameterNames();
		
		while (paramNames.hasNext()) {
			String key = (String) paramNames.next();  
			String value = request.getParameter(key);
			//log.debug("#resolveArgument RequestParameter: " + key + "=" + value + "");
			
	    	for(Method met : mets) {
	    		
	    		Parameter[] metParams = met.getParameters();	    		
	    		String metName = met.getName();	    		
	    		//log.debug("#resolveArgument metName: " + metName.substring(0, 3) + "=" + metName.substring(3) + "" + "" + key);
	    		
	    		if(metName.substring(0, 3).equals("set")) {	    			
	    			if(metName.substring(3).toUpperCase().equals(key.toUpperCase())) {	    				
	    				met.invoke(commVo, value);
	    			}
	    		}
	    	}
		}
    	
    	return commVo;
    	
    }

}
