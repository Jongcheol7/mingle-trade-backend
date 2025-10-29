package com.mingletrade.mingletrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mingletrade.mingletrade.domain.Portfolio;

@Mapper
public interface PortfolioMapper {

	List<Portfolio> selectByEmail(String email);
}
