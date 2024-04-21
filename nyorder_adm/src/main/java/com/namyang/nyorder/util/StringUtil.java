package com.namyang.nyorder.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : StringUtil.java
 * 작성자  : kjin
 * 작성일  : 2022. 2. 22.
 *
 * 설 명  : StringUtil
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 2. 22.        kjin        최조 프로그램 작성
 *
 */
@Slf4j
public class StringUtil {
	
	/**
	 * <pre>
	 * Description : String / List / Map / [] 형태의 Object들의 null 그리고 빈값을 체크해줍니다.
	 * 
	 * </pre>
	 * 
	 * @Method Name : isEmpty
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(final Object o) {
		
		if (o == null) { 
			return true; 
		}
		
		if ((o instanceof String) && (((String)o).trim().length() == 0)) {
			return true; 
		}
		
		if (o instanceof Map) {
			return ((Map<?, ?>)o).isEmpty(); 
		}
		
		if (o instanceof List) {
			return ((List<?>)o).isEmpty(); 
		}
		
		if (o instanceof Object[]) {
			return (((Object[])o).length == 0);
		}
		
		return false; 
	}
	
	/**
	 * <pre>
	 * Description : null 이 아닌지
	 * 
	 * </pre>
	 * 
	 * @Method Name : isNotEmpty
	 * @param o
	 * @return
	 */
	public static boolean isNotEmpty(final Object o) {
		return !isEmpty(o);
	}
	
	/**
     * 비어 있으면 null String 을 리턴한다.
     * @param str
     * @return
     */
    public static String nvl( final String str ){
    	if (isEmpty(str)) {
            return "";
        } else {
            return str.trim();
        }
    }
    
    /**
     * 비어 있으면 nullvalue 을 리턴한다.
     * @param str
     * @param nullvalue
     * @return
     */
    public static String nvl( final String str, final String nullvalue ){
    	if (isEmpty(str)) {
            return nullvalue;
        } else {
            return str.trim();
        }
    }

    /**
     * 비어 있으면 nullvalue 을 리턴한다.
     * @param str
     * @param nullvalue
     * @return
     */
    public static int nvl( final String str, final int nullvalue ){
    	if (isEmpty(str)) {
            return nullvalue;
        } else {
            return Integer.parseInt(str);
        }
    }
    
    /**
     * 비어 있으면 nullvalue 을 리턴한다.
     * @param str
     * @param nullvalue
     * @return
     */
    public static long nvl( final String str, final long nullvalue ){
    	if (isEmpty(str)) {
            return nullvalue;
        } else {
            return Long.parseLong(str);
        }
    }
    
	/**
	 * <pre>
	 * Description : 
	 * 
	 * </pre>
	 * 
	 * @Method Name : nvl
	 * @param str
	 * @param nullvalue
	 * @return
	 */
	public static double nvl( final String str, final double nullvalue ) {
		if (isEmpty(str)) {
			return nullvalue;
		} else {
			return Double.parseDouble(str);
		}
	}

    /**
     * 비어 있으면 nullvalue 을 리턴한다.
     * @param val
     * @param nullvalue
     * @return
     */
    public static int nvl( final Integer val, final int nullvalue ){
    	if (isEmpty(val))
            return nullvalue;
        else
            return val.intValue();
    }
    
    /**
     * <pre>
     * Description : null 이면 "" 리턴
     * 
     * </pre>
     * 
     * @Method Name : nvl
     * @param obj
     * @return
     */
    public static String nvl(final Object obj) {
    	if (isEmpty(obj))
			return "";
		else
			return String.valueOf(obj).trim();
    }
    
    /**
    * 해당 Object를 String으로 형변형하여 리턴한다.
    * <p>
    * @param    obj  변환할 객체
    * @return       변환된 문자열 
    */
    public static String nvl(final Object obj, final String converted) {
    	if (isEmpty(obj))
			return converted;
		else
			return String.valueOf(obj).trim();
    }
    
    /**
     * <pre>
     * Description : 해당 Object를 int 로 형변형하여 리턴한다.
     * 
     * </pre>
     * 
     * @Method Name : nvl
     * @param obj
     * @param converted
     * @return
     */
    public static int nvl(final Object obj, final int converted) {
    	if (isEmpty(obj))
			return converted;
		else
			return Integer.parseInt(obj.toString());
    }
    
