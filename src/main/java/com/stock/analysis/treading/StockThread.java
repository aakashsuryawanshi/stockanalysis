package com.stock.analysis.treading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.stock.analysis.entities.Results;
import com.stock.analysis.indicators.MACDIndicator;
import com.stock.analysis.indicators.Result;
import com.stock.analysis.record.RecordTransactions;
import com.stock.analysis.resources.MainResource;

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
		Result result = generateResult();//macdIndicator.getMACDHistCustom(stockName, interval);
		if(result != null){
			if(results.getResults().contains(result)){
				results.getResults().remove(result);
			}
			results.getResults().add(result);
			results.printResults();
			System.out.println();
			Results results1 = Results.getInstance();
			List<Result> result1 = new ArrayList<>();
			for(Result re : results1.getResults()){
				result1.add(new Result(re));
			}
			//RecordTransactions.updateProfit(result1);
			RecordTransactions.updateResult(result1);
		}
		
		//System.out.println(LocalDateTime.now());
	}
	
	public Result generateResult(){
		Result re = new Result();
		String[] names = {"rohit","akash","hrudaya","astik","shrushti","atisk","sonu","abhi","parth","khushi"};
		re.setStockName(names[(int)(Math.random()*((9-0)+1))+0]);
		//System.out.println("Stock Name: " + re.getStockName());
		re.setSymbol(re.getStockName());
		re.setIndicator("MACD");
		re.setIndicatorValue(Math.random()*(1+1+1)-1);
		//System.out.println("Indicator Value: " + re.getIndicatorValue());
		re.setStockValue((int)(Math.random()*((1000-10)+1))+10);
		//System.out.println("Indicator Value: " + re.getStockValue());
		if(re.getIndicatorValue() < 0){
			re.setSignal("sell");
		}
		else{
			re.setSignal("buy");
		}
		//System.out.println("Signal: "+re.getSignal());
		//System.out.println();
		return re;
	}
}
