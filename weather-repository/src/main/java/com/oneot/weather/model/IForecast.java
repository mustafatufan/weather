package com.oneot.weather.model;

import java.util.Date;

public interface IForecast {

	public Date getForecastDate();

	public String getName();

	public String getNightPhenomenon();

	public Integer getNightTemp();

	public String getDayPhenomenon();

	public Integer getDayTemp();

}
