package com.stock.analysis.indicators;

import com.stock.analysis.utils.CommonUtils;

public class Result {
	String symbol;
	String stockName;
	String indicator;
	double indicatorValue;
	int changed = 0;
	String signal;
	String time;
	double stockValue;
	double boughtAt;

	public Result(){
		
	}
	public Result(Result that) {
		this.symbol = that.symbol;
		this.stockName = that.stockName;
		this.indicator = that.indicator;
		this.indicatorValue = that.indicatorValue;
		this.signal = that.signal;
		this.time = that.time;
		this.stockValue = that.stockValue;
		this.boughtAt = that.boughtAt;
	}

	public Result(String symbol, String indicator, double value, String time, String signal) {
		this.symbol = symbol;
		this.stockName = CommonUtils.getCompanyBySymbol(symbol).getName();
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
	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getIndicatorValue() {
		return indicatorValue;
	}

	public void setIndicatorValue(double indicatorValue) {
		this.indicatorValue = indicatorValue;
	}

	
	public int getChanged() {
		return changed;
	}
	public void setChanged(int changed) {
		this.changed = changed;
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
