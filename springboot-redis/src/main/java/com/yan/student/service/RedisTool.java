package com.yan.student.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTool {

	@Resource
	public RedisTemplate redisTemplate;
	
    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {

        boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, 60, TimeUnit.SECONDS);

        return !result;

    }

    /**
     * 释放锁
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean tryReleaseDistributedLock(String lockKey, String requestId) {
    	boolean result = false;
    	if (redisTemplate.opsForValue().get(lockKey).equals(requestId)) { 
            //保证当前线程释放
            result = redisTemplate.delete(lockKey);
        }
    	return result;
    }
    
}