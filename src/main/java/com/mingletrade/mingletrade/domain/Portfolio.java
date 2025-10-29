package com.mingletrade.mingletrade.domain;

public class Portfolio {
	
	private int id;
	private String email;
	private String symbol;
	private double enterPrice;
	private String currency;
	private int quantity;
	
	public Portfolio(int id, String email, String symbol, double enterPrice, String currency, int quantity) {
		super();
		this.id = id;
		this.email = email;
		this.symbol = symbol;
		this.enterPrice = enterPrice;
		this.currency = currency;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getEnterPrice() {
		return enterPrice;
	}

	public void setEnterPrice(double enterPrice) {
		this.enterPrice = enterPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", email=" + email + ", symbol=" + symbol + ", enterPrice=" + enterPrice
				+ ", currency=" + currency + ", quantity=" + quantity + "]";
	}
	
}
