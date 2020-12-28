package com.yan.student.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.yan.student.common.EnableRedisLock;
import com.yan.student.schema.Student;

public class StudentInfoService {

	@Resource
	public RedisTemplate redisTemplate;
	
	@EnableRedisLock(lockKey="redis_lock_student")
	public boolean updateStudentInfo(Student student) {
		System.out.println("updateStudentInfo");
		return false;
	}
	
	@EnableRedisLock(lockKey="redis_lock_student")
	public boolean createStudentInfo(Student student) {
		System.out.println("createStudentInfo");
		return false;
	}
}