    /**
     * <pre>
     * Description : 해당 Object를 double 로 형변형하여 리턴한다.
     * 
     * </pre>
     * 
     * @Method Name : nvl
     * @param obj
     * @param converted
     * @return
     */
    public static double nvl(final Object obj, final double converted) {
    	if (isEmpty(obj))
			return converted;
		else
			return Double.parseDouble(obj.toString());
    }
    
    /**
     * 한글고려한 ..붙이기    ex) cutBytes("우리1234", 3, "...") ==> "우리1..."
     * @param string
     * @param maxSize
     * @param re
     * @return
     */
    public static String cutBytes(final String string, final int maxSize, final String re) {
        
        if(string == null)
            return "";
        
        final int tLen = string.length();
        int count = 0;
        char c;
        int s=0;
        for(s=0;s<tLen;s++){
            c = string.charAt(s);
            if(count > maxSize) break;
            if(c>127) count +=2;
            else count ++;
        }
        return (tLen >s)? string.substring(0,s)+re : string;
    }      
    
    /**
     * String 를 배열 형태로 리턴한다.
     * @param str
     * @param strToken
     * @return
     */
    public static String[] getStringArray(final String str, final String strToken) {
        if (str.indexOf(strToken) != -1) {
            final StringTokenizer st = new StringTokenizer(str, strToken);
            final String[] stringArray = new String[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++) {
                stringArray[i] = st.nextToken();
            }
            return stringArray;
        }
        return new String[] {str };
    }    
    
    /**
     * <pre>
     * 이메일 주소 마스킹 처리
     * </pre>
     * @param email
     * @return maskedEmailAddress
     */
    public static String getMaskedEmail(final String email) {
      /*
       * 요구되는 메일 포맷
       * {userId}@domain.com
       * */
       final String regex = "\\b(\\S+)+@(\\S+.\\S+)";
       final Matcher matcher = Pattern.compile(regex).matcher(email);
       if (matcher.find()) {
          final String id = matcher.group(1); // 마스킹 처리할 부분인 userId
          /*
          * userId의 길이를 기준으로 최소 마스킹 길이를 제외한 앞글자 길이가  
          * 앞글자 길이가 세글자 이상인 경우 세글자 뒤에 모든 글자를 마스킹 처리하고,
          * 앞글자 길이가 세글자 미만인 경우 앞글자 뒤에 모두 마스킹 처리하고,
          * 앞글자 길이가 0보다 작거나 같을 경우 최소 마스킹 길이만큼 마스킹 처리
          */
          final int MIN_MASK = 4; // 최소 마스킹 길이
          final int length = id.length() - MIN_MASK;
          if( length <= 0) {
              final char[] c = new char[MIN_MASK];
              Arrays.fill(c, '*');
              return email.replace(id, String.valueOf(c));
          } else if( length < 3) {
              return email.replaceAll("(?<=.{"+length+"}).(?=[^@]*?@)", "*");
          } else {
              return email.replaceAll("(?<=.{3}).(?=[^@]*?@)", "*");
          }
       }
       return email;
    }
    
    /**
     * <pre>
     * 이름 마스킹 처리
     * </pre>
     * @param name
     * @return maskedName
     */
    public static String getMaskedName(final String name) {
        /*
         * name의 길이를 기준으로 세글자 이상인 경우 가운데 글자를 마스킹 처리하고,
         * 두글자인 경우 마지막 글자를 마스킹 처리
         * */
        final int length = name.length();
        if( length == 2) {
            return name.replaceAll("(?<=.).", "O");
        } else if( length > 2){
            return name.replaceAll("(?<=.).(?=.)", "O");
        }
        return name;
    }
    
    /**
     * <pre>
     * 1. Description : 문자열을 base64 인코딩
     * 2. Processing details
     *    1) 
     * </pre>
     * @Method Name : encodingString
     * @param source
     * @return
     */
    public static String encodingString(final String source){
        String tempSource = "";
        try{
            tempSource = URLEncoder.encode(source, "UTF-8");
        }catch(final UnsupportedEncodingException e){
        	log.error(e.getMessage().replaceAll("[\r\n]",""));
        }
        return tempSource;
    }
    
