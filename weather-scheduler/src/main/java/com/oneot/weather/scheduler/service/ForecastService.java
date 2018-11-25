package com.oneot.weather.scheduler.service;

import java.net.URL;

public interface ForecastService {

	public void saveForecastListFromUrl(URL url) throws ForecastServiceUnavailableException;

}
