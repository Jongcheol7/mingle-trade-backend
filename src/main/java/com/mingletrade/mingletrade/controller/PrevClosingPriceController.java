package com.mingletrade.mingletrade.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mingletrade.mingletrade.domain.PrevClosingPrice;
import com.mingletrade.mingletrade.service.PrevClosingPriceService;

@RestController
@RequestMapping("/api/prev-price")
public class PrevClosingPriceController {

	private final PrevClosingPriceService service;

	public PrevClosingPriceController(PrevClosingPriceService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/insert")
	public String insertTest(@RequestBody PrevClosingPrice dto) {
		service.insert(dto);
		return "Insert 성공";
	}
	
	@GetMapping("/selectAll/{closeDate}")
	public List<PrevClosingPrice> selectAll(@PathVariable String closeDate){
		return service.selectAll(closeDate);
	}
	
	@GetMapping("/fetch-binance")
	public void fetchFromBinance() {
		service.fetchAndInsertFromBinance();
	}
}
