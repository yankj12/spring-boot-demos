package com.yan.springboot.threadpool.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PoolMailService {

	@Resource(name = "defaultThreadPool")
	private ThreadPoolTaskExecutor executor;
	
	public void sendMail(String[] mails) throws Exception {
		//使用Future方式执行多任务
        //生成一个集合
        List<Future> futures = new ArrayList<>();
        
        for(String mail:mails) {
        	Future<?> future = executor.submit(() -> {
                //发送短信
        		log.info("模拟发送邮件完成, 邮件内容：{}", mail);
            });
            futures.add(future);
        }

        // 查看执行结果
        for (Future<?> future : futures) {
        	while (true) {//CPU高速轮询：每个future都并发轮循，判断完成状态然后获取结果，这一行，是本实现方案的精髓所在。即有10个future在高速轮询，完成一个future的获取结果，就关闭一个轮询
        		if (future.isDone()&& !future.isCancelled()) {//获取future成功完成状态，如果想要限制每个任务的超时时间，取消本行的状态判断+future.get(1000*1, TimeUnit.MILLISECONDS)+catch超时异常使用即可。
        			//get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
        			future.get();//获取结果
        			//System.out.println("任务i="+i+"获取完成!"+new Date());
        			//list.add(i);
        			break;//当前future获取结果完毕，跳出while
        		}else {
        			Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU---》新手别忘记这个
        		}
        	}
        }
	}
}
