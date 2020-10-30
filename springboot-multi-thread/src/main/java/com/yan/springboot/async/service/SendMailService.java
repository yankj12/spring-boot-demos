package com.yan.springboot.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendMailService {

	@Async
	public void sendMail(String s) {
		log.info("准备模拟发送邮件, 邮件内容：{}", s);
		try {
			Thread.sleep(5*1000);	//	使当前线阻塞 5s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("模拟发送邮件完成, 邮件内容：{}", s);
	}
}
