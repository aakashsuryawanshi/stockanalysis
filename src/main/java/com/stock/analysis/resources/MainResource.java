package com.stock.analysis.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.analysis.entities.Company;
import com.stock.analysis.treading.StockThread;
import com.stock.analysis.utils.CommonUtils;

@RestController
@RequestMapping(value="api/")
public class MainResource {

	@RequestMapping(value="/companies", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Company> testBasic(){
		return CommonUtils.companies;
	}
	
	@RequestMapping(value="/main/{word}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String test(@PathVariable(value="word")String word){
		return "Hello " + word;
	}
}
