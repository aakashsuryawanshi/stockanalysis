package com.stock.analysis.treading;

import java.time.LocalDateTime;
import java.util.Date;

import com.stock.analysis.entities.Results;
import com.stock.analysis.indicators.MACDIndicator;
import com.stock.analysis.indicators.Result;

public class StockThread extends Thread{

	String stockName;
	int interval;
	MACDIndicator macdIndicator;
	
	public StockThread(){
		macdIndicator = new MACDIndicator();
	}
	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}


	@Override
	public void run(){
		//macdIndicator.execute(stockName, interval, 0, 0);
		Results results = Results.getInstance();
		Result result = macdIndicator.getMACDHist(stockName, interval);
		if(result != null){
			if(results.getResults().contains(result)){
				results.getResults().remove(result);
			}
			results.getResults().add(result);
			results.printResults();
			System.out.println();
		}
		
		//System.out.println(LocalDateTime.now());
	}
}
