package com.oneot.weather.model;

import java.util.Date;

public interface IForecast {

	public Date getForecastDate();

	public String getName();

	public TimeOfDay getTimeOfDay();

	public String getPhenomenon();

	public Integer getTemp();

}
