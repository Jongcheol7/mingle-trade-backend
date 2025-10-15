package com.mingletrade.mingletrade.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mingletrade.mingletrade.domain.Freeboard;

@Mapper
public interface FreeboardMapper {
	
	int insertFreeboard(Freeboard fb);
}
