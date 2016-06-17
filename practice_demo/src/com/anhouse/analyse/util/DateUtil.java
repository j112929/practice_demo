package com.anhouse.analyse.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * @Title: TODO 
 * @Description: TODO(用一句话描述该类文件做什么)
 * @Team: 技术1部Java开发小组
 * @Author Andy-ZhichengYuan   
 * @Date 2014年12月9日
 * @Version V1.0   */
public class DateUtil {
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String longFmt20 = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String longFmt19 = "yyyy-MM-dd HH:mm:ss";
	public static final String longFmt16 = "yyyyMMdd-HH:mm:ss";
	public static final String longFmt15 = "yyyyMMdd HHmmss";
	public static final String longFmt14 = "yyyyMMddhhmmss";
	
	public static final String shortFmtT12 = "HH:mm:ss.SSS";
	public static final String shortFmt10 = "yyyy-MM-dd";
	public static final String shortFmtMd = "yyyy-M-d";
	public static final String shortFmtD8 = "yyyyMMdd";
	public static final String shortFmtT8 = "HH:mm:ss";
	public static final String shortFmtT6 = "HHmmss";
	
	/**根据传入的模式获取日期的格式类对象SimpleDateFormat
	 * @param pattern:
	 * longFmt19   yyyy-MM-dd HH:mm:ss 
	 * longFmt15   yyyyMMdd HHmmss
	 * shortFmt10  yyyy-MM-dd
	 * shortFmtT8   HH:mm:ss      */
	public static SimpleDateFormat getFmt(String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter;
	}
	
	/**根据传入的模式获取  现在的  日期时间Date
	 * @param pattern:
	 * longFmt19   yyyy-MM-dd HH:mm:ss
	 * longFmt15   yyyyMMdd HHmmss
	 * shortFmt10  yyyy-MM-dd
	 * shortFmtT8   HH:mm:ss   */
	public static Date getNowDateByFmt(String pattern){
		Date crtTime = new Date();
		SimpleDateFormat formatter = getFmt(pattern);
		String dateString = formatter.format(crtTime);
		
		ParsePosition pos = new ParsePosition(0);
		Date crtDate = formatter.parse(dateString, pos);
		formatter = null;
		dateString = null;
		pos = null;
		return crtDate;
	}
	
	/**根据格式将  传入日期的  String转换成相应格式的Date
	 * @param pattern:
	 * longFmt19   yyyy-MM-dd HH:mm:ss
	 * longFmt15   yyyyMMdd HHmmss
	 * shortFmt10  yyyy-MM-dd
	 * shortFmtT8   HH:mm:ss          */
	public static Date strToDateByFmt(String strDate, String pattern){
		SimpleDateFormat fmter = getFmt(pattern);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = fmter.parse(strDate, pos);
		fmter = null;
		pos = null;
		return strtodate;
	}
	
	/**根据传入的模式获取   现在的  日期时间字符串String
	 * @param pattern:
	 * longFmt19   yyyy-MM-dd HH:mm:ss 
	 * longFmt15   yyyyMMdd HHmmss
	 * longFmt14   yyyyMMddhhmmss
	 * shortFmt10  yyyy-MM-dd
	 * shortFmtD8  yyyyMMdd
	 * shortFmtT8  HH:mm:ss             */
	public static String getNowString(String pattern){
		Date crtTime = new Date();
		SimpleDateFormat formatter = getFmt(pattern);
		String dateString = formatter.format(crtTime);
		formatter = null;
		crtTime = null;
		return dateString;		
	}
	
