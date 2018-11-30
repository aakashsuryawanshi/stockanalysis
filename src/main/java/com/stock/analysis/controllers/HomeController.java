package com.stock.analysis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "*")
public class HomeController {

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView("index");
	    mav.addObject("user", "abc");
	    return mav;
	}
}
