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
		List<Map<String, Object>> lists = mapper.selectAllFreeboard(start, limit); 
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("lists", lists);
		result.put("total", total);
		return result;
	}
	
	@Transactional
	public void update(Freeboard fb) {
		mapper.updateFreeboard(fb);
	}
	
	@Transactional 
	public void viewUp(int boardId, String email) {
		
		int viewCnt = mapper.findViewInFiveMins(boardId, email);
		if(viewCnt > 0) {
			System.out.println("이미 5분이내에 조회했습니다.");
		}else {
			mapper.viewTableUp(boardId, email);
			mapper.boardViewUpdate(boardId);
		}
	}
	
	public Freeboard selectOneById(int id) {
		return mapper.selectOneById(id);
	}
}
