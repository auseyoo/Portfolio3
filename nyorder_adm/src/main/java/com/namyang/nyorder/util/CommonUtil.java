package com.namyang.nyorder.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.support.JdbcUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : CommonUtil.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 23.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 23.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
public class CommonUtil {
	
	
	public static HashMap<String, Object> setMapKeyToCamelCase(HashMap<String, Object> param){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			
			resultMap.put(JdbcUtils.convertUnderscoreNameToPropertyName(entry.getKey()), entry.getValue());
			
			System.out.println("[key]:" + JdbcUtils.convertUnderscoreNameToPropertyName(entry.getKey()) + ", [value]:" + entry.getValue());
		}
		
		return resultMap;
	}
	
	/**
	 * @Method Name : mapToClass
	 * @작성일 : 2022. 2. 23.
	 * @작성자 : kjin
	 * @Method 설명 : Map => Vo class 로 변환
	 * @param <T>
	 * @param map
	 * @param o
	 * @param t
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T mapToClass(Map map, Object obj, Class<T> t) {
		try {
			BeanUtils.populate(obj, map);
		} catch (IllegalAccessException e) {
			log.error("error : " + e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("error : " + e.getMessage());
		}
		return (T) obj;
	}
	
	/**
	 * @Method Name : classToMap
	 * @작성일 : 2022. 2. 23.
	 * @작성자 : kjin
	 * @Method 설명 : Vo class => Map 으로 변환
	 * @param <S>
	 * @param obj
	 * @param type2
	 * @return
	 */
	public static <S> S classToMap(Object obj, Class<S> type2) {
		ObjectMapper oMapper = new ObjectMapper();
		S s = oMapper.convertValue(obj, type2);
		return s;
	}
	
}