    /**
     * <pre>
     * @Desc        : Clob 를 String 으로 변경
     * </pre>
     * 
     * @author      : kjkim
     * @Method Name : clobToString
     * @param clob
     * @return
     * @throws Exception 
     */
	public static String clobToString(final Clob clob) throws Exception {
		
		if (clob == null)
			return "";
		
		final StringBuffer strOut = new StringBuffer();
		
		String str = "";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(clob.getCharacterStream());
			str = br.readLine();
			while (str != null) {
				strOut.append(str);
			}
			
		} catch (final SQLException e) {
			log.error(e.getMessage().replaceAll("[\r\n]","") , e);
		} catch (final IOException io) {
			log.error(io.getMessage().replaceAll("[\r\n]","") , io);
		} finally {
			if (br != null){
				br.close();
			}
		}
		
		return strOut.toString();
	}
	
	/**
	 * String to List<String> with delimeter
	 * 
	 * @param str
	 * @param delimeter
	 * @return
	 */
	public static List<String> strToList(final String str, final String delimeter) {
		final List<String> tokens = new ArrayList<String>();

		if (str != null) {
			final StringTokenizer st = new StringTokenizer(str, delimeter);
			while (st.hasMoreTokens()) {
				final String en = st.nextToken().trim();
				tokens.add(en);
			}
		}
		return tokens;
	}
    
	/**
	 * <pre>
	 * Description : 랜덤 문자 추출
	 * 
	 * </pre>
	 * 
	 * @Method Name : getRandomPassword
	 * @param length
	 * @return
	 */
	public static String getRandomPassword( final int length ) {
		
		final char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		final StringBuffer sb = new StringBuffer();
		
		final SecureRandom sr = new SecureRandom();
		
		for( int i = 0 ; i < length; i++ )
		{
			sb.append( charaters[ sr.nextInt( charaters.length ) ] );
		}
		
		return sb.toString();
	}
	
	/**
	 * <pre>
	 * @Desc        : 임의 비밀번호 8자리 생성 (특수문자 조합)
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : randomPw
	 * @return
	 */
	public static String getRandomPasswordSc( final int length ) {
		final char pwCollection[] = new char[] {
				'1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z',
				'!','@','#','$','%','&','*'};//배열에 선언
		
		final StringBuffer sbRanPw = new StringBuffer();
		
		for ( int i = 0; i < length; i++ )
		{
			//final int selectRandomPw = (int)(Math.random()*(pwCollection.length));
			final int selectRandomPw = (int)(new SecureRandom().nextDouble()*(pwCollection.length));
			sbRanPw.append(pwCollection[selectRandomPw]);
		}
		log.info("random ranPw => " + sbRanPw.toString());
		return sbRanPw.toString();
	}
	
	/**
	 * <pre>
	 * Description : 랜덤 숫자 추출
	 * 
	 * </pre>
	 * 
	 * @Method Name : getRandomPassword
	 * @param length
	 * @return
	 */
	public static String getRandomNumber( final int length ) {
		
		final char[] charaters = {'0','1','2','3','4','5','6','7','8','9'};
		final StringBuffer sb = new StringBuffer();
		final SecureRandom sr = new SecureRandom();
		
		for( int i = 0 ; i < length ; i++ )
		{
			sb.append( charaters[ sr.nextInt( charaters.length ) ] );
		}
		
		return sb.toString();
	}
	
	/**
	 * <pre>
	 * Description : 마지막 문자열을 찾아서 replace 
	 * 
	 * </pre>
	 * 
	 * @Method Name : replaceLast
	 * @param string
	 * @param toReplace
	 * @param replacement
	 * @return
	 */
	public static String replaceLast(final String string, final String toReplace, final String replacement) {    
		
		if (isEmpty(string))
		{
			return "";
		}
		
		final int pos = string.lastIndexOf(toReplace);     
		
		if (pos > -1) {
			return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length());
		} else { 
			return string;
		} 
	}
	
	/**
	 * <pre>
	 * @Desc        : 브라우저 구분 얻기.
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : getBrowser
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		
		final String header = request.getHeader("User-Agent");
		if(header.indexOf("MSIE") > -1)
		{
			return "MSIE";
		}
		
		/*
		 * IE 10 버전 이하에서는 'MSIE' 문자열을 체크하여 브라우저가 IE인지 체크하였다.
		 * 하지만 IE 11 버전부터는 브라우저 정보에 MSIE라는 문자열이 들어가지 않는다
		 * 기존에 IE를 체크하던 로직은 IE 11을 나타내는 'rv:11' 문자열을 함께 체크하도록 해야 한다.
		 * */
		else if(header.indexOf("rv:11.0") > -1)
		{
			return "ie11";
		}
		else if(header.indexOf("Chrome") > -1)
		{
			return "Chrome";
		}
		else if(header.indexOf("Opera") > -1)
		{
			return "Opera";
		}
		return "Firefox";
	}
	
	public static String getDisposition(String filename, String browser) throws Exception {
		
		String encodedFilename = "";
		
		if(browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if(browser.equals("ie11")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if(browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.equals("Chrome")) {
			final StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++)
			{
				final char c = filename.charAt(i);
				if (c > '~')
				{
					sb.append(URLEncoder.encode(String.valueOf(c), "UTF-8"));
				}
				else
				{
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		}
		else
		{
			throw new Exception("Not Supported Browser.");
		}
		
		return encodedFilename;
	}
	
	/**
	 * 전화번호 형태를 하이픈을 추가하여 보여준다.
	 * 
	 * @param telNm
	 * @return
	 */
	public static String getTelNumberRex(String telNo) {
		Pattern tellPattern = Pattern.compile( "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})");
		
		Matcher matcher = tellPattern.matcher(telNo);
        if(matcher.matches()) {
            //정규식에 적합하면 matcher.group으로 리턴
            return matcher.group(1)+"-"+matcher.group(2)+"-"+matcher.group(3);
        }
        else{
            //정규식에 적합하지 않으면 substring으로 휴대폰 번호 나누기
            String str1 = telNo.substring(0, 3);
            String str2 = telNo.substring(3, 7);
            String str3 = telNo.substring(7, 11);
            return str1+"-"+str2+"-"+str3;
        }
	}
	
	
	/**
	 *  사업자번호 형태를 하이픈을 추가하여 보여준다.
	 * 
	 * @param telNm
	 * @return
	 */
	public static String getBizNumberRex(String bizNo) {
		final String regEx = "(\\d{3})(\\d{2})(\\d{5})";
		if(!Pattern.matches(regEx, bizNo)) {
			return "";
		}
		return bizNo.replaceAll(regEx, "$1-$2-$3");
	}
	
	/**
	 * <pre>
	 * @Desc        :  XSS필터 : 크로스 사이트 스크립트 치환
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : getXSSFilter
	 * @param value
	 * @return
	 */
	public static String replaceXSSFilter(String str) {
		
		if (isEmpty(str))
		{
			return "";
		}
		
		String value = str;
		
		value = value.replaceAll("<"  , "&lt;");
		value = value.replaceAll(">"  , "&gt;");
		//value = value.replaceAll("\\(", "&#40;");		// 치환 안함
		//value = value.replaceAll("\\)", "&#41;");		// 치환 안함
		value = value.replaceAll("'"  , "&#39;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll("`", "&#96;");
		
		return value;
	}
	
	/**
	 * <pre>
	 * @Desc        :  XSS필터 : 크로스 사이트 스크립트 역치환
	 * </pre>
	 * 
	 * @author      : kjkim
	 * @Method Name : getReXSSFilter
	 * @param value
	 * @return
	 */
	public static String replaceReXSSFilter(String str) {
		
		if (isEmpty(str))
		{
			return "";
		}
		
		String value = str;
		
		value = value.replaceAll("&lt;" , "<");
		value = value.replaceAll("&gt;" , ">");
		value = value.replaceAll("&#40;", "(");
		value = value.replaceAll("&#41;", ")");
		value = value.replaceAll("&#39;", "'");
		value = value.replaceAll("&quot;", "\"");
		value = value.replaceAll("&#96;" , "`");
		
		return value;
	}
	
	/**
	 * <pre>
	 * @Desc        :  \n \n\r \N 등 치환
	 * </pre>
	 * 
	 * @author      : djkim
	 * @Method Name : replacePrintFilter
	 * @param value
	 * @return
	 */
	public static String replacePrintFilter(String str) {
		
		if (isEmpty(str))
		{
			return "";
		}
		
		String value = str;
		
		value = value.replaceAll("\n" , " ");
		value = value.replaceAll("\n\r", " ");
		value = value.replaceAll("\r", " ");
		value = value.replaceAll("\\\\N", " ");
		
		
		return value;
	}
	
	
	
}