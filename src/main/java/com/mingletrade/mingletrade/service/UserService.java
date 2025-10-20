package com.mingletrade.mingletrade.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mingletrade.mingletrade.mapper.UserMapper;

@Service
public class UserService {

	private final UserMapper userMapper;

	public UserService(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}
	
	public Map<String, Object> selectUser(String email){
		return userMapper.findByEmail(email);
	}
}
