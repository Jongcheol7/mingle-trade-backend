package com.mingletrade.mingletrade.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrevClosingPrice {

	private Long id;
	private LocalDate closeDate;
	private String symbol;
	private Double price;
	private String logoUrl;
	private LocalDateTime createdAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "PrevClosingPrice [id=" + id + ", closeDate=" + closeDate + ", symbol=" + symbol + ", price=" + price
				+ ", logoUrl=" + logoUrl + ", createdAt=" + createdAt + "]";
	}
}
