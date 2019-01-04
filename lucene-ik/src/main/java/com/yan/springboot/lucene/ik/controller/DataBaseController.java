package com.yan.springboot.lucene.ik.controller;

import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yan.springboot.lucene.ik.dao.facade.DataBaseDao;
import com.yan.springboot.lucene.ik.dao.facade.FoodRepository;
import com.yan.springboot.lucene.ik.schema.Food;
import com.yan.springboot.lucene.ik.util.IndexDemo;

@RestController
public class DataBaseController {
	 
	@Autowired DataBaseDao dataBaseDao; 
	@Autowired IndexDemo indexDemo; 
	
	@Autowired
	FoodRepository foodRepository;
	
	@GetMapping("/createIndex") 
	public String createIndex(){ 
		//查询数据库，必须要批量查询 
		int foodTotalCount = dataBaseDao.foodcount();
		//查询总行数    8 
		int skip = 0;  
		//开始位置 
		int rows = 5;   
		//每页行数 
		 /*
	     * 0---4  5
	     * 5---9  5
	     * 10---14  5
	     */ 
		while(skip <= foodTotalCount){ 
			//每拉取一次数据 
			List<Map<String, Object>> queryFood=dataBaseDao.queryFood(skip, rows); 
			//获取字段 
			for(int i=0;i<queryFood.size();i++){ 
				//获取每行数据 
				Map<String, Object> lineData = queryFood.get(i); 
				//创建Document对象 
				Document doc = new Document(); 
				//获取每列数据 
				Field foodid=new Field("foodid",lineData.get("foodid").toString(),TextField.TYPE_STORED); 
				Field foodname=new Field("foodname",lineData.get("foodname").toString(),TextField.TYPE_STORED); 
				Field price=new Field("price",lineData.get("price").toString(),TextField.TYPE_STORED); 
				Field imagepath=new Field("imagepath",lineData.get("imagepath").toString(),TextField.TYPE_STORED); 
				//添加到Document中 
				doc.add(foodid); 
				doc.add(foodname); 
				doc.add(price); 
				doc.add(imagepath); 
				//调用，创建索引库 
				indexDemo.write(doc); 
			}
			skip += rows; 
		} 
		return "成功"; 
	} 
	
	//搜索，实现高亮 
	@GetMapping("/searchFood") 
	public List<Map> getFood(String keyWord) throws Exception{ 
		return indexDemo.search("foodname", keyWord); 
	} 
	
	@PostMapping("/food")
	public String addFood(String foodName, Double price, String imagePath) throws Exception{
		Food food = new Food();
		food.setFoodName(foodName);
		food.setPrice(price);
		food.setImagePath(imagePath);
		
		food = foodRepository.save(food);
		//System.out.println(food.getFoodId());

		// 增量增加索引
		//创建Document对象 
		Document doc = new Document(); 
		//获取每列数据 
		Field foodid=new Field("foodid", food.getFoodId() != null?Integer.toString(food.getFoodId()):"", TextField.TYPE_STORED); 
		Field foodname=new Field("foodname",foodName, TextField.TYPE_STORED); 
		Field priceField=new Field("price", Double.toString(price), TextField.TYPE_STORED); 
		Field imagepath=new Field("imagepath",imagePath, TextField.TYPE_STORED); 
		//添加到Document中 
		doc.add(foodid); 
		doc.add(foodname); 
		doc.add(priceField); 
		doc.add(imagepath); 
		
		indexDemo.appendIndex(doc);
		
		return "success";
	}
}