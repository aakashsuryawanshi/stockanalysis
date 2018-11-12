package com.stock.analysis.indicators;

public class Result {
	String symbol;
	String indicator;
	double indicatorValue;
	String signal;
	String time;
	double stockValue;
	double boughtAt;

	public Result() {

	}

	public Result(String symbol, String indicator, double value, String time, String signal) {
		this.symbol = symbol;
		this.indicator = indicator;
		this.indicatorValue = value;
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

	public double getValue() {
		return indicatorValue;
	}

	public void setValue(double value) {
		this.indicatorValue = value;
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

	public double getBoughtAt() {
		return boughtAt;
	}

	public void setBoughtAt(double boughtAt) {
		this.boughtAt = boughtAt;
	}

	@Override
	public boolean equals(Object obj) {
		if (((Result) obj).symbol.equalsIgnoreCase(this.symbol)
				&& ((Result) obj).indicator.equalsIgnoreCase(this.indicator)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((symbol == null | indicator == null) ? 0 : (symbol.hashCode() + indicator.hashCode()));
		return result;
	}

}
