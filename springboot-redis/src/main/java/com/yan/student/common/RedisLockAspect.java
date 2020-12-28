package com.yan.student.common;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Aspect
public class RedisLockAspect {

	@Autowired
	public RedisTemplate redisTemplate;
	
	@Around(value="@annotation(com.yan.student.common.EnableRedisLock)")
	public void handleRedisLoak(ProceedingJoinPoint joinPoint) throws Throwable{
		// 通过反射获取到注解对象，课件反射非常重要
		EnableRedisLock redisLock = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(EnableRedisLock.class);
		// 获取注解对象的变量值
		String lockKey = redisLock.lockKey();
		long expireTimes = redisLock.expireTime();
		TimeUnit timeUnit = redisLock.timeUnit();
		int retryTimes = redisLock.retryTimes();
		
		// 获取锁
		if(tryGetDistributedLock(lockKey, "requestId", expireTimes)) {
			
			try {
				// 获取锁成功的继续执行业务逻辑
				joinPoint.proceed();
			} finally {
				tryReleaseDistributedLock(lockKey, "requestId");
			}
		}
	}
	
	
    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, long expireTime) {

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
