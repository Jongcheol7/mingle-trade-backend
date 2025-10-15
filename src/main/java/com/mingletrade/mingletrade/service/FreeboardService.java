package com.mingletrade.mingletrade.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingletrade.mingletrade.domain.Freeboard;
import com.mingletrade.mingletrade.domain.PrevClosingPrice;
import com.mingletrade.mingletrade.mapper.FreeboardMapper;

@Service
public class FreeboardService {

	private final FreeboardMapper mapper;
	
	
	public FreeboardService(FreeboardMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Transactional
	public void insert(Freeboard fb) {
		mapper.insertFreeboard(fb);
	}
}
