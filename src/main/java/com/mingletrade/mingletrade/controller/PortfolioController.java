package com.mingletrade.mingletrade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<Map<String, Object>> selectByEmail(@RequestParam String email) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			System.out.println("selectByEmail 받은 내용 : " + email);
			List<Portfolio> list = service.selectByEmail(email);
			result.put("status", "success");
			result.put("data", list);
			return ResponseEntity.ok(result);
		}catch (Exception e) {
			result.put("status", "fail");
	        result.put("message", e.getMessage());
	        return ResponseEntity.status(500).body(result);
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteOneByEmail(@RequestBody Map<String, Object> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			System.out.println("deleteOneByEmail 받은 내용 : " + param.toString());
			int id = (int) param.get("id");
			String email = (String) param.get("email");
			service.deleteOneByEmail(id, email);
			
			result.put("status", "success");
			result.put("message", "삭제 완료");
			return ResponseEntity.ok(result);
		}catch (Exception e) {
			result.put("status", "fail");
			result.put("message", e.getMessage());
			return ResponseEntity.status(500).body(result);
		}
		
		
	}
}
