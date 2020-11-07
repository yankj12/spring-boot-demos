package com.yan.springboot.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yan.springboot.security.schema.SysUser;

@Mapper
public interface UserMapper {
    SysUser findByUserName(@Param("username") String username);
}