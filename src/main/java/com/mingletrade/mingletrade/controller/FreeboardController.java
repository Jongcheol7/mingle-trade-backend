package com.mingletrade.mingletrade.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		System.out.println("받은 데이터 : " + fb);
		service.insert(fb);
		return "success";
	}
}
