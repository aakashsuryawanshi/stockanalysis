package com.stock.analysis.other;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.stock.analysis.entities.Company;
import com.stock.analysis.utils.CommonUtils;

public class FileProcessing {

	public static void getSymbolsAndName() {
		try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Akash\\Downloads\\EQUITY_L.csv"),
				Charset.forName("Cp1252"))) {
			stream.forEach(line -> System.out.println(line.split(",")[0] + ", " + line.split(",")[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for(Company company : CommonUtils.companies){
			System.out.println(company.getSymbol() + "#" + company.getName());
		}
	}
}
