package com.namyang.nyorder.util;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : CamelHashMap.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 10.
 *
 * 설 명  : Mybatis return Map 형태를 카멜케이스 형태로 변경하는 Util
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 10.        kjin        최조 프로그램 작성
 *
 */
public class CamelHashMap extends HashMap<Object, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2706327269983141795L;

	@Override
	public Object put(Object key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(StringUtil.nvl(key)), value);
	}
	
}
