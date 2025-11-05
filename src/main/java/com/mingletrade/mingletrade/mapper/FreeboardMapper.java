package com.mingletrade.mingletrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mingletrade.mingletrade.domain.Freeboard;

@Mapper
public interface FreeboardMapper {
	
	int insertFreeboard(Freeboard fb);
	
	List<Map<String, Object>> selectAllFreeboard(@Param("start") int start, 
							           @Param("limit") int limit);
	
	int selectTotalCount();
	
	Freeboard selectOneById(@Param("id") int id);
	
	void updateFreeboard(Freeboard fb);
	
	int findViewInFiveMins(@Param("id") int boardId, @Param("email") String email);
	
	void viewTableUp(@Param("id") int boardId, @Param("email") String email);
	
	void boardViewUpdate(@Param("id") int boardId);
} 
