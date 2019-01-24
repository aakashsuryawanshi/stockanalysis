package com.stock.analysis.entities;

public class MACDInfo {
	String time;
	double macd;
	double hist;
	double signal;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getMacd() {
		return macd;
	}
	public void setMacd(double macd) {
		this.macd = macd;
	}
	public double getHist() {
		return hist;
	}
	public void setHist(double hist) {
		this.hist = hist;
	}
	public double getSignal() {
		return signal;
	}
	public void setSignal(double signal) {
		this.signal = signal;
	}
	
	
}
