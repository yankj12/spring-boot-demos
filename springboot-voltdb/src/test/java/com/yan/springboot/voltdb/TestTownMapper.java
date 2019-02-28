package com.yan.springboot.voltdb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yan.springboot.voltdb.mapper.TownMapper;
import com.yan.springboot.voltdb.schema.Town;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = { SpringBootVoltDBApplication.class })
public class TestTownMapper {
	
	@Autowired
	TownMapper townMapper;
	
	@Test
	public void test(){
		List<Town> towns = townMapper.findFirstX(10);
		
		System.out.println(towns.size());
		for(Town town:towns) {
			String jsonString = JSON.toJSONString(town);
			System.out.println(jsonString);	
		}
	}
}
