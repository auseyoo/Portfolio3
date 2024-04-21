package com.namyang.nyorder.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : Mybatis Util
 * 파일명  : MyBatisCmpr.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 18.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 18.        kjin        최조 프로그램 작성
 *
 */
public class MyBatisCmpr {
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		
		if (obj instanceof String)
		{
			return obj == null || "".equals(obj.toString().trim());
		}
		else if (obj instanceof List)
		{
			return obj == null || ((List) obj).isEmpty();
		}
		else if (obj instanceof Map)
		{
			return obj == null || ((Map) obj).isEmpty();
		}
		else if (obj instanceof Object[])
		{
			return obj == null || Array.getLength(obj) == 0;
		}
		else
		{
			return obj == null;
		}
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	
}
