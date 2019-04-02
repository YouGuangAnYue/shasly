package com.shasly.common.jedis;

import java.util.List;

/**
 * @author Exrickx
 */
public interface JedisClient {

	String set(String key, String value);

	String set(String key, String value,String nxxx,String expx,Long time);

	String get(String key);

	Boolean exists(String key);

	Long expire(String key, int seconds);

	Long ttl(String key);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Long hdel(String key, String... field);

	Boolean hexists(String key, String field);

	List<String> hvals(String key);

	Long del(String key);
}
