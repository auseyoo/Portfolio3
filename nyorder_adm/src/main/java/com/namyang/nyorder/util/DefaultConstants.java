package com.namyang.nyorder.util;

import java.util.Arrays;
import java.util.List;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : DefaultConstants.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 21.
 *
 * 설 명  : 상수 클래스
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 21.        kjin        최조 프로그램 작성
 *
 */
public class DefaultConstants {
	
	/**
	 * 기본 상수
	 */
	public static final String DEFAULT_YES = "1";
	public static final String DEFAULT_NO = "0";
	
	/** Multipart File is Null **/
	public static final int MULTIPART_FILE_NOT_EXIST = -1;
	
	public static final String URL_VIEW_RES_BEAN_NAME     = "urlBasedViewResolver";
	
	public static final String PROFILES_ACTIVE_LOCAL = "local";
	public static final String PROFILES_ACTIVE_DEV = "dev";
	public static final String PROFILES_ACTIVE_LIVE = "live";
	
	// 매입처 시퀀스 남양 = 1
	public static final String PUCH_SEQ_DEFAULT = "1";
	
	/**
	 * 판촉구분 코드
	 */
	public static final List<String> PRD_TYPES = Arrays.asList("1", "2");
	public static final String PRD_TYPE_NM_NEW = "신규";
	public static final String PRD_TYPE_NM_RE = "재계약";
	public static final String PRD_TYPE_NEW = "1";
	public static final String PRD_TYPE_RE = "2";
	
	
}
