package com.stock.analysis.entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import com.stock.analysis.indicators.Result;

public class Results {

	private static Results myObj;
	Set<Result> results;
	static {
		myObj = new Results();
	}

	private Results() {
		results = new HashSet<Result>();
	}

	public static Results getInstance() {
		return myObj;
	}

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public void printResults() {
		for (Result re : results) {
			System.out.println(re.getTime() + " " + re.getSymbol() + " " + re.getIndicator() + " " + re.getSignal()
					+ " " + re.getStockValue());
		}
		try (FileWriter f = new FileWriter("E:\\work\\Workspace\\analysis\\configs\\data\\logs.txt", true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);){

			for (Result result : results) {
				if (result.getSignal() != null && result.getSymbol() != null) {
					p.println(result.getSymbol() + "," + result.getStockValue() + "," + result.getSignal());
				} else {
					System.out.println("Fields in result empty at RecordTraction line no. 73");
				}
			}
			p.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
