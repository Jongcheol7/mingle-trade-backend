package com.mingletrade.mingletrade.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	Map<String, Object> findByEmail(String email);
	void insertUser(Map<String, Object> user);
	
}
