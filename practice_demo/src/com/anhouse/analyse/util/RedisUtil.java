package com.anhouse.analyse.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.pingan.haofang.util.StringTools;

/**   
 * @Title: Redis工具类
 * @Description: 存取Redis的操作
 * @Team: 技术1部Java开发小组
 * @Author Andy-ZhichengYuan   
 * @Date 2015年3月10日
 * @Version V1.0
 * @update at 2016-01-26   */
public class RedisUtil {
	private static Logger log = LoggerFactory.getLogger( RedisUtil.class );
	private static final String ProCfgName = "redis.properties";
	
	//动态读取配置文件
	private static PropertiesCfg ProCfg;
	private static String host;
	private static int port;
	private static int timeout;
	private static String password;
	private static int db;
	private static JedisPool pool;
	static {
		ProCfg = new PropertiesCfg();
		ProCfg.setProCfg( ProCfgName );
		
		host = ProCfg.getValue( "redis.host" );
		port = ProCfg.getIntValue( "redis.port" );
		password = ProCfg.getValue( "redis.pass" );
		timeout = ProCfg.getIntValue( "redis.timeout" );
		db = ProCfg.getIntValue( "redis.default.db" );
		if( StringUtils.isBlank(password) ) password = null;
		if(timeout <= 0) timeout = 2000;
		if(port <= 0) port = 6379;
		if(db < 0 || db > 15) {
			db = 0;
			log.warn("Unknown jedis.db ["+ ProCfg.getValue( "redis.default.db" ) +"], default use "+ db);
		}
		
		pool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, db);
	}
	
	/** 持久化参数，expire设置这个值为持久化存储   */
	public static final int PERSIST = 0;
	public static final int SECOND = 1;
	public static final int MINUTE = 60*SECOND;
	public static final int HOUR = 60*MINUTE;
	public static final int DAY = 24*HOUR;
	//static Jedis jedis = pool.getResource();
	
	/**
	 * 非法的超时时间值异常
	 * @author ZHAOLIN387  */
	public static class InvalidExpireException extends RuntimeException{
		private static final long serialVersionUID = -8920432674676474073L;
		public InvalidExpireException(String msg){
			super(" Invalid expire value "+msg);
		}
	}
	
	/**
	 *  检查Redis缓存中是否已包含此Key
	 * @param key  */
	public static boolean exists(String key) {
		boolean flag = false;
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			flag = jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return flag;
	}
	
	
	
	
	/**
	 * 将字符串保存到redisCache中
	 * @param key
	 * @param expire expire seconds
	 * @param value
	 * @return  */
	public static boolean setStringRedisCacheInfo(String key, String value, int expire) {
		if(expire < PERSIST){
			throw  new InvalidExpireException(""+expire);
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			if(expire > PERSIST ){
				jedis.setex(key,expire,value);
			}else if(expire == PERSIST ){
				jedis.set(key,value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return false;
	}

	/**
	 * 从redisCache中获取key对应的String值
	 * @param key
	 * @return  */
	public static String getStringRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 将List保存到redisCache中
	 * @param key
	 * @param expire 
	 * @param list
	 * @return  */
	public static boolean setListRedisCacheInfo(String key, List<String> list, int expire) {
		if(expire < PERSIST){
			throw  new InvalidExpireException(""+expire);
		}
		
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			for (String str : list) {
				jedis.rpush(key, str);
			}
			if(expire > PERSIST){
				jedis.expire(key, expire);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return false;
	}

	/**
	 * 从redisCache中获取key对应的List值
	 * @param key
	 * @return  */
	public static List<String> getListRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.lrange(key, 0, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return null;
	}

	
	/**
	 * 将Set保存到redisCache中
	 * @param key
	 * @param expire expire seconds
	 * @param set
	 * @return   */
	public static boolean setSetRedisCacheInfo(String key, Set<String> set, int expire) {
		if(expire < PERSIST){
			throw  new InvalidExpireException(""+expire);
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			for (String str : set) {
				jedis.sadd(key, str);
			}
			if(expire > PERSIST){
				jedis.expire(key, expire);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return false;
	}

	/**
	 * 从redisCache中获取key对应的Set值
	 * @param key
	 * @return  */
	public static Set<String> getSetRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return null;
	}
	
	
	/**
	 * 从redisCache中获取key对应的Set值
	 * @param key
	 * @return  */
	public static Set<byte[]> getobjectRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.smembers(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return null;
	}

	
	/**
	 * 将Map保存到redisCache中
	 * @param key
	 * @param map
	 * @return   */
	public static boolean setMapRedisCacheInfo(String key,  Map<String, String> map, int expire) {
		if(expire < PERSIST){
			throw  new InvalidExpireException(""+expire);
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
//			Set<String> keys1 = map.keySet();
//			for (String str : keys1) {
//				jedis.hset(key, str, map.get(str));
//			}
			//TODO update by YZC
			jedis.hmset(key, map);
			if(expire > PERSIST){
				jedis.expire(key, expire);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return false;
	}

	
	/**
	 * 将单个Key-Value保存到RedisCache中
	 * @param redisKey
	 * @param mapKey
	 * @param mapValue
	 * @param expire
	 * @return   */
	public static boolean setMapRedisCache(String redisKey, String mapKey, String mapValue, int expire) {
		if(expire < PERSIST){
			throw  new InvalidExpireException(""+expire);
		}
		if( !StringTools.isTrimNull(redisKey, mapKey, mapValue) ) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				jedis.hset(redisKey, mapKey, mapValue);
				if(expire > PERSIST){
					jedis.expire(redisKey, expire);
				}
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(jedis != null){
					pool.returnResource(jedis);
				}
			}
		}
		return false;
	}
	
	/**
	 * 从redisCache中获取key对应的Map值
	 * @param key
	 * @return   */
	public static Map<String, String> getMapRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 将redisCache中key对应的值删除
	 * @param key  */
	public static void delRedisCacheInfo(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	
	/**
	 * 将对象保存到redisCache中
	 * @param key
	 * @param expire expire second
	 * @param object
	 * @return  */
	public static boolean setObject2JsonCacheInfo(String key, Object object, int expire) {
		return setStringRedisCacheInfo(key, FastJsonUtil.toJSONString(object), expire);
		//update by YZC at 2015-11-27
//		return setStringRedisCacheInfo(key, JSONHelper.obj2JSONString(object), expire); 
	}
	
	/**
	 *  从redisCache中获取key对应的对象
	 * @param key 
	 * @param clazz 对象类型
	 * @return  */
	public static <T> T getJson2ObjectCacheInfo(String key, Class<T> clazz) {
		return FastJsonUtil.toBean(getStringRedisCacheInfo(key), clazz);
		//update by YZC at 2015-11-27
//		return JSONHelper.jsonToObject(getStringRedisCacheInfo(key), clazz);
	}
	
	
	//使用通配符获取一批Key，此方法危险，请慎用
//	public static Set<String> getkeys(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			Set<String> set = jedis.keys(key);
//			return set;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			if(jedis != null){
//				pool.returnResource(jedis);
//			}
//		}
//		return null;
//	}
	
}

