package com.mingletrade.mingletrade.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mingletrade.mingletrade.domain.PrevClosingPrice;


@Mapper
public interface PrevClosingPriceMapper {

	void insertPrevClosingPrice(PrevClosingPrice price);
	
	List<PrevClosingPrice> selectPrevClosingPriceAll(String closeDate);
	
	void deletePrevClosingPrice(LocalDate closeDate);
}
