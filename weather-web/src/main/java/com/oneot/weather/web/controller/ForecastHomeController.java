package com.oneot.weather.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForecastHomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex() {
		return "home";
	}
}
