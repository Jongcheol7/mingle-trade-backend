package com.mingletrade.mingletrade.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.domain.Portfolio;
import com.mingletrade.mingletrade.service.PortfolioService;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
	
	private PortfolioService service;

	public PortfolioController(PortfolioService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/select")
	public List<Portfolio> selectByEmail(@RequestParam String email) {
		System.out.println("selectByEmail 받은 내용 : " + email);
		return service.selectByEmail(email);
	}
	
}
