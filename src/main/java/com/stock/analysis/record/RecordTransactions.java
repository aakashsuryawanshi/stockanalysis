package com.stock.analysis.record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.stock.analysis.entities.Results;
import com.stock.analysis.indicators.Result;

public class RecordTransactions {

	public static void updateProfit(List<Result> results) {
		List<Result> previousResults = readFromFile();
		double profit = 0;
		for (Result oldResult : previousResults) {
			for (Result newResult : results) {
				if (oldResult.getSymbol().equalsIgnoreCase(newResult.getSymbol())) {
					if (oldResult.getSignal().equalsIgnoreCase("buy") && newResult.getSignal().equalsIgnoreCase("sell")) {
						profit += newResult.getValue() - oldResult.getValue();
					}
				}
			}
			if(oldResult.getSymbol().equalsIgnoreCase("profit")){
				profit += oldResult.getValue();
			}
		}
		Result profitResult = new Result();
		profitResult.setSymbol("Profit");
		profitResult.setSignal("Buy");
		profitResult.setValue(profit);
		results.add(profitResult);
		writeToFile(results);
	}

	public static List<Result> readFromFile() {
		String fileName = "E:\\work\\Workspace\\analysis\\configs\\data\\records.csv";
		List<String> list = new ArrayList<>();
		List<Result> results = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			list = br.lines().collect(Collectors.toList());
			for (String item : list) {
				String[] elements = item.split(",");
				Result result = new Result();
				result.setSymbol(elements[0]);
				result.setValue(Double.parseDouble(elements[1]));
				result.setSignal(elements[2]);
				results.add(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public static void writeToFile(List<Result> results){
		Path path = Paths.get("E:\\work\\Workspace\\analysis\\configs\\data\\records.csv");
		 
		try (BufferedWriter writer = Files.newBufferedWriter(path))
		{
			for(Result result : results){
				if(result.getSignal().equalsIgnoreCase("buy") || result.getSymbol().equalsIgnoreCase("profit")){
					writer.write(result.getSymbol() + "," + result.getStockValue() + "," + result.getSignal()+ "\n");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
