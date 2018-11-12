package com.stock.analysis.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.analysis.entities.Company;
import com.stock.analysis.entities.WatchList;
import com.stock.analysis.utils.CommonUtils;

@RestController
@RequestMapping(value="api/watchlist")
public class WatchlistResource {

	@RequestMapping(value="/{symbol}/add", method=RequestMethod.GET)
	public WatchList addCompanyToWatchList(@PathVariable(value="symbol")String symbol){
		WatchList list = WatchList.getInstance();
		Company company = CommonUtils.getCompanyBySymbol(symbol);
		if(company != null){
			list.getList().add(company);
		}
		return list;
	}
	
	@RequestMapping(value="/{symbol}/remove", method=RequestMethod.GET)
	public WatchList removeCompanyToWatchList(@PathVariable(value="symbol")String symbol){
		WatchList list = WatchList.getInstance();
		Company company = CommonUtils.getCompanyBySymbol(symbol);
		list.getList().remove(company);
		return list;
	}
}
