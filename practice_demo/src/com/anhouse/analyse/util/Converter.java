package com.anhouse.analyse.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;


/**   
 * @Title: TODO 
 * @Description: TODO(用一句话描述该类文件做什么)
 * @Team: 技术1部Java开发小组
 * @Author Andy-ZhichengYuan   
 * @Date 2014年12月9日
 * @Version V1.0   */
public class Converter {
	private static final String[] EN = {"'","\"","(",")","[","]","<", ">", ",", ";","!","?","`","$","|","^"};
	private static final String[] CN = {"‘","“","（","）","【","】","《","》","，","；","！","？","·","￥","~","……"};
	private static final DecimalFormat DecimalFmt = new DecimalFormat("###############.##");
	
	
	
	public final static String[] Trues = {"Y", "true", "Yes", "是", "OK"};
	/** 将String转换为Boolean */
	public static boolean parseBoolean(String str) {
		if( StringUtils.isBlank(str) ) return false;
		boolean b = false;
		str = StringUtils.trim(str);
		for(String s : Trues) {
			b = StringUtils.equalsIgnoreCase(s, str);
			if( b ) break;
		}
		return b;
	}
	
	/** 将String转换为Int */
	public static int parseInt(String str) {
		if( StringUtils.isBlank(str) ) return 0;
		int i = 0;
		try {
			str = StringUtils.trim(str);
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}
	
	/** 将String转换为Short */
	public static short parseShort(String str) {
		if( StringUtils.isBlank(str) ) return 0;
		short s = 0;
		try {
			str = StringUtils.trim(str);
			s = Short.parseShort(str);
		} catch (Exception e) {
			s = 0;
		}
		return s;
	}
	
	/** 将String转换为Long */
	public static Long parseLong(String str) {
		if( StringUtils.isBlank(str) ) return 0l;
		Long i = 0l;
		try {
			str = StringUtils.trim(str);
			i = Long.parseLong( str );
		} catch (Exception e) {
			i = 0l;
		}
		return i;
	}
	
	/** 将String转换为Float */
	public static Float parseFloat(String str) {
		if( StringUtils.isBlank(str) ) return 0.00f;
		Float f = 0.00f;
		try {
			str = StringUtils.trim(str);
			f = Float.parseFloat( str );
		} catch (Exception e) {
			f = 0.00f;
		}
		return f;
	}
	
	/** 将String转换为Double */
	public static Double parseDouble(String str) {
		if( StringUtils.isBlank(str) ) return 0.00d;
		Double d = 0.00d;
		try {
			str = StringUtils.trim(str);
			d = Double.parseDouble( str );
		} catch (Exception e) {
			d = 0d;
		}
		return d;
	}
	
	
	/** 获取两位小数 */
	public static Float get2Decimal(Float f) {
		if ( Float.isNaN(f) ) return 0.00f;
		return Float.parseFloat(DecimalFmt.format(f));
	}
	
	/** 获取两位小数 */
	public static Double get2Decimal(Double d) {
		if ( Double.isNaN(d) ) return 0.00d;
		//String.format("%.2f",result)
		return Double.parseDouble(DecimalFmt.format(d));
	}
	
	
	
	
	/** 将List转换为Set */
	public static Set<String> toSet(List<String> list){
		Set<String> set = new TreeSet<String>();
		if(list != null && !list.isEmpty()){
			for(String e : list){
				set.add(e);
			}
		}		
		return set;
	}
	
	/** 将String·Array转换为Set */
	public static Set<String> toSet(String[] array){
		Set<String> set = new TreeSet<String>();
		if(array != null && array.length > 0){
			for(String e : array){
				set.add(e);
			}
		}		
		return set;
	}
	
	/** 将int·Array转换为Set */
	public static Set<Integer> toSet(int[] array){
		Set<Integer> set = new TreeSet<Integer>();
		if(array != null && array.length > 0){
			for(int e : array){
				set.add(e);
			}
		}		
		return set;
	}
	
	/** 将String[]·Array转换为String，并以split将元素分隔开   */
	public static String toString(String[] row, String split){
		if(row != null && row.length > 0) {
			SmartString ss = new SmartString(split, true);
			for(String s : row){
				ss.append(s);
			}
			return ss.toString();
		}
		return "";
	}
	
	
	/** Get方式获取<中文>参数值的编码转换
	 * @param getParam: Get方式获得的参数
	 * @param srcCharset: 原来的字符骗码,可默认为: null || ""   */
	public static String toUTF8(String getParam, String srcCharset){
		try {
			if( StringUtils.isNotBlank(getParam) ){
				if( StringUtils.isNotBlank(srcCharset) )
					return new String(getParam.getBytes(srcCharset), "UTF-8");
				else
					return new String(getParam.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return getParam;
	}
	
	/** Get方式获取<中文>参数值的编码转换
	 * @param getParam: Get方式获得的参数
	 * @param srcCharset: 原来的字符骗码,可默认为: null || ""   */
	public static String toISO(String getParam, String srcCharset){
		try {
			if( StringUtils.isNotBlank(getParam) ){
				if( StringUtils.isNotBlank(srcCharset) )
					return new String(getParam.getBytes("UTF-8"), srcCharset);
				else
					return new String(getParam.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return getParam;
	}
	
	
	/** <英文>的标点及符号转换为<中文>的
	 * @param strValue: String的参数值   */
	public static String toCN(String strValue){
		try {
			if( StringUtils.isNotBlank(strValue) ){
				for (int i = 0; i < EN.length; i++) {
					strValue = StringUtils.replace(strValue, EN[i], CN[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strValue;
	}
	
	
	/** <Enter>的回车符号转换为<Html>的字符：<br>
	 * @param strValue: String的参数值   */
	public static String toHtml(String strValue){
		try {
			if( StringUtils.isNotBlank(strValue) ){
				return StringUtils.replace(strValue, "\r\n", "<br>");//转义回车
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strValue;
	}
	
	/** 源字符与转义符 */
	private static final String[] src = {"&", "<", ">", "\"", " ", "'"};
	private static final String[] trn = {"&amp;", "&lt;", "&gt;", "&quot;", "&nbsp;", "&apos;"};
	
	/** HTML标签转义 */
	public static String htmlSpecialChars(String str) {
		if( StringUtils.isBlank(str) ) return str;
		for (int i = 0; i < src.length; i++) {
			//str = str.replaceAll(src[i], trn[i]);
			str = StringUtils.replace(str, src[i], trn[i]);
		}
		return str;
	}
	
	
	
	
	
	/**Set<String>转String[] */
	public static String[] toArray(Set<String> set){
		if(set != null && set.size() > 0){
			String[] a = new String[set.size()];
			return set.toArray(a);
		}
		return null;
	}
	
	/**Set<String>转List<String> */
	public static List<String> toList(Set<String> set){
		List<String> list = new ArrayList<String>();
		if(set != null && set.size() > 0){
			Iterator<String> itr = set.iterator();
			while(itr.hasNext()){
				list.add(itr.next());
			}
		}
		return list;
	}
	
	/**将Set<String>转为String  */
	public static String toString(Set<String> set, String split) {
		if(set != null && set.size() > 0) {
			String s = StringTools.clear(set.toString(), "[", "]");
			s = StringUtils.replace(s, ", ", split);
			return s;
		}
		return null;
	}
	
	
	public static List<Long> toLongList(String src, String split){
		List<Long> list = new ArrayList<Long>();
		if( !StringTools.isTrimNull(src, split) ){
			String[] ss = StringUtils.split(src, split);
			for (int i = 0; i < ss.length; i++) {
				String temp = ss[i];
				if( StringTools.isTrimNull(temp) ) list.add(0l);
				list.add( parseLong(temp) );
			}
		}
		return list;
	}
	
	public static List<Short> toShortList(String src, String split){
		List<Short> list = new ArrayList<Short>();
		if( !StringTools.isTrimNull(src, split) ){
			String[] ss = StringUtils.split(src, split);
			for (int i = 0; i < ss.length; i++) {
				String temp = ss[i];
				if( StringTools.isTrimNull(temp) ) list.add((short)0);
				list.add( parseShort(temp) );
			}
		}
		return list;
	}
	
	
	
	/**List<String>转String[] */
	public static String[] toArray(List<String> list){
		if(list != null && list.size() > 0){
			String[] a = new String[list.size()];
			return list.toArray(a);
		}
		return null;
	}	
}
