package com.oneot.weather.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oneot.weather.model.Forecast;
import com.oneot.weather.model.IForecast;
import com.oneot.weather.repository.ForecastRepository;

@Service("forecastService")
public class ForecastServiceImpl implements ForecastService {

	private ForecastRepository forecastRepository;

	@Override
	public List<IForecast> getForecastListOfLastDay() {
		List<Forecast> forecastList = forecastRepository.findForecastListOfLastDay();
		return forecastList.stream().map(forecast -> convertForecast(forecast)).collect(Collectors.toList());
	}

	private IForecast convertForecast(Forecast forecast) {
		return forecast;
	}

	@Autowired
	@Qualifier("forecastRepository")
	public void setForecastRepository(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}

}
