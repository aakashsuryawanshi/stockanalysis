package com.stock.analysis.entities;

public class Company {
	String symbol;
	String name;
	
	public Company(){
		
	}
	
	public Company(String symbol, String name){
		this.symbol = symbol;
		this.name = name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
