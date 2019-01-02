package com.yan.springboot.lucene.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {

	@RequestMapping(value = {"/", "/index"})
	public String indexPage(){
		
		return "search";
	}
}
