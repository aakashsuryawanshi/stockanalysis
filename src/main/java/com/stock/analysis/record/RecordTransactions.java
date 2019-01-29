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
		List<Result> newResults = readFromFile();
		double profit = 0;
		for (Result oldResult : previousResults) {
			boolean found = false;
			for (Result newResult : results) {
				if (oldResult.getSymbol().equalsIgnoreCase(newResult.getSymbol())) {
					if (oldResult.getSignal().equalsIgnoreCase("buy") && newResult.getSignal().equalsIgnoreCase("sell")
							&& newResult.getStockValue() > oldResult.getStockValue()) {
						profit += newResult.getStockValue() - oldResult.getStockValue();
					}
					found = true;
				}
			}
			if (oldResult.getSymbol().equalsIgnoreCase("profit")) {
				profit += oldResult.getStockValue();
			}
			if (!found) {
				newResults.add(oldResult);
			}
		}
		Result profitResult = new Result();
		profitResult.setSymbol("Profit");
		profitResult.setSignal("Buy");
		profitResult.setStockValue(profit);
		results.add(profitResult);
		writeToFile(results);
	}

	public static void updateResult(List<Result> newResults) {
		List<Result> oldResults = readFromFile();
		List<Result> updated = new ArrayList<Result>();
		double profit = 0.0d;
		for (Result oldR : oldResults) {
			boolean found = false;
			for (Result newR : newResults) {
				if (!oldR.getSymbol().equalsIgnoreCase("Profit")
						&& oldR.getSymbol().equalsIgnoreCase(newR.getSymbol())) {
					found = true;
					if (oldR.getSignal().equalsIgnoreCase("buy") && newR.getSignal().equalsIgnoreCase("buy")) {
						updated.add(oldR);
					}
					else if (oldR.getSignal().equalsIgnoreCase("buy") && newR.getSignal().equalsIgnoreCase("sell")
							&& newR.getStockValue() > oldR.getStockValue()) {
						profit += (newR.getStockValue() - oldR.getStockValue());
					}
					else if(oldR.getSignal().equalsIgnoreCase("buy") && newR.getSignal().equalsIgnoreCase("sell")
							&& newR.getStockValue() < oldR.getStockValue()){
						found = false;
					}
				}
			}
			if (!oldR.getSymbol().equalsIgnoreCase("Profit") && !found) {
				updated.add(oldR);
			}
			if (oldR.getSymbol().equalsIgnoreCase("Profit")) {
				profit += oldR.getStockValue();
			}
		}
		for (Result newR : newResults) {
			boolean found = false;
			for (Result oldR : oldResults) {
				if (oldR.getSymbol().equalsIgnoreCase(newR.getSymbol())) {
					found = true;
				}
			}
			if (!found && newR.getSignal().equalsIgnoreCase("buy")) {
				updated.add(newR);
			}
		}
		Result profitResult = new Result();
		profitResult.setSymbol("Profit");
		profitResult.setSignal("Buy");
		profitResult.setStockValue(profit);
		updated.add(profitResult);
		writeToFile(updated);
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
				result.setStockValue(Double.parseDouble(elements[1]));
				result.setSignal(elements[2]);
				results.add(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void writeToFile(List<Result> results) {
		Path path = Paths.get("E:\\work\\Workspace\\analysis\\configs\\data\\records.csv");

		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (Result result : results) {
				if (result.getSignal() != null && result.getSymbol() != null) {
					if (result.getSignal().equalsIgnoreCase("buy") || result.getSymbol().equalsIgnoreCase("profit")) {
						writer.write(
								result.getSymbol() + "," + result.getStockValue() + "," + result.getSignal() + "\n");
					}
				} else {
					System.out.println("Fields in result empty at RecordTraction line no. 73");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
