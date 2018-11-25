package com.oneot.weather.scheduler.service;

public class ForecastServiceUnavailableException extends Exception {

	private static final long serialVersionUID = 6437140320637764217L;

	public ForecastServiceUnavailableException(Throwable cause) {
		super(cause);
	}
}
