package com.namyang.nyorder.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 시스템명 : 남양유업 대리점주문 시스템
 * 업무명  : 
 * 파일명  : DateUtil.java
 * 작성자  : kjin
 * 작성일  : 2022. 3. 15.
 *
 * 설 명  : 날짜, 시간 관련 유틸. 
 * --------------------------------------------------
 *   변경일             변경자           변경내역
 * --------------------------------------------------
 * 2022. 3. 15.        kjin        최조 프로그램 작성
 *
 */
public class DateUtil {
    
    /** The Constant DEFAULT_PATTERN. */
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd";

    /** The Constant DEFAULT_PATTERN. */
    public static final String PATTERN_DEFAULT2 = "yyyy/MM/dd";

    /** The Constant PATTERN_SYSDATE. */
    public static final String PATTERN_SYSDATE = "yyyyMMdd";

    /** The Constant DEFAULT_PATTERN. */
    public static final String PATTERN_YYMMDD = "yy.MM.dd";

    /** The Constant PATTERN_SYSDATE. */
    public static final String PATTERN_SYSDATE_YYYY = "yyyy";

    /** The Constant PATTERN_SYSDATE. */
    public static final String PATTERN_SYSDATE_MM = "MM";

    /** The Constant PATTERN_SYSDATE. */
    public static final String PATTERN_SYSDATE_DD = "dd";

    /** The Constant PATTERN_SYSDATE. */
    public static final String PATTERN_SYSDATE_FULLDATE = "yyyyMMddHHmmss";

    /**
     * 현재날짜(yyyy/MM/dd)를 가져오기.
     * 
     * @return the today
     */
    public static String getToday() {
        return getToday(PATTERN_DEFAULT);

    }

    /**
     * 오늘날짜 가져오기
     * 
     * @param format
     *            the format
     * @return the today
     */
    public static String getToday(final String format) {
        final DateTime dt = new DateTime();
        return parseStringDate(dt, format);

    }
    
    /**
     * DateTime을 String 날짜로 변환.
     * 
     *@param datetime DateTime
     * @param pattern 날짜 포멧
     * 
     * @return string date
     */
    public static String parseStringDate(final DateTime datetime, final String pattern) {
        final DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.print(datetime);
    }

    /**
     * <pre>
     * Description : 생년월일을 기준으로 현재 나이 계산
     * </pre>
     * 
     * @Method Name : getAge
     * @param birthYear
     * @param birthMonth
     * @param birthDay
     * @return
     */
	public static int getAge(final int birthYear, final int birthMonth, final int birthDay) {
		final Calendar current = Calendar.getInstance();
		final int currentYear  = current.get(Calendar.YEAR);
		final int currentMonth = current.get(Calendar.MONTH) + 1;
		final int currentDay   = current.get(Calendar.DAY_OF_MONTH);
		
		int age = currentYear - birthYear;
		// 생일 안 지난 경우 -1
		if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)  
			age--;
		
