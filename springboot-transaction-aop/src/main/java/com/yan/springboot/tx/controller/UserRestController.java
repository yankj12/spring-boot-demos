package com.yan.springboot.tx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String imagesDataGrid(Integer page, Integer rows, String validStatus) {
		
		return "hello";
	}
}
