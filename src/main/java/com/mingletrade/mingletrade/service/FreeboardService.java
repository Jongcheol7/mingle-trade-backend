package com.mingletrade.mingletrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Transactional
	public Map<String, Object> selectAllFreeboard(int start, int limit ) {
		System.out.println("service start : " + start);
		int total = mapper.selectTotalCount();
		List<Freeboard> lists = mapper.selectAllFreeboard(start, limit); 
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("lists", lists);
		result.put("total", total);
		return result;
	}
	
	public Freeboard selectOneById(int id) {
		return mapper.selectOneById(id);
	}
}
