package com.yan.springboot.lucene.ik.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseDaoImpl {

	@Autowired JdbcTemplate jdbc; 
	
	//查询数据库总行数 
	public int foodcount() { 
		String sql="select count(*) as foodCount from food"; 
		return Integer.parseInt(jdbc.queryForObject(sql, java.lang.String.class)); 
	} 
	
	//每页显示行数 
	public List<Map<String, Object>> queryFood(int start,int rws) { 
		String sql="select * from food limit " + start +"," + rws ; 
		return jdbc.queryForList(sql); 
	}
}