	/**根据格式将  传入日期Date转换成相应格式的String
	 * @param pattern:
	 * longFmt19   yyyy-MM-dd HH:mm:ss
	 * longFmt15   yyyyMMdd HHmmss
	 * shortFmt10  yyyy-MM-dd
	 * shortFmtT8   HH:mm:ss          */
	public static String dateToStringByFmt(Date date, String pattern){
		SimpleDateFormat fmtter = getFmt(pattern);
		String dateString = fmtter.format(date);
		fmtter = null;
		return dateString;
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////
	 
	/** US格式化的日期转化为CN的格式 (yyyy-MM-dd HH:mm:ss)
	 * US格式例如：Sat 26/Jun/2010:18:41:25 +0800 */
	public static String usDateFormat(String date) {
		SimpleDateFormat f = null;
        try {
        	if(StringTools.count(date, " ") == 2) {
        		f = new SimpleDateFormat("EEE dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
        	} else {
        		f = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
        	}
            Date d = f.parse( date );
            f = new SimpleDateFormat( longFmt19 );
            date = f.format(d);
        } catch (ParseException e) {
        	log.error("US格式日期转化报错：", e);
        	return null;
        }
        return date;
	}
	
	public static Date usDateFormat2Date(String date) {
		SimpleDateFormat f = null;
        try {
        	if(StringTools.count(date, " ") == 2) {
        		f = new SimpleDateFormat("EEE dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
        	} else {
        		f = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z",Locale.US);
        	}
            return f.parse( date );
        } catch (ParseException e) {
        	log.error("US格式日期转化报错：", e);
        	return null;
        }
	}
	
	/** 格式化日期的字符串 */
	public static String dateFormat(String date, String format){
		try {
			if( !StringTools.isTrimNull(date) ) {
				DateFormat df = DateFormat.getDateTimeInstance();
				//df = DateFormat.getDateInstance();
				Date d = df.parse(date);
				date = dateToStringByFmt(d, format);
				df = null;
				d = null;
				return date;
			}
		} catch (ParseException e) {
			log.error("格式化日期的字符串出错："+ date, e);
			e.printStackTrace();
		}
		return date;
	}
	
	/** 格式化日期的字符串
	 *@param date : "07/17/2008 10:10:51 AM"
	 *@param date : "07/17/2008 03:10:51 PM"   */
	public static String dateFormat(String date) {
		try {
			if( !StringTools.isTrimNull(date) ) {
				if(StringUtils.contains(date, "/")) {
					SmartString s = new SmartString("-");
					String[] ss = StringUtils.split(date, " ");
					if(ss.length == 3) {
						s.append( StringUtils.substringAfterLast(ss[0], "/") );
						s.append( StringUtils.substringBefore(ss[0], "/") );
						s.append( StringUtils.substringBetween(ss[0], "/", "/") );
						if( StringUtils.equalsIgnoreCase(ss[2], "PM") ){
							ss = StringUtils.split(ss[1], ":");
							ss[0] = ""+ (Integer.valueOf(ss[0]).intValue() + 12);
							date = (ss[0] +":"+ ss[1] +":"+ ss[2]);
							ss = null;
						}else{
							date = ss[1];
							ss = null;
						}
						date = (s.toString() +" "+ date);
					}
					s = null;
					ss = null;
				}
			}
		} catch (Exception e) {
			log.error("格式化日期的字符串出错："+ date, e);
			e.printStackTrace();
		}
		return date;
	}
	

	
	/**
	 * 得到现在时间
	 * @return Date	  */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}
	
	/**
	 * 得到现在时间
	 * @return String	  */
	public static String getNow(String pattern) {
		return getNowString(pattern);
	}
	
	/** 获得当天的日期：YYYY-MM-dd */
	public static String getToday(){
		return getNowString(shortFmt10);
	}
	
	/** 获得现在的时间：YYYY-MM-dd HH:mm:ss  */
	public static String getNowTime(){
		return getNowString(longFmt19);
	}
	
	
	/** 获取今天以前（负）、以后（正）的第{days}天的日期
	 * @param days: 今天以前的第n天，为负整数，以后为正整数    */
	public static String getAgoBackDate(int days, String pattern) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if(days != 0) cal.add(Calendar.DAY_OF_MONTH, days);
		date = cal.getTime();
		
		String dateStr = dateToStringByFmt(date, pattern);
		date = null;
		return dateStr;
	}
	
	/** 获取相对系统当前日期<往前>或<往后>的n天日期, 格式：YYYY-MM-dd
	 * @param n : -1, 1 */
	public static String getAgoBackDate(int n){
		String now = getNowString(shortFmt10);
		if(n == 0){
			return now;
		}else{
			return getNextDay(now, n);
		}
	}
	
	
	
	
	/**
	 * 提取一个月中的最后一天
	 * @param day
	 * @return Date	  */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 24 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}
	
