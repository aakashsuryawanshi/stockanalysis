package com.stock.analysis.entities;

import java.util.HashSet;
import java.util.Set;

import com.stock.analysis.indicators.Result;

public class Results {

	private static Results myObj;
    Set<Result> results;
    static{
        myObj = new Results();
    }
     
    private Results(){
    	results = new HashSet<Result>();
    }
     
    public static Results getInstance(){
        return myObj;
    }

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}
    
    public void printResults(){
    	for(Result re : results){
    		System.out.println(re.getTime() + " " + re.getSymbol() + " " + re.getIndicator() + " " + re.getSignal() + " " + re.getStockValue()); 
    	}
    }
}
