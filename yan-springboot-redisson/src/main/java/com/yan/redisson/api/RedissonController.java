package com.yan.redisson.api;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.redisson.schema.User;
import com.yan.redisson.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private UserService userService;
    
    @GetMapping("/getFlag/{code}")
    public String getFlag(@PathVariable("code") String code){
    	String flag = userService.findFlag(code);
    	return flag;
    }
    
    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") String id){
    	User user = userService.findUser(id);
    	return user;
    }
    
    @GetMapping(value = "/save/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
        	// 尝试加锁，最多等待100秒，上锁以后60秒自动解锁
        	boolean res = lock.tryLock(100, 60, TimeUnit.SECONDS);
        	// true if lock is successfully acquired, otherwise false if lock is already set.
        	if(res) {
        		// 锁60S
        		lock.lock(60, TimeUnit.SECONDS);
        		
        		// 模拟业务，业务时间30S
        		Thread.sleep(30 * 1000);
        	}else {
        		log.error("获取锁失败");
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	log.error("未知异常");
        } finally {
            lock.unlock();
            log.info("已解锁");
        }
        return "success";
    }

}
