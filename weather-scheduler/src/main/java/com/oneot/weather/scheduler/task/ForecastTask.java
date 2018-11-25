package com.oneot.weather.scheduler.task;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oneot.weather.scheduler.service.ForecastService;
import com.oneot.weather.scheduler.service.ForecastServiceUnavailableException;

@Component
public class ForecastTask {

	private static final Logger log = LoggerFactory.getLogger(ForecastTask.class);

	@Value("${com.oneot.weather.scheduler.task.url}")
	private String urlParam;

	private ForecastService forecastService;

	@Scheduled(cron = "${com.oneot.weather.scheduler.task.cron}")
	public void run() {
		try {
			URL url = new URL(urlParam);
			forecastService.saveForecastListFromUrl(url);
		} catch (ForecastServiceUnavailableException | MalformedURLException ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Autowired
	@Qualifier("forecastService")
	public void setForecastService(ForecastService forecastService) {
		this.forecastService = forecastService;
	}
}