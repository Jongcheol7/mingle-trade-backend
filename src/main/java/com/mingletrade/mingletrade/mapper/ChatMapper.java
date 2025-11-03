package com.mingletrade.mingletrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mingletrade.mingletrade.domain.User;

@Mapper
public interface ChatMapper {

	List<Map<String, Object>> selectChatList(String email);
	
}