		return age;
	}
    
    /**
     *
     * 해당년의 월의 마지막 날짜
     *
     * @param year 년도
     * @param month 월
     * @return int
     */
    public static int getDaysOfMonth(final int year, final int month) {
        final int[] DOMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        final int[] lDOMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 4) == 0) {
            if ((year % 100) == 0 && (year % 400) != 0) {
                if(month>11){
                    return DOMonth[0];
                }else{
                    return DOMonth[month-1];
                }
            }
            if(month>11){
                return lDOMonth[0];
            }else{
                return lDOMonth[month-1];
            }
        } else {
            if(month>11){
                return DOMonth[0];
            }else{
                return DOMonth[month-1];
            }
        }
    }

    /**
     *
     * 시스템 날짜
     *
     * @return Date
     */
    public static Date getSysDate() {
        final Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
    *
    * 시스템 날짜 (yyyy-MM-dd)
    *
    * @return String
    */
   public static String getSysYearDayHypen() {
       final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
       final Date date = new Date();
       return dateFormat.format(date);
   }
   
    /**
     *
     * 두 시간의 간격
     *
     * @param d1 시작날짜
     * @param d2 끝날짜
     * @return int (분)
     * @throws Exception
     */
    public static int getTimeInterval(final Date d1, final Date d2) throws Exception {
        int interval = 0;

        interval = (int)((d1.getTime() - d2.getTime()) / (60 * 1000));

        return interval;

    }

    /**
     *
     * 두 시간의 간격
     *
     * @param d1 시작날짜
     * @param d2 끝날짜
     * @return int (분)
     * @throws Exception
     */
    public static int getTimeInterval(final long d1, final long d2) throws Exception {
        int interval = 0;

        interval = (int)((d1 - d2) / (60 * 1000));

        return interval;

    }

    /**
     *
     * 현재와 시간 비교
     *
     * @return timedate 초
     * @throws Exception
     */

    public static int getTimeIntervalSecond(final String timedate) throws Exception {
        int interval = 0;

        final long d1 = System.currentTimeMillis();
        final long d2 = Long.parseLong(timedate);
        interval = (int)((d1 - d2) / 1000);

        return interval;

    }

    /**
    *
    * targetDate까지의 남은 시간을 일-시-분-초 순서로 배열로 반환한다.
    *
    * @param targetDate 초
    * @return int[] 일-시-분-초
    */
   public static int[] getIntervalSecond(final Date targetDate) {
       final Date sysdate = getSysDate();

       int totalSec = (int)((targetDate.getTime() - sysdate.getTime()) / 1000 );

       final int[] days = new int[4];
       if(totalSec < 0)
           totalSec = 0;

       final int day = totalSec / (60 * 60 * 24);
       final int hour = (totalSec - day * 60 * 60 * 24) / (60 * 60);
       final int minute = (totalSec - day * 60 * 60 * 24 - hour * 60 * 60) / 60;
       final int second = totalSec % 60;

       days[0] = day;
       days[1] = hour;
       days[2] = minute;
       days[3] = second;
       return days;
   }

    /**
     *
     * 날짜
     *
     * @param date 날짜
     * @param ymd 날짜
     * @param amt 추가 일자 또는 시간
     * @return Date
     */
    public static Date getDateAdd(final Date date, final int ymd, final int amt) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
        return cal.getTime();
    }
    
    /**
     *
     * 날짜
     *
     * @param ts Timestamp 객체
     * @return int
     */
    public static int getAge(final Timestamp ts) {

        final Calendar c = Calendar.getInstance();
        final int curYear = c.get(Calendar.YEAR);

        c.setTimeInMillis(ts.getTime());
        final int birthYear = c.get(Calendar.YEAR);

        return curYear - birthYear;
    }
    
    /**
     *
     * 초를 일-시-분-초로 표현한다.
     *
     * @param totalSec 초
     * @return 일-시-분-초
     */
    public static String getSecondToDate(final int totalSec) {
        final int day = totalSec / (60 * 60 * 24);
        final int hour = (totalSec - day * 60 * 60 * 24) / (60 * 60);
        final int minute = (totalSec - day * 60 * 60 * 24 - hour * 3600) / 60;
        final int second = totalSec % 60;
        return day+"-"+hour+"-"+minute+"-"+second;
    }

    /**
     *
     * 입력받은 date 에 addDate 만큼 날짜를 더해준다.
     *
     * @param date
     * @param addDate
     */
    public static Date addDate(final Date date, final int addDate){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addDate);
        return cal.getTime();
    }
    
    /**
     *
     * 입력받은 date 에 addDate 만큼 날짜를 더해준다.
     *
     * @param date
     * @param addDate
     */
    public static Date addMonth(final Date date, final int addDate){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, addDate);
        return cal.getTime();
    }
    
    /**
     * <pre>
     * Description : 입력받은 date 에 분 을 더해준다
     * 
     * </pre>
     * 
     * @Method Name : addMinute
     * @param date
     * @param addDate
     * @return
     */
    public static Date addMinute(final Date date, final int addDate){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, addDate);
        return cal.getTime();
    }

    /**
     *
     * 입력받은 날짜를 원하는 식으로 표출한다.
     *
     * @param date Date 객체
     * @param formatter 날짜형식 ex) yyyyMMdd
     * @return String 객체
     */
    public static String formatter(final Date date, final String formatter) {
        final SimpleDateFormat sdfDate = new SimpleDateFormat(formatter, Locale.KOREAN);//dd/MM/yyyy
        return sdfDate.format(date);
    }
    
    /**
    *
    * 입력받은 날짜를 원하는 식으로 표출한다.
    *
    * @param date Date 객체
    * @param formatter 날짜형식 ex) yyyyMMdd
    * @return String 객체
    */
	public static String toDateFormat(final String str) {
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);//dd/MM/yyyy
		return sdfDate.format(str);
	}
	
	/**
	 * <pre>
	 * Description : 날짜 사이에 문자 및 공백 제거
	 * 
	 * </pre>
	 * 
	 * @Method Name : convertDateReplace
	 * @param date
	 * @return
	 */
	public static String convertDateReplace(String date) {
		String inDate = date;
		if (null != inDate && !"".equals(inDate))
		{
			inDate = inDate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
			if (inDate.length() > 14)
			{
				inDate = inDate.substring(0, 14);
			}
		}
		return inDate;
	}
	
	/**
	 * <pre>
	 * Description : timestamp 를 Date String으로 변환하는 함수
	 * 
	 * </pre>
	 * 
	 * @Method Name : getTimestampToDate
	 * @param timestampStr
	 * @return
	 */
	public static String getTimestampToDate(final String timestampStr) {
		
		if (null == timestampStr || "".equals(timestampStr))
		{
			return "";
		}
		
		final long timestamp = Long.parseLong(timestampStr);
		final Date date = new Date(timestamp); 
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN); 
		final String formattedDate = sdf.format(date);
		
		return formattedDate;
	}
	
}