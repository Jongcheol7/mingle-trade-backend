package com.mingletrade.mingletrade.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.domain.Freeboard;
import com.mingletrade.mingletrade.service.FreeboardService;

@RestController
@RequestMapping("/api/freeboard")
public class FreeboardController {
	
	private final FreeboardService service;

	public FreeboardController(FreeboardService service) {
		super();
		this.service = service;
	}

	@PostMapping("/insert")
	public String insert(@RequestBody Freeboard fb) {
		System.out.println("insert받은 데이터 : " + fb);
		service.insert(fb);
		return "success";
	}
	
	@PutMapping("/update")
	public String update(@RequestBody Freeboard fb) {
		System.out.println("update받은 데이터 : " + fb);
		service.update(fb);
		return "success";
	}
	
	@GetMapping("/selectAll")
	public Map<String, Object> selectAllFreeboard(@RequestParam(defaultValue = "1") int page, 
			                            @RequestParam(defaultValue = "10") int limit) {
		System.out.println("selectAllFreeboard받은 데이터 :" + page + " / " + limit);
		int start = (page - 1) * 10;
		Map<String, Object> data = service.selectAllFreeboard(start, limit);
		return data;
	}
	
	@GetMapping("/{id}")
	public Freeboard selectOneById(@PathVariable int id) {
		System.out.println("selectOneById받은 데이터 : " + id);
		return service.selectOneById(id);
	}
	
	@PostMapping("/viewUp")
	public int tempViewsUp(@RequestBody Map<String, Object> param) {
		System.out.println("tempViewsUp받은 데이터 : " + param);
		int boardId = (int) (param.get("boardId"));
		return service.tempViewsUp(boardId);
	}
}
