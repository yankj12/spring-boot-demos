package com.yan.springboot.async.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yan.springboot.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { DemoApplication.class })
public class SendMailServiceTest {

	@Autowired
	private SendMailService sendMailService;

	@Test
	public void testSendMail() {
		sendMailService.sendMail("hello");
		System.out.println("finish");
		
		try {
			// 模拟阻塞10s，等待模拟发送邮件完成
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
