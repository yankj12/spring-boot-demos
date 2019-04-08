package com.yan.springboot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yan.springboot.security.mapper.UserMapper;
import com.yan.springboot.security.schema.SysUser;

@Service
public class CustomUserService implements UserDetailsService { 
    @Autowired
    UserMapper userMapper;
    // 重写loadUserByUsername 方法获得 userdetails  类型用户
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userMapper.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}