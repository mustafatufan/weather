package com.oneot.weather.web.service;

import java.util.List;

import com.oneot.weather.model.IForecast;

public interface ForecastService {
	public List<IForecast> getForecastListOfLastDay();
}
