package com.yan.springboot.lucene.ik.dao.facade;

import java.util.List;
import java.util.Map;

public interface DataBaseDao {
	// 查询数据库总行数
	public int foodcount();

	// 每页显示行数
	public List<Map<String, Object>> queryFood(int start, int rws);
}
