package com.mingletrade.mingletrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mingletrade.mingletrade.domain.Freeboard;

@Mapper
public interface FreeboardMapper {
	
	int insertFreeboard(Freeboard fb);
	
	List<Freeboard> selectAllFreeboard(@Param("start") int start, 
							           @Param("limit") int limit);
	
	int selectTotalCount();
	
	Freeboard selectOneById(@Param("id") int id);
	
	void updateFreeboard(Freeboard fb);
	
	int tempViewsUp(@Param("boardId") int boardId);
} 