	/**
	 * 得到现在小时
	 * @return String	  */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = getFmt(longFmt19);
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}
	
	/**
	 * 得到现在分钟
	 * @return String	  */
	public static String getMinute() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = getFmt(longFmt19);
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}
	
	/**获取现在的HH:mm 小时和分钟
	 * @return String HH:mm      */
	public static String getNowHHmm(){
		return DateUtil.getNowString(DateUtil.shortFmtT8).substring(0, 5);//HH:mm
	}
	
	/**获取现在的HH:mm:ss 小时和分钟
	 * @return String HH:mm      */
	public static String getNowHHmmss(){
		return getNowString(shortFmtT8).substring(0, 8);//HH:mm
	}
	
	/** 判断值是否是HH:mm:ss的格式  */
	public static boolean isHHmmss(String hhmmss) {
		if( !StringTools.isTrimNull(hhmmss) ) {
			return Pattern.matches("[0-9]{2}:[0-9]{2}:[0-9]{2}", hhmmss);
		}
		return false;
	}
	
	
	/** 计算当天两个时间(HH:mm:ss)的秒钟差  */
	public static long compare2Date(String startHHmmss, String endHHmmss) {
		long s = DateUtil.strToDateByFmt(startHHmmss, DateUtil.shortFmtT8).getTime();
		long e = DateUtil.strToDateByFmt(endHHmmss, DateUtil.shortFmtT8).getTime();
		long seconds = (s - e) / 1000; //s
		return seconds;
	}
	
	/** 计算HH:mm:ss相加/减deltaMinute分钟后的值 */
	public static String plusMinute(String HHmmss, int deltaMinute) {
		long l = DateUtil.strToDateByFmt(HHmmss, DateUtil.shortFmtT8).getTime();
		l += (deltaMinute * 60 * 1000);
		Date date = new Date( l );
		return dateToStringByFmt(date, shortFmtT8);
	}
	
	
	/**
	 * 计算两个时分的差值，返回字符型的      小时数
	 * @param minHm < bigHm        格式：HH:mm
	 * @return String	  */
	public static String getTwoHour(String minHm, String bigHm) {
		String[] min = null;
		String[] big = null;
		min = minHm.split(":");
		big = bigHm.split(":");
		if("0".equals(min[0].trim()))
			min[0]="24";
		if("0".equals(big[0].trim()))
			big[0]="24";
		if (Integer.parseInt(min[0])>Integer.parseInt(big[0]))
			return "0";
		else {
			double y = Double.parseDouble(min[0]) + Double.parseDouble(min[1]) / 60;
			double u = Double.parseDouble(big[0]) + Double.parseDouble(big[1]) / 60;
			if(y>u)
				return "0";
			else
				return u-y+"";
		}
	}
	
	/**计算两个时分的差值，返回int型的      分钟数
	 * @param minHm < bigHm        格式：HH:mm
	 * @return double	  */
	public static double getTwoMinute(String minHm, String bigHm){
		String[] min = null;
		String[] big = null;
		min = minHm.split(":");
		big = bigHm.split(":");
		if("00".equals(min[0].trim()))
			min[0]="24";
		if("00".equals(big[0].trim()))
			big[0]="24";
		if (Integer.parseInt(min[0])>Integer.parseInt(big[0]))
			return 0;
		else {
			double m = Double.parseDouble(min[0])*60 + Double.parseDouble(min[1]);
			double b = Double.parseDouble(big[0])*60 + Double.parseDouble(big[1]);
			if(m>b){
				return 0;
			}
			return b-m;
		}	 
	}
	
	
	
	/**
	 * 判断Date(yyyy-MM-dd)日期是否是在参照日期以前
	 * @param date： yyyy-MM-dd, 判定日期
	 * @param refer：yyyy-MM-dd, 参照日期
	 * @return boolean	  */
	public static boolean isAgoRefer(String date, String refer) {
		long day = getDays(date, refer);
		return (day < 0);
	}
	
	/**
	 * 判断Date(yyyy-MM-dd)日期是否是在参照日期以后
	 * @param date:  yyyy-MM-dd, 判定日期
	 * @param refer：yyyy-MM-dd, 参照日期
	 * @return boolean	  */
	public static boolean isAfterRefer(String date, String refer) {
		long day = getDays(date, refer);
		return (day > 0);
	}
	
	
	/**
	 * 得到二个日期间的间隔天数
	 * @param sj1 , sj2 yyyy-MM-dd
	 * @return String	  */
	public static String getTwoDay(String sj1, String sj2) {
		long day = 0;
		try {
			SimpleDateFormat myFormatter = getFmt(shortFmt10);
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			myFormatter = null;
			mydate = null;
			date = null;
		} catch (Exception e) {
			return "0";
		}
		return ""+ day;
	}
	
	/**
	 * 时间前推或后推分钟,其中mm表示分钟.
	 * @param sj1 , mm  yyyy-MM-dd HH:mm:ss
	 * @return String	  */
	public static String getPreTime(String sj1, String mm) {
		SimpleDateFormat format = getFmt(longFmt19);
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(mm) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
			log.error("getPreTime is error: "+e.getMessage());
			return sj1;
		}
		return mydate1;
	} 
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * @param nowdate: YYYY-MM-dd
	 * @param delay: int(-1, 1)
	 * @return String  YYYY-MM-dd  */
	public static String getNextDay(String nowdate, int delay) {
		try{
			SimpleDateFormat format = getFmt(shortFmt10);
			String mdate = "";
			Date d = strToDateByFmt(nowdate, shortFmt10);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		}catch(Exception e){
			return "";
		}
	} 
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * @param nowdate: dateFormat
	 * @param delay: int(-1, 1)
	 * @return String  dateFormat */
	public static String getNextDay(String nowdate, int delay,String dateFormat) {
		try{
			SimpleDateFormat format = getFmt(dateFormat);
			String mdate = "";
			Date d = strToDateByFmt(nowdate, dateFormat);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		}catch(Exception e){
			return "";
		}
	} 
	
	/**
	 * 判断是否润年 
	 * @param ddate yyyy-MM-dd
	 * @return boolean	  */
	public static boolean isLeapYear(String ddate) { 
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 4.能被4整除同时能被100整除则不是闰年	   */
		Date d = strToDateByFmt(ddate, shortFmt10);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	} 
	
	/**
	 * 返回美国时间格式 26 Apr 2006
	 * @param str yyyy-MM-dd
	 * @return String	  */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = getFmt(shortFmt10);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	} 
	
	/**
	 * 获取一个月的最后一天
	 * @param dat yyyy-MM-dd
	 * @return String	  */
	public static String getEndDateOfMonth(String dat) {
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}
	
	/**
	 * 判断两个String类型的日期是否是在同一个周
	 * @param start,end   yyyy-MM-dd (HH:mm:ss)
	 * @return  boolean    */
	public static boolean isSameWeek(String start,String end){
		Date dt1 = null;
		Date dt2 = null;
		if(start.length()==10&&end.length()==10){
			dt1 = strToDateByFmt(start, DateUtil.shortFmt10);
			dt2 = strToDateByFmt(end, DateUtil.shortFmt10);
		}else if(start.length()==19&&end.length()==19){
			dt1 = strToDateByFmt(start, DateUtil.longFmt19);
			dt2= strToDateByFmt(end, DateUtil.longFmt19);
		}else{
			return false;
		}
		return isSameWeekDates(dt1, dt2);
	}
	
	/**
	 * 判断两个时间是否在同一个周
	 * @param date1 date2     Date
	 * @return    boolean	  */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		if(date1==null||date2==null){
			return false;
		}
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	
	
	
	
	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * @return String	  */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	} 
	
	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * @param sdate yyyy-MM-dd
	 * @param num
	 * @return String	  */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtil.strToDateByFmt(sdate, shortFmt10);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	} 
	
	/**
	 * 根据一个日期，返回是星期几的字符串
	 * @param sdate yyyy-MM-dd
	 * @return String	  */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDateByFmt(sdate, shortFmt10);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}
	
	/**
	 * 获取Current当前日期的星期数字<br>
	 * @return 当前日期的星期: 0~6  */
	public static int getCurrWeek() {
		Date dt = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) w = 0;
		return w;
	}
	
	/**
	 * 获取YYYY-MM-dd日期的星期数字<br>
	 * @param date: yyyy-MM-dd
	 * @return 当前日期的星期: 0~6  */
	public static int getWeekInt(String date) {
		Date dt = strToDateByFmt(date, shortFmt10);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) w = 0;
		return w;
	}
	
	/**
	 * 获取当前日期是星期几<br>
	 * @param dt
	 * @return 当前日期是星期几         */
	public static String getWeekOfDate(String date) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		int w = getWeekInt(date);
		return weekDays[w];
	}
	
	
	/**
	 * 获取当前日期的星期汉字数<br>
	 * @return 当前日期的星期: 一、二...  */
	public static String getNowWeek() {
		String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
		int w = getCurrWeek();
		return weekDays[w];
	}
	
	/**
	 * 根据Current当前日期的星期数字，计算往前一周的天数<br>
	 * @return 当前日期的星期: 0~6  */
	public static int getAgoWeekDays(){
		int w = getCurrWeek();
		if(w > 1) return -w;
		if(w == 0) {
			return -6;
		}else {//w == 1
			return -7;
		}
	}
	
	
	/**
	 * 根据Current当前日期的星期数字，计算往前一个工作日的日期<br>
	 * @return 往前一工作日期: yyyy-MM-dd   */
	public static String getAgoOneWorkDay(){
		int w = getCurrWeek();
		if(w == 0){//星期日
			return getAgoBackDate(-2);
		}else if(w == 1) {//星期一
			return getAgoBackDate(-3);
		}else {//w >= 2
			return getAgoBackDate(-1);
		}
	}
	
	
	/**
	 * 获取当前日期是星期几的一个int值<br>
	 * @param dt
	 * @return 当前日期是星期几  int        */
	public static int getWkIntOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		
		return w;
	}
	
	
	/**
	 * 根据日期来获取它是在星期几的汉字数<br>
	 * @param longDate   yyyy-MM-dd HH:mm:ss
	 * @return String 日期的星期几: 一、二...  */
	public static String getWeekCNword(String longDate){
		String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
		Date dt = strToDateByFmt(longDate, longFmt19);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		
		return weekDays[w];
	}
	
	
	/**
	 * 根据一个日期格式的字符串，获得星期几
	 * @param sdate yyyy-MM-dd
	 * @return "星期几"         */
	public static String getWeekStr(){
		//String sdate
		//String str = DateUtil.getWeek(sdate);
		int w = getCurrWeek();
		switch (w) {
		case 0:
			return "星期日";
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		default:
			return "";
		}
	}
	
	
	/**
	 * 获取两个时间之间的天数
	 * @param date1,date2: yyyy-MM-dd
	 * @return long: (date1 - date2)天数	  */
	public static long getDays(String date1, String date2) {
		if (date1 == null || "".equals(date1)) 
			date1 = getNow(shortFmt10);
		if (date2 == null || "".equals(date2)) return 0;
		long day = 0;
		try {
			// 转换为标准时间
			SimpleDateFormat myFormatter = getFmt(shortFmt10);
			java.util.Date d1 = myFormatter.parse(date1);
			java.util.Date d2 = myFormatter.parse(date2);
			day = (d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000);
			d1 = null; d2 = null;
			myFormatter = null;
		} catch (Exception e) {
			log.error("get 2 Date days is error: "+e.getMessage());
			day = 0;
		}
		return day;
	}
	
	/**
	 * 获取两个时间之间的小时数
	 * @param date1, date2: yyyy-MM-dd HH:mm:ss
	 * @return long: (date1 - date2)小时数	  */
	public static long getHours(String date1, String date2) {
		if (date1 == null || "".equals(date1)) 
			date1 = getNow(longFmt19);
		if (date2 == null || "".equals(date2)) return 0;
		long min = 0;
		try {
			// 转换为标准时间
			SimpleDateFormat myFormatter = getFmt(longFmt19);
			java.util.Date d1 = myFormatter.parse(date1);
			java.util.Date d2 = myFormatter.parse(date2);
			min = (d1.getTime() - d2.getTime()) / 24;
			d1 = null; d2 = null;
			myFormatter = null;
		} catch (Exception e) {
			log.error("get 2 Date days is error: "+e.getMessage());
			min = 0;
		}
		return min;
	}
	
	/**
	 * 获取两个时间之间的分钟数
	 * @param date1, date2: yyyy-MM-dd HH:mm:ss
	 * @return long: (date1 - date2)分钟数	  */
	public static long getMinutes(String date1, String date2) {
		if (date1 == null || "".equals(date1)) 
			date1 = getNow(longFmt19);
		if (date2 == null || "".equals(date2)) return 0;
		long min = 0;
		try {
			// 转换为标准时间
			SimpleDateFormat myFormatter = getFmt(longFmt19);
			java.util.Date d1 = myFormatter.parse(date1);
			java.util.Date d2 = myFormatter.parse(date2);
			min = (d1.getTime() - d2.getTime()) / (24 * 60 * 60);
			d1 = null; d2 = null;
			myFormatter = null;
		} catch (Exception e) {
			log.error("get 2 Date days is error: "+e.getMessage());
			min = 0;
		}
		return min;
	}
	
	/**
	 * 获取两个时间之间的秒钟数
	 * @param date1, date2: yyyy-MM-dd HH:mm:ss
	 * @return long: (date1 - date2)秒钟数	  */
	public static long getSeconds(String date1, String date2) {
		if (date1 == null || "".equals(date1)) 
			date1 = getNow(longFmt19);
		if (date2 == null || "".equals(date2)) return 0;
		long scd = 0;
		try {
			// 转换为标准时间
			SimpleDateFormat myFormatter = getFmt(longFmt19);
			java.util.Date d1 = myFormatter.parse(date1);
			java.util.Date d2 = myFormatter.parse(date2);
			scd = (d1.getTime() - d2.getTime()) / (24 * 60 * 60);
			d1 = null; d2 = null;
			myFormatter = null;
		} catch (Exception e) {
			log.error("get 2 Date days is error: "+e.getMessage());
			scd = 0;
		}
		return scd;
	}
	
	
	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * @param sdate   yyyy-MM-dd
	 * @return String	  */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01"; 
		
		// 得到这个月的1号是星期几
		Date date = DateUtil.strToDateByFmt(sdate, shortFmt10);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, (1 - u));
		return newday;
	} 
	
	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * @param k 表示是取几位随机数，可以自己定
	 * @return String	  */
	public static String getNo(int k) { 
		return DateUtil.getNowString(longFmt14) + getRandom(k);
	} 
	
	/**
	 * 返回一个随机数 
	 * @param i
	 * @return String	  */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	} 
	
	/**
	 * 判断String是否是Date格式
	 * @param data      yyyy-MM-dd (HH:mm:ss)
	 * @return boolean  	  */
	public static boolean rightDate(String date) { 
		SimpleDateFormat sdf = getFmt(longFmt19);
		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = getFmt(longFmt19);
		} else {
			sdf = getFmt(shortFmt10);
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	} 
	
	/** 获取传入的sdate相应的格式日期
	 * @param sdate  yyyy-MM-dd
	 * @param nd=1表示返回的值中包含年度
	 * @param yf=1表示返回的值中包含月份
	 * @param rq=1表示返回的值中包含日期 //
	 * @param format表示返回的格式 :
	 *        1 以年月日中文返回      2 以横线-返回      3 以斜线/返回
	 *        4 以缩写不带其它符号形式返回     5 以点号.返回	  **/
	public static String getStringDateMonth(String sdate, String nd, String yf, String rq, String format) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = getFmt(shortFmt10);
		String dateString = formatter.format(currentTime);
		String s_nd = dateString.substring(0, 4); // 年份
		String s_yf = dateString.substring(5, 7); // 月份
		String s_rq = dateString.substring(8, 10); // 日期
		String sreturn = "";
		
		if (sdate == null || sdate.equals("") || !isDate(sdate)) { // 处理空值情况
			if (nd.equals("1")) {
				sreturn = s_nd;
				// 处理间隔符
				if (format.equals("1"))
					sreturn = sreturn + "年";
				else if (format.equals("2"))
					sreturn = sreturn + "-";
				else if (format.equals("3"))
					sreturn = sreturn + "/";
				else if (format.equals("5"))
					sreturn = sreturn + ".";
			}
			// 处理月份
			if (yf.equals("1")) {
				sreturn = sreturn + s_yf;
				if (format.equals("1"))
					sreturn = sreturn + "月";
				else if (format.equals("2"))
					sreturn = sreturn + "-";
				else if (format.equals("3"))
					sreturn = sreturn + "/";
				else if (format.equals("5"))
					sreturn = sreturn + ".";
			}
			// 处理日期
			if (rq.equals("1")) {
				sreturn = sreturn + s_rq;
				if (format.equals("1"))
					sreturn = sreturn + "日";
			}
		} else {
			// 不是空值，也是一个合法的日期值，则先将其转换为标准的时间格式
			sdate =getOKDate(sdate);
			s_nd = sdate.substring(0, 4); // 年份
			s_yf = sdate.substring(5, 7); // 月份
			s_rq = sdate.substring(8, 10); // 日期
			if (nd.equals("1")) {
				sreturn = s_nd;
				// 处理间隔符
				if (format.equals("1"))
					sreturn = sreturn + "年";
				else if (format.equals("2"))
					sreturn = sreturn + "-";
				else if (format.equals("3"))
					sreturn = sreturn + "/";
				else if (format.equals("5"))
					sreturn = sreturn + ".";
			}
			// 处理月份
			if (yf.equals("1")) {
				sreturn = sreturn + s_yf;
				if (format.equals("1"))
					sreturn = sreturn + "月";
				else if (format.equals("2"))
					sreturn = sreturn + "-";
				else if (format.equals("3"))
					sreturn = sreturn + "/";
				else if (format.equals("5"))
					sreturn = sreturn + ".";
			}
			// 处理日期
			if (rq.equals("1")) {
				sreturn = sreturn + s_rq;
				if (format.equals("1"))
					sreturn = sreturn + "日";
			}
		}
		return sreturn;
	} 
	
	/**获取-m或m个月后当前的日期
	 * @param sdate   yyyy-MM-dd  ,   m(-int/int)
	 * @return String    */
	public static String getNextMonthDate(String sdate, int m) {
		sdate = getOKDate(sdate);
		int year = Integer.parseInt(sdate.substring(0, 4));
		int month = Integer.parseInt(sdate.substring(5, 7));
		String day = sdate.substring(8,10);
		month = month + m;
		if (month < 0) {
			month = month + 12;
			year = year - 1;
		} else if (month > 12) {
			month = month - 12;
			year = year + 1;
		}
		String smonth = "";
		if (month < 10)
			smonth = "0" + month;
		else
			smonth = "" + month;
		return year + "-" + smonth + "-"+day;
	} 
	
	/**将一个合法的String转换为标准的时间格式
	 * @param sdate  yyyy-MM-dd
	 * @return String   */
	public static String getOKDate(String sdate) {
		if (sdate == null || "".equals(sdate))
			return getNowString(shortFmt10); 
		// 将"/"转换为"-"
		sdate = StringUtils.replace(sdate, "/", "-");
		// 如果只有8位长度，则要进行转换
		if (sdate.length() == 8)
			sdate = sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-" + sdate.substring(6, 8);
		if (!isDate(sdate)) {
			sdate = getNowString(shortFmt10);
		}
		
		SimpleDateFormat formatter = getFmt(shortFmt10);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(sdate, pos);
		String dateString = formatter.format(strtodate);
		formatter = null;
		strtodate = null;
		pos = null;
		return dateString;
	} 
	
	/**判断是否是标准Date格式的String,可以适应:
	 * "yyyy-MM-dd" "yyyy-M-d" "yyyy-MM-d" "yyyy-M-dd"
	 * @param sdate
	 * @return boolean    */
	public static boolean isDate(String sdate){
		try {    
			SimpleDateFormat sdf = getFmt(shortFmtMd);
			sdf.setLenient(false);
			sdate = sdate.replaceAll("-0", "-");
			Date d = sdf.parse(sdate);
			String s = sdf.format(d);
			
			sdf = null;
			d = null;
			return sdate.equals(s);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**比较两个日期差几D几H几M几S
	 * @param start , end 格式yyyy-MM-dd HH:mm:ss  
	 * @return String    */
	public static String compare2Dates(String start,String end){
		SimpleDateFormat df = getFmt(longFmt19);
		Date sdate = null;
		Date edate = null;
		try {
			sdate = df.parse(start);
			edate = df.parse(end);
			long l=sdate.getTime()-edate.getTime();
			long day=l/(24*60*60*1000);
			long hour=(l/(60*60*1000)-day*24);
			long min=((l/(60*1000))-day*24*60-hour*60);
			long s=(l/1000-day*24*60*60-hour*60*60-min*60);
			return day+"天"+hour+"小时"+min+"分"+s+"秒";
		} catch (ParseException e) {
			log.error("2 date compare is error: "+e.getMessage());
			return null;
		}		 
	}
	
	/** 计算两个时间的秒钟差  */
	public static long compare2Date(Date ago, Date late){
		long seconds = 0l;
		if(ago != null && late != null){
			seconds = (late.getTime() - ago.getTime()) / 1000; //s
		}
		return seconds;
	}
	
	
	
	/**比较当天两个时刻差几时几分几秒
	 * @param start , end 格式HH:mm:ss
	 * @return String    */
	public static String compareNowDay2Times(String start , String end){
		String[] st = start.trim().split(":");
		String[] et = end.trim().split(":");
		long h = Long.valueOf(et[0])-Long.valueOf(st[0]);
		
		long m ;
		long s ;
		if(Long.valueOf(et[1]) < Long.valueOf(st[1])){
			m = 60 - Long.valueOf(st[1])+ Long.valueOf(et[1]);
		}else{
			m = Long.valueOf(et[1])-Long.valueOf(st[1]);
		}
		if(Long.valueOf(et[2]) < Long.valueOf(st[2])){
			s = 60 - Long.valueOf(st[2])+ Long.valueOf(et[2]);
		}else{
			s = Long.valueOf(et[2])-Long.valueOf(st[2]);
		}
		
		return h+"小时"+m+"分钟"+s+"秒";
	}
	
	/**判断一个日期是否 在一个日期段内
	 * @param String start <  end , itself 格式：YYYY-MM-DD
	 * @return boolean   */
	public static boolean isInSpanDate(String start, String end, String itself){
		boolean flag = false;
		long s = strToDateByFmt(start, shortFmt10).getTime();
		long e = strToDateByFmt(end, shortFmt10).getTime();
		long i = strToDateByFmt(itself, shortFmt10).getTime();
		if(i>=s&&i<=e){
			flag = true;
		}
		return flag;
	}
	
	/**判断一个时分是否在当天的一个时分段内
	 * @param String start < end ,itself  格式：HH:mm 
	 * @return boolean     */
	public static boolean isInSpanHHmm(String start, String end, String itself){
		boolean flag = false;
		int sHm = Integer.valueOf(start.replace(":", "")).intValue();
		int eHm = Integer.valueOf(end.replace(":", "")).intValue();
		int iHm = Integer.valueOf(itself.replace(":", "")).intValue();
		if(iHm>=sHm && iHm<=eHm){
			flag = true;
		}
		return flag;
	}
	
	
	
	/**将一个日期类型的字符串转换成标准的日期字符串
	 * @param String strDate: yyyyMMdd
	 * @return normal String of date: yyyy-MM-dd    */
	public static String toFormatDate(String strDate) {
		if(StringUtils.isNotBlank(strDate) && strDate.length() == 8) {
			String yyyy = strDate.substring(0, 4);
			String MM = strDate.substring(4, 6);
			String dd = strDate.substring(6, 8);
			return (yyyy +"-"+ MM +"-"+ dd);
		}
		return strDate;
	}
	
	/**将一个日期类型的字符串转换成标准的日期字符串
	 * @param String strTime: HHmmss
	 * @return normal String of date: HH:mm:ss    */
	public static String toFormatTime(String strTime) {
		if(StringUtils.isNotBlank(strTime) && strTime.length() == 6) {
			String HH = strTime.substring(0, 2);
			String mm = strTime.substring(2, 4);
			String ss = strTime.substring(4, 6);
			return (HH +":"+ mm +":"+ ss);
		}
		return strTime;
	}
	
	/**将一个日期类型的字符串转换成标准的日期字符串
	 * @param String strDate: yyyyMMdd
	 * @param String strTime: HHmmss
	 * @return normal String of date:  yyyy-MM-dd HH:mm:ss*/
	public static String toFormatDate(String strDate, String strTime) {
		return (toFormatDate(strDate) +" "+ toFormatTime(strTime));
	}
	
	/**将长日期格式的字符串截成短的日期字符串
	 * @param String longDate: yyyy-MM-dd (HH:mm:ss)
	 * @return String shortDate :yyyy-MM-dd   */
	public static String getPartByLongStrdate(String longDate){
		if(longDate!=null&&!longDate.isEmpty()&&longDate.length()>=10){
			return longDate.substring(0, 10);
		}
		return getNowString(shortFmt10);
	}
	
	/**将长日期格式的字符串截成短的日期字符串
	 * @param String longDate: yyyy-MM-dd HH:mm:ss
	 * @return String backDate: HH:mm:ss   */
	public static String getBackPartDate(String longDate){
		if(longDate!=null&&!longDate.isEmpty()&&longDate.length()>=19){
			return longDate.substring(11, 19);
		}
		return getNowString(shortFmtT8);
	}
	
	
	/**获取日期格式的年份数字
	 * @param  String strDate  yyyy-MM-dd (HH:mm:ss)
	 * @return int 4位数        参数据Error返回0   */
	public static int getYearNum(String strDate){
		if(strDate.length()>=4){
			return Integer.valueOf(strDate.substring(0, 4));
		}
		return 0;
	}
	
	/**获取日期格式的月份数字
	 * @param  String strDate  yyyy-MM-dd (HH:mm:ss)
	 * @return int 1-12           参数Error返回0     */
	public static int getMonthNum(String strDate) {
		if(strDate.length()>=8){
			return Integer.valueOf(strDate.substring(5, 7));
		}
		return 0;
	}	 
	
	
	
	
	
	/**判断两个标准格式的日期时间是否在同一分钟内
	 * @param String agoTime < lateTime :  yyyy-MM-dd HH:mm:ss  */
	public static boolean isInOneMinute(String agoTime,String lateTime){
		if(agoTime.length()==19 && lateTime.length()==19){
			long at = (strToDateByFmt(agoTime, longFmt19).getTime())/1000;
			long lt = (strToDateByFmt(lateTime, longFmt19).getTime())/1000;
			long span = lt-at;
			String atss = agoTime.substring(agoTime.length()-2, agoTime.length());
			int ssp = 60 - Integer.valueOf(atss).intValue();
			
			if(span<=0){
				return false;
			}else if(span>0 && span<ssp){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	/**判断两个标准格式的日期是否是在同一天内
	 * @param String agoTime < lateTime:  yyyy-MM-dd (HH:mm:ss)  */
	public static boolean isInOneDay(String agoTime,String lateTime){
		if(getPartByLongStrdate(agoTime).equals(getPartByLongStrdate(lateTime))){
			return true;
		}
		return false;
	}
	
	/**判断日期是否在指定的日期范围内    */
	public static boolean isInCycle(String date, String startDate, String endDate){
		if(getDays(date, DateUtil.getOKDate(startDate)) >= 0 && getDays(date, DateUtil.getOKDate(endDate))  <= 0){
			return true;
		}
		return false;
	}
	
	/** 获取当前日期在当前<月份>的日期段 */
	public static String[] getNowMonthSpan(){
		String[] span = new String[2];
		String nowDate = getNowString(shortFmt10);
		String[] s = StringUtils.split(nowDate, "-");
		if("01".equals(s[1]) && "01".equals(s[2])){
			int y = Integer.valueOf(s[0]).intValue();
			span[0] = (y - 1) + "-12" + "-01";
			span[1] = s[0] + "-" + s[1] + "-" + s[2];
		}else if( !"01".equals(s[1]) && "01".equals(s[2]) ){
			int m = Integer.valueOf(s[1]).intValue();
			if(m <= 10){
				span[0] = s[0] + "-0" + (m-1) + "-01";
			}else{
				span[0] = s[0] + "-" + (m-1) + "-01";
			}
			span[1] = s[0] + "-" + s[1] + "-" + s[2];
		}else{
			span[0] = s[0] + "-" + s[1] + "-01";
			span[1] = s[0] + "-" + s[1] + "-" + s[2];
		}
		return span;
	}
	
	
	/** 获取当前日期在当前<星期>的日期段 */
	public static String[] getNowWeekSpan(){
		String[] span = new String[2];
		String nowDate = getNowString(shortFmt10);
		int week = getWkIntOfDate(strToDateByFmt(nowDate, shortFmt10));
		if(week == 1 || week == 7)
			span[0] = getNextDay(nowDate, -7);
		else{
			int delay = week - 9;
			span[0] = getNextDay(nowDate, delay);
		}
		span[1] = nowDate;
		return span;
	}
	
	/** 获取当前日期在当前<day>天之前的日期段 */
	public static String[] getNowDaySpan(int day){
		String[] span = new String[2];
		String nowDate = getNowString(shortFmt10);
		span[0] = getNextDay(nowDate, day);
		span[1] = nowDate;
		return span;
	}
	
	/** 获取当前日期再<往前一周>的日期段 */
	public static String[] getAgoDaySpan(){
		String[] span = new String[2];
		String nowDate = getNowString(shortFmt10);
		int day = getAgoWeekDays();
		span[0] = getNextDay(nowDate, day);
		span[1] = nowDate;
		return span;
	}
	
	
	
	
	/** 获取"yyyyMMdd"格式日期的间戳（单位:秒）
	 *@notice 参数日期格式 为"yyyyMMdd"    */
	public static long getiDateSec(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(shortFmtD8);
			Date dt = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			log.error("获取当日0点时间戳失败。");
			return 0;
		}
	}
	/** 获取"yyyy-MM-dd"格式日期的间戳（单位:秒）
	  *@notice 参数日期格式 为"yyyy-MM-dd"    */
	public static long getsDateSec(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(shortFmt10);
			Date dt = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			log.error("获取当日0点时间戳失败。");
			return 0;
		}
	}
	/** 获取"yyyyMMdd"或"yyyy-MM-dd"格式日期的间戳（单位:秒）
	 *@notice 参数日期格式 为"yyyyMMdd"或"yyyy-MM-dd"    */
	public static long getDateSec(String date) {
		if( StringTools.isTrimNull(date) ) return 0;
		
		if(date.length() == 8 && !StringUtils.contains(date, "-")) {
			return getiDateSec(date);
		}
		if(date.length() == 10 && StringUtils.contains(date, "-")) {
			return getsDateSec(date);
		}
		return 0;
	}
	
	
	/** 获取"HH:mm:ss"格式日期的间戳（单位:秒）
	 *@notice 参数日期格式 为"HH:mm:ss"    */
	public static long getTimeSec(String time) {
		long totalSec = 0l;
		if(StringUtils.countMatches(time, ":") == 2 && time.length() == 8) {
			String[] my = StringUtils.split(time, ":");
			int hour = Converter.parseInt(my[0]);
			int min = Converter.parseInt(my[1]);
			int sec = Converter.parseInt(my[2]);
			my = null;
			totalSec = (hour*3600 + min*60 + sec);
		}
		return totalSec;
	}
	
	/** 获取"yyyy-MM-dd HH:mm:ss"格式日期的间戳（单位:秒）
	  *@notice 参数日期格式 为"yyyy-MM-dd HH:mm:ss"    */
	public static long getDateTimeSec(String dateTime) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(longFmt19);
			Date date = format.parse(dateTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			log.error("获取当日0点时间戳失败。");
			return 0;
		}
	}
	
	
	
	/** 将时间戳（单位:秒）参数转换为：日期格式 "yyyy-MM-dd" */
	public static String toDate(long longDate) {
		Date date = new Date(longDate * 1000);
		String time = dateToStringByFmt(date, shortFmt10);
		date = null;
		return time;
	}
	
	/** 将时间戳（单位:秒）参数转换为：日期格式 format */
	public static String toDate(long longDate,String format) {
		Date date = new Date(longDate * 1000);
		String time = dateToStringByFmt(date, format);
		date = null;
		return time;
	}
	
	/** 将时间戳（单位:秒）参数转换为：日期格式 "HH:mm:ss" */
	public static String toTime(long longTime) {
		Date date = new Date(longTime * 1000);
		SimpleDateFormat fmtter = getFmt(shortFmtT8);
		fmtter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		String time = fmtter.format(date);
		fmtter = null;
		date = null;
		return time;
	}
	/** 将时间戳（单位:秒）参数转换为：日期格式 "yyyy-MM-dd HH:mm:ss" */
	public static String toDateTime(long longDateTime) {
		Date date = new Date(longDateTime * 1000);
		String time = dateToStringByFmt(date, longFmt19);
		date = null;
		return time;
	}
	
	/** 获取两个“yyyy-MM-dd / yyyyMMdd”格式之间的所有天数的日期
	 * @param isClosed：是否是闭区间  */
	public static Set<String> getBetweenDates(String sYYYYmmdd, String eYYYYmmdd, boolean isClosed) {
		Set<String> dates = new TreeSet<String>();
		String dateFormat = shortFmtD8;
		if(sYYYYmmdd.length() >= 10 || StringUtils.containsIgnoreCase(sYYYYmmdd, "-"))
			dateFormat = shortFmt10;
		
		Date s = strToDateByFmt(sYYYYmmdd, dateFormat);
		Calendar start = Calendar.getInstance();
		start.setTime( s );
		
		Date e = strToDateByFmt(eYYYYmmdd, dateFormat);
		Calendar end = Calendar.getInstance();
		end.setTime( e );
		
		if( isClosed ) dates.add(sYYYYmmdd);
        while ( start.before(end) ) {
            dates.add( dateToStringByFmt(start.getTime(), dateFormat) );
            start.add(Calendar.DAY_OF_YEAR, 1);
        }
        if( isClosed ) dates.add(eYYYYmmdd);
        return dates;
	}
	
	
	public static void main(String[] args) {
		Set<String>  dates = getBetweenDates("20151101", "20151111", true);
		String[] ds = Converter.toArray(dates);
		
		if(ds != null && ds.length > 0) {
			for(String date : ds){
				System.out.println(date);
			}
		}
		
	}
	
	
	
}
