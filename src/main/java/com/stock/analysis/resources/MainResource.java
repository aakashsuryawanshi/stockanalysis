package com.stock.analysis.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.analysis.entities.Company;
import com.stock.analysis.entities.Results;
import com.stock.analysis.entities.WatchList;
import com.stock.analysis.indicators.Result;
import com.stock.analysis.record.RecordTransactions;
import com.stock.analysis.treading.MultiThreading;
import com.stock.analysis.utils.CommonUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/")
public class MainResource {

	@RequestMapping(value = "/companies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getAllEquityCompanies() {
		return CommonUtils.companies;
	}

	@RequestMapping(value = "/startSniper", method = RequestMethod.GET)
	public Boolean startSniper() {
		try {
			Set<String> symbols = new HashSet<String>();
			for (Company company : WatchList.getInstance().getList()) {
				symbols.add(company.getSymbol() + ".NS");
			}
			MultiThreading multiThreading = MultiThreading.getInstance();
			multiThreading.setStartStopFlag(true);
			multiThreading.startAnalysis(symbols, 15);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/stopSniper", method = RequestMethod.GET)
	public Boolean stopSniper() {
		try {
			MultiThreading multiThreading = MultiThreading.getInstance();
			multiThreading.setStartStopFlag(false);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/getSignals", method = RequestMethod.GET)
	public Set<Result> getSignals() {
		Results results = Results.getInstance();
		List<Result> result = new ArrayList<>();
		for(Result re : results.getResults()){
			result.add(new Result(re));
		}
		RecordTransactions.updateProfit(result);
		return results.getResults();
	}

}
