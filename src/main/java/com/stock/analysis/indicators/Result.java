package com.stock.analysis.indicators;

public class Result {
	String symbol;
	String indicator;
	String value;
	String signal;
	String time;
	double stockValue;
	
	public Result(){
		
	}
	
	public Result(String symbol, String indicator, String value, String time, String signal){
		this.symbol = symbol;
		this.indicator = indicator;
		this.value = value;
		this.time = time;
		this.signal = signal;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	
	
}
