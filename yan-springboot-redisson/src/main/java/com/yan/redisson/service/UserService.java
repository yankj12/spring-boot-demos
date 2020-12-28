package com.yan.redisson.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.redisson.schema.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
    private RedissonClient redissonClient;
	
	public User findUser(String id) {
		String key = String.format("User#%s", id);
		RBucket<User> bucket = redissonClient.getBucket(key);
		//bucket.isExists()
		User user = bucket.get();
		if(user == null){
			log.info(String.format("查询数据库，User#%s", id));
			
			user = new User();
			user.setId(id);
			user.setEmail("test@test.com");
			user.setName("张三");
			
			// 设置缓存和失效时间
			// trySet  Tries to set element atomically into empty holder.
			bucket.trySet(user, 120, TimeUnit.SECONDS);
			// set  Stores element into the holder.
			//bucket.set(user, 120, TimeUnit.SECONDS);
		}else {
			log.info(String.format("使用缓存，User#%s", id));
		}
		return user;
	}
	
	public String findFlag(String code) {
		String key = String.format("Flag#%s", code);
		RBucket<String> bucket = redissonClient.getBucket(key);
		//bucket.isExists()
		String flag = bucket.get();
		if(flag == null){
			log.info(String.format("查询数据库，Flag#%s", code));
			
			flag = "1";
			
			// 设置缓存和失效时间
			// trySet  Tries to set element atomically into empty holder.
			bucket.trySet(flag, 120, TimeUnit.SECONDS);
		}else {
			log.info(String.format("使用缓存，Flag#%s", code));
		}
		return flag;
	}
}
