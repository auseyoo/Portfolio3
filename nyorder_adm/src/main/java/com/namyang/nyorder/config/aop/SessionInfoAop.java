package com.namyang.nyorder.config.aop;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.namyang.nyorder.comm.vo.AccountVO;
import com.namyang.nyorder.comm.vo.CommVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SessionInfoAop {
	
	private final HttpSession httpSession;	

    @Pointcut("execution(* com.namyang.nyorder..*Service.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.namyang.nyorder.config.aop.SessionInfo)")
    private void enableSessionInfo() {}
	
    @Before("cut() && enableSessionInfo()")
    public void before(JoinPoint joinPoint) {
    	
    	log.debug("before  start ####### " + joinPoint.getThis().toString());
    	
        //메서드로 들어오는 매개변수들
        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
        	
        	log.debug("before  ####### arg :: " + arg.toString());
        	
            if(arg instanceof CommVO)  {
            	//클래스 캐스팅
            	CommVO commVo = CommVO.class.cast(arg);
            	AccountVO userInfo = (AccountVO) httpSession.getAttribute("userInfo");
            	
            	if(userInfo!=null) {
            		
                	commVo.setAdmCd(userInfo.getAdmCd());
                	
            	}else {            		
            		commVo.setAgenSeq("00000000000");
            	}
            }
        }
    }	
    
}

