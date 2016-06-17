package com.anhouse.analyse.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 基于fastjson封装的工具包
 * 
 * @author 刘鹏
 *
 */
public class FastJsonUtil {
	
	private static Logger logger = Logger.getLogger(FastJsonUtil.class);
	
	/**
	 * 将JSON格式的字符串转换成任意Java类型的对象
	 * 
	 * @param json
	 *            JSON格式的字符串
	 * @param type
	 *            任意Java类型
	 * @return 任意Java类型的对象
	 */
	public static <T> T toAny(String json, TypeReference<T> type) {
		return JSON.parseObject(json, type);
	}

	

	/**
	 * 把JSON文本parse为JSONObject或者JSONArray
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static Object toObject(String text) {

		Object object = null;

		if (!StringUtils.isEmpty(text)) {
			object = JSON.parse(text);
		}
		return object;
	}

	
	/**
	 * 把JSON文本parse成JSONObject
	 * 
	 * @param str
	 * 
	 * @return
	 */
	public static JSONObject toJSONObject(String text) {

		JSONObject jsonObject = null;

		if (!StringUtils.isEmpty(text)) {
			try {
				jsonObject = JSON.parseObject(text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.info("字符串转换JSON失败："+e.getMessage()); 
				logger.info("转换失败的字符串："+text);
			}
		}

		return jsonObject;
	}

	/**
	 * 将JSON格式的字符串转换为java类型的对象或者java数组类型的对象，不包括java集合类型
	 * 
	 * @param json
	 *            JSON格式的字符串
	 * @param clz
	 *            java类型或者java数组类型，不包括java集合类型
	 * @return java类型的对象或者java数组类型的对象，不包括java集合类型的对象
	 */
	public static <T> T toBean(String text, Class<T> clazz) {

		T t = null;

		if (!StringUtils.isEmpty(text)) {
			t = JSON.parseObject(text, clazz);
		}

		return t;
	}

	/**
	 * 把JSON文本parse成JSONArray
	 * 
	 * @param text
	 * 
	 * @return
	 */
	public static JSONArray toJSONArray(String text) {

		JSONArray jsonArray = null;

		if (!StringUtils.isEmpty(text)) {
			jsonArray = JSON.parseArray(text);
		}
		return jsonArray;
	}

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 * @param object
	 * 
	 * @return
	 */
	public static String toJSONString(Object object) {

		String jsonString = "";

		if (object != null) {
			jsonString = JSON.toJSONString(object);
		}

		return jsonString;
	}

	/**
	 * 将JavaBean序列化为带格式的JSON文本
	 * 
	 * @param object
	 * 
	 * @param prettyFormat
	 * 
	 * @return
	 */
	public static String toJSONString(Object object, boolean prettyFormat) {

		String jsonString = "";

		if (object != null) {
			jsonString = JSON.toJSONString(object, prettyFormat);
		}

		return jsonString;
	}

	/**
	 * 将JavaBean转换为JSONObject或者JSONArray
	 * 
	 * @param javaObject
	 * 
	 * @return
	 */
	public static Object toJSON(Object javaObject) {

		Object json = null;

		if (javaObject != null) {
			json = JSON.toJSON(javaObject);
		}

		return json;
	}

	/**
	 * 将Json文本转成HashMap
	 * 
	 * @param text
	 * 
	 * @return
	 */
	public static Map<String, Object> toHashMap(String text) {

		HashMap<String, Object> map = new HashMap<>();

		JSONObject jsonObject = toJSONObject(text);
		Set<String> set = jsonObject.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 将JSON格式的字符串转换为List<T>类型的对象
	 * 
	 * @param text
	 *            JSON格式的字符串
	 * @param clz
	 *            指定泛型集合里面的T类型
	 * @return List<T>类型的对象
	 */
	public static <T> List<T> toList(String text, Class<T> clazz) {

		List<T> list = null;

		if (!StringUtils.isEmpty(text)) {
			list = JSON.parseArray(text, clazz);
		}
		return list;
	}

	
	public static void main(String[] args) {
		
//		String line = "{'_id':{'$oid':'564bd47ad031dcc3438b4595'},'aContext':[{'appdeviceid':'3b43hgyutf7ur3hbg3','appsource':'hfb','guid':'0a10f0f2b3b59458ced41fa82748fd66','mobile':'13916830013','position':'Iapp','time':{'$numberLong':'1447328222'},'userid':'19992618'}],'aExtra':[],'iCreateTime':{'$date':'2015-11-18T01:29:30.777Z'},'iTime':{'$date':'2015-11-12T11:37:02.910Z'},'iUpdateTime':{'$date':'2015-11-18T01:29:30.777Z'},'sCustomerip':'10.28.80.42','sEnv':'paf','sGuid':'0a10f0f2b3b59458ced41fa82748fd66','sLevel':'INFO','sMessage':'user#account#register#success','sMethod':'POST','sRecorder':'app-hf-api','sRequestip':'10.59.72.23','sTrackID':'366F7364-65FB-5C61-E658-BEE2D894706E','sUrl':'http://api.yuyouwei.dev.anhouse.com.cn/user/user/qlogin.html'}";
//	   
//		JSONObject jsonObject = toJSONObject(line);
//	    //Object object = jsonObject.get("sLevel");
//	    Object object = jsonObject.get("aContext");
//	    //JSONObject jsonObject2 = toJSONObject(object.toString());
//	    String str = StringTools.clear(object.toString(), "[","]");
//	    String str2 = StringTools.clear(str.toString(), "[","]");
//	    //Object object2 = jsonObject2.get("appdeviceid");
//	    System.err.println(object.toString());
//	    System.err.println(str2);
		System.err.println(StringUtils.substring("abcdefghijklmn", 0, 10));
	}
}
