package com.oneot.weather.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oneot.weather.model.IForecast;
import com.oneot.weather.web.service.ForecastService;

@Controller
@RequestMapping("/api")
public class ForecastApiController {

	private ForecastService forecastService;

	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody List<IForecast> getForecast() {
		List<IForecast> list = new ArrayList<>();
		list = forecastService.getForecastListOfLastDay();
		return list;
	}

	@Autowired
	@Qualifier("forecastService")
	public void setForecastService(ForecastService forecastService) {
		this.forecastService = forecastService;
	}

}
