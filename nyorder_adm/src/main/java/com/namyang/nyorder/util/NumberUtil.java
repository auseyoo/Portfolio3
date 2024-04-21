package com.namyang.nyorder.util;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : NumberUtil.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 21.
 *
 * 설 명  : 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 21.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
public class NumberUtil {
    
    /**
     *
     * 숫자 체크 (double 자리수 크기만)
     *
     * @param str Sting Value
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        try{
        	Double.parseDouble(str);
        } catch (final NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    /**
    *
    * 숫자 체크 (int가 넘는 자리 수까지)
    *
    * @param str Sting Value
    * @return boolean
    */
    public static boolean isNumericLong(String str) {
       try{
           Double.parseDouble(str);
       } catch (final NumberFormatException ex) {
           return false;
       }
       return true;
    }
   
    /**
     *
     * 통화(돈) 체크
     *
     * @param str Sting Value
     * @return boolean
     */
    public static boolean isCurrency(String str) {
        try{
            new DecimalFormat().parse(str.trim());
        } catch (final ParseException ex) {
            return false;
        }
        return true;
    }

    /**
     *
     * 통화(돈) 숫자 변환
     *
     * @param arg Sting Value
     * @return int
     */
    public static int curToInt(String arg) {
        try {
            return new DecimalFormat().parse(arg.trim()).intValue();
        } catch (final ParseException ex) {
        	log.error(ex.getMessage().replaceAll("[\r\n]",""));
        }
        return -1;
    }

    /**
     *
     * 통화(돈) 숫자 변환
     *
     * @param arg Sting Value
     * @return long
     */
    public static long curToLong(String arg) {
        try {
            return new DecimalFormat().parse(arg.trim()).longValue();
        } catch (final ParseException ex) {
        	log.error(ex.getMessage().replaceAll("[\r\n]",""));
        }
        return -1;
    }
    
    /**
     * 
     * <pre>
     * @Desc        : 랜덤수 가져오기
     * </pre>
     * 
     * @author      : kjkim
     * @Method Name : randomNumber
     * @param scope
     * @return
     */
    public static int randomNumber(final Integer scope) {
		
		if (scope == null || scope <= 0)
		{
			return 0;
		}
		
		final SecureRandom random = new SecureRandom();
		
        return random.nextInt(scope);
	}
    
    /**
	 * <pre>
	 * @Desc        : 숫자인지 체크 후 자리 수까지 체크
	 * 					(int 자리수 크기만)
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : isNumLength
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isNumLength(String str, int length) {
		
		try {
			
			if ( StringUtil.isEmpty(str) ) {
				return false;
			}
			
			if( isNumeric(str) && str.length() <= length) {
				return true;
			} else {
				return false;
			}
		} catch (final NumberFormatException ex) {
			return false;
		}
	}
    
    
}