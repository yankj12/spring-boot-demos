package com.yan.springboot.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootStrapController {

	@RequestMapping(value = {"/", "/index"})
	public String indexPage(){
		
		return "index";
	}
}
