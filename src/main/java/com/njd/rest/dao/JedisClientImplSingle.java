package com.njd.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientImplSingle implements JedisClient {
	//单机版
	//@Autowired
	private JedisPool redisClient; 
	@Override
	public String get(String key) {
		Jedis jedis = redisClient.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = redisClient.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
		
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = redisClient.getResource();
		String value = jedis.hget(hkey, key);
		jedis.close();
		return value;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis  = redisClient.getResource();
		long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String hkey) {
		Jedis jedis  = redisClient.getResource();
		long result = jedis.incr(hkey);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = redisClient.getResource();
		long result = jedis.expire(key,  second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = redisClient.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

}
