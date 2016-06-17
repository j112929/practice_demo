package com.anhouse.analyse.util;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.anhouse.analyse.util.RedisUtil;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtilTest{
	Logger log = org.apache.log4j.Logger.getLogger(RedisUtilTest.class);
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void testStringPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		boolean result = RedisUtil.setStringRedisCacheInfo(key, "hello", RedisUtil.PERSIST);
		assertTrue(result);
		String value = RedisUtil.getStringRedisCacheInfo(key);
		assertEquals("hello", value);
	}
	
	@Test
	public void testStringExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		boolean result = RedisUtil.setStringRedisCacheInfo(key, "hello",1);
		String  value = RedisUtil.getStringRedisCacheInfo(key);
		assertEquals("hello", value);
		assertTrue(result);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getStringRedisCacheInfo(key);
		assertNull(value);
	}
	
	@Test
	public void testListPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		List<String> list = new ArrayList<String>();
		list.add("foo");
		list.add("bar");
		boolean result = RedisUtil.setListRedisCacheInfo(key, list, RedisUtil.PERSIST);
		assertTrue(result);
		List<String>  value = RedisUtil.getListRedisCacheInfo(key);
		assertEquals(list, value);
	}
	
	@Test
	public void testListExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		List<String> list = new ArrayList<String>();
		list.add("foo");
		list.add("bar");
		boolean result = RedisUtil.setListRedisCacheInfo(key, list,1);
		List<String>  value = RedisUtil.getListRedisCacheInfo(key);
		assertEquals(list, value);
		assertTrue(result);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getListRedisCacheInfo(key);
		assertTrue(value.isEmpty());
	}
	
	@Test
	public void testSetPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Set<String> set = new HashSet<String>();
		set.add("foo");
		set.add("bar");
		boolean result = RedisUtil.setSetRedisCacheInfo(key, set, RedisUtil.PERSIST);
		assertTrue(result);
		Set<String>  value = RedisUtil.getSetRedisCacheInfo(key);
		assertEquals(set, value);
	}
	
	@Test
	public void testSetExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Set<String> set = new HashSet<String>();
		set.add("foo");
		set.add("bar");
		boolean result = RedisUtil.setSetRedisCacheInfo(key, set,1);
		assertTrue(result);
		Set<String>  value = RedisUtil.getSetRedisCacheInfo(key);
		assertEquals(set, value);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getSetRedisCacheInfo(key);
		assertTrue(value.isEmpty());
	}
	
	@Test
	public void testMapEntryPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Map<String,String> map = new HashMap<String,String>();
		boolean result = RedisUtil.setMapRedisCache(key, "zoo","zoo", RedisUtil.PERSIST);
		assertTrue(result);
		Map<String,String>  value = RedisUtil.getMapRedisCacheInfo(key);
		map.put("zoo","zoo");
		assertEquals(map, value);
	}
	
	@Test
	public void testMapEntryExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Map<String,String> map = new HashMap<String,String>();
		boolean result = RedisUtil.setMapRedisCache(key, "zoo","zoo",1);
		Map<String,String>  value = RedisUtil.getMapRedisCacheInfo(key);
		map.put("zoo","zoo");
		assertEquals(map,value);
		assertTrue(result);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getMapRedisCacheInfo(key);
		assertTrue(value.isEmpty());
	}
	
	@Test
	public void testMapPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Map<String,String> map = new HashMap<String,String>();
		map.put("foo","foo");
		map.put("bar","bar");
		boolean result = RedisUtil.setMapRedisCacheInfo(key, map, RedisUtil.PERSIST);
		assertTrue(result);
		Map<String,String>  value = RedisUtil.getMapRedisCacheInfo(key);
		assertEquals(map, value);
	}
	
	@Test
	public void testMapExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		Map<String,String> map = new HashMap<String,String>();
		map.put("foo","foo");
		map.put("bar","bar");
		boolean result = RedisUtil.setMapRedisCacheInfo(key, map,1);
		Map<String,String>  value = RedisUtil.getMapRedisCacheInfo(key);
		assertEquals(map,value);
		assertTrue(result);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getMapRedisCacheInfo(key);
		assertTrue(value.isEmpty());
	}
	
	@Test
	public void testObjectPersist(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		String obj = new String();
		boolean result = RedisUtil.setObject2JsonCacheInfo(key, obj, RedisUtil.PERSIST);
		assertTrue(result);
		String value = RedisUtil.getJson2ObjectCacheInfo(key,String.class);
		assertEquals(obj, value);
	}
	
	@Test
	public void testObjectExpire(){
		String key = "test.redis.util.key";
		RedisUtil.delRedisCacheInfo(key);
		String obj = new String();
		boolean result = RedisUtil.setObject2JsonCacheInfo(key, obj,1);
		String  value = RedisUtil.getJson2ObjectCacheInfo(key,String.class);
		assertEquals(obj,value);
		assertTrue(result);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = RedisUtil.getJson2ObjectCacheInfo(key,String.class);
		assertNull(value);
	}
	
	
}
