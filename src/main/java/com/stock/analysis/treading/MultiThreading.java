package com.stock.analysis.treading;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MultiThreading {
	
	static MultiThreading multiThreading;
	boolean startStopFlag;
	static{
		multiThreading = new MultiThreading();
	}
	private MultiThreading(){
		startStopFlag = false;
	}
	public static MultiThreading getInstance(){
		return multiThreading;
	}
	public void startAnalysis(Set<String> stockNames, int interval){
		if(stockNames == null || stockNames.isEmpty() || interval == 0){
			System.out.println("Inputs not correct");
			return;
		}
		if(stockNames.size() > (interval*2)){
			System.out.println("maximum stocks allowed is " + (interval*2));
		}
		List<StockThread> threads = new ArrayList<StockThread>();
		for(String stockName : stockNames){
			StockThread thread = new StockThread();
			thread.setStockName(stockName);
			thread.setInterval(interval);
			threads.add(thread);
		}
		
		try{
			int index = 0;
			while(startStopFlag){
				threads.get(index).run();
				threads.get(index+1).run();
				System.out.println();
				TimeUnit.MINUTES.sleep(1);
				if(index == threads.size()-2){
					index = 0;
				}
				index += 2;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean isStartStopFlag() {
		return startStopFlag;
	}
	public void setStartStopFlag(boolean startStopFlag) {
		this.startStopFlag = startStopFlag;
	}
	public static void main(String[] args){
		Set<String> stockNames = new LinkedHashSet<String>();
		String[] stockNameSlot_1 = {"TCS","INFY","TECHM","AHLUCONT","ALLSEC","AUBANK","AXISBANK","BANDHANBNK","BASML","CANTABIL.NS"};
		String[] stockNameSlot_2 = {"XCHANGING", "SSWL","RAMKY","EASTSILK","APCL","MARUTI","MAXVIL", "AMARAJABAT", "JAYBARMARU", "EXCELCROP",};
		String[] stockNameSlot_3 = {"SREEL", "ENERGYDEV", "BIRLACORPN", "ASAHIINDIA","VRLLOG","BHARATFORG","TATAINVEST","JBMA","MUNJALSHOW","LINDEINDIA"};
		
		for(String stockName : stockNameSlot_1){
			stockNames.add(stockName + ".NS");
		}
		
		for(String stockName : stockNameSlot_2){
			stockNames.add(stockName + ".NS");
		}
		
		for(String stockName : stockNameSlot_3){
			stockNames.add(stockName + ".NS");
		}
		System.out.println(stockNames.size());
		MultiThreading mult = MultiThreading.getInstance();
		mult.setStartStopFlag(true);
		mult.startAnalysis(stockNames, 15);
		//RSIIndicator.execute("INFY.NS", 15, 14);
		//ExternalCall.timeSerice("INFY.NS", "15min");
	}
}
