package com.yan.springboot.tx.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yan.springboot.tx.schema.User;

@Mapper
public interface UserMapper {

	void insertUser(User user);
	
}
