package com.namyang.nyorder.config.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.namyang.nyorder.config.error.exception.BusinessException;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 공통 에러 처리
 * 파일명  : GlobalExceptionHandler.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 2.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 2.        kjin        최조 프로그램 작성
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	public MessageSource messageSource;
	
	/**
	 * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
	 * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
	 * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleMethodArgumentNotValidException ★★★", e);
		if(isJson(null, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	/**
	 * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
	 * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
	 */
	@ExceptionHandler(BindException.class)
	public Object handleBindException(BindException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleBindException ★★★", e);
		if(isJson(null, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	/**
	 * enum type 일치하지 않아 binding 못할 경우 발생
	 * 주로 @RequestParam enum으로 binding 못했을 경우 발생
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Object handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleMethodArgumentTypeMismatchException ★★★", e);
		if(isJson(null, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleHttpRequestMethodNotSupportedException ★★★", e);
		if(isJson(null, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.METHOD_NOT_ALLOWED);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	@ExceptionHandler(value = NotFoundException.class)
	public Object handleNotFoundException(NotFoundException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleNotFoundException ★★★", e);
		if(isJson(handlerMethod, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public Object handleNoHandlerFoundException(NoHandlerFoundException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleNoHandlerFoundException ★★★", e);
		if(isJson(handlerMethod, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleException ★★★", e);
		if(isJson(handlerMethod, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	@ExceptionHandler(value = BusinessException.class)
	//@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Object handleBusinessException(BusinessException e, HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("★★★ handleBusinessException ★★★", e);
		if(isJson(handlerMethod, request))
		{
			Map<String, Object> errorMap = returnJson(e, request, response);
			return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return returnView(e, request, response);
		}
	}
	
	private ModelAndView returnView(Exception e, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = null;
		
		if (e instanceof NotFoundException || e instanceof NoHandlerFoundException)
		{
			mav = new ModelAndView("error/404");
		}
		else
		{
			mav = new ModelAndView("error/500");
		}
		return mav;
	}
	
	private Map<String, Object> returnJson(Exception e, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("errorCode", "9999");
		errorMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		if ( e instanceof BusinessException )
		{
			errorMap.put("message", e.getMessage());
		}
		else
		{
			errorMap.put("message", messageSource.getMessage("Err.E001", null, Locale.KOREA));
		}
		return errorMap;
	}
	
	private boolean isJson(HandlerMethod  handlerMethod, HttpServletRequest request) {
		
		final boolean isJsonResponse = false;
		
		if (request != null)
		{
			if(request.getContentType() != null && request.getContentType().indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) return true;
			if(request.getHeader("accept") != null && request.getHeader("accept").indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) return true;
		}
		
		if(handlerMethod != null)
		{
			if (handlerMethod.getMethodAnnotation(RequestMapping.class) != null) {
				final String[] mtArr = handlerMethod.getMethodAnnotation(RequestMapping.class).produces();
				for(final String mt : mtArr) {
					if(mt.indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) return true;
				}
			}
			
			if (handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class) != null) {
				final String[] mtArr = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class).produces();
				for(final String mt : mtArr) {
					if(mt.indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) return true;
				}
			}
			
			if (handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class) != null) {
				final String[] mtArr = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class).produces();
				for(final String mt : mtArr) {
					if(mt.indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) return true;
				}
			}
		}
		
		return isJsonResponse;
	}
	
}