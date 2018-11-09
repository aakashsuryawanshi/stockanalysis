package com.stock.analysis.treading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.stock.analysis.indicators.RSIIndicator;
import com.stock.anaysis.common.ExternalCall;
import com.stock.anaysis.utils.AudioUtil;

public class MultiThreading {

	public static void startAnalysis(List<String> stockNames, int interval){
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
			while(true){
				threads.get(index).start();
				threads.get(index+1).start();
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
	public static void main(String[] args){
		List<String> stockNames = new ArrayList<String>();
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
		//MultiThreading.startAnalysis(stockNames, 15);
		RSIIndicator.execute("INFY.NS", 15, 14);
		//ExternalCall.timeSerice("INFY.NS", "15min");
	}
}
