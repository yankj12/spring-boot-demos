package com.yan.student.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableRedisLock {

	String lockKey();
	long expireTime() default 5;
	TimeUnit timeUnit() default TimeUnit.SECONDS;
	int retryTimes() default 10;
	
}
