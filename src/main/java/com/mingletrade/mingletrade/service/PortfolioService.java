package com.mingletrade.mingletrade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mingletrade.mingletrade.domain.Portfolio;
import com.mingletrade.mingletrade.mapper.PortfolioMapper;

@Service
public class PortfolioService {

	private PortfolioMapper mapper;
	
	public PortfolioService(PortfolioMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<Portfolio> selectByEmail(String email, String currency) {
		return mapper.selectByEmail(email, currency);
	}
	
	public void deleteOneByEmail(int id, String email) {
		mapper.deleteOneByEmail(id, email);
	}
	
	public void updatePortfolio(Portfolio portfolio) {
		mapper.updatePortfolio(portfolio);
	}
	
	public void insertPortfolio(Portfolio portfolio) {
		mapper.insertPortfolio(portfolio);
	}
}
