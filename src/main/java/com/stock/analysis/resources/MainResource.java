package com.stock.analysis.resources;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.analysis.treading.StockThread;

@RestController
public class MainResource {

	@RequestMapping(value="/main", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public StockThread testBasic(){
		return new StockThread();
	}
	
	@RequestMapping(value="/main/{word}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String test(@PathVariable(value="word")String word){
		return "Hello " + word;
	}
}
