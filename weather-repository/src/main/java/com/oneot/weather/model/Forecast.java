package com.oneot.weather.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "forecast")
public class Forecast implements IForecast {

	@Id
	@GeneratedValue(generator = "forecast_generator")
	@SequenceGenerator(name = "forecast_generator", sequenceName = "forecast_sequence", initialValue = 1000)
	private Long forecastId;

	@DateTimeFormat
	@Column(name = "forecast_date", nullable = false)
	private Date forecastDate;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "night_phenomenon", nullable = false)
	private String nightPhenomenon;

	@Column(name = "night_temp", nullable = false)
	private Integer nightTemp;

	@Column(name = "day_phenomenon", nullable = false)
	private String dayPhenomenon;

	@Column(name = "day_temp", nullable = false)
	private Integer dayTemp;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Override
	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getNightPhenomenon() {
		return nightPhenomenon;
	}

	public void setNightPhenomenon(String nightPhenomenon) {
		this.nightPhenomenon = nightPhenomenon;
	}

	@Override
	public Integer getNightTemp() {
		return nightTemp;
	}

	public void setNightTemp(Integer nightTemp) {
		this.nightTemp = nightTemp;
	}

	@Override
	public String getDayPhenomenon() {
		return dayPhenomenon;
	}

	public void setDayPhenomenon(String dayPhenomenon) {
		this.dayPhenomenon = dayPhenomenon;
	}

	@Override
	public Integer getDayTemp() {
		return dayTemp;
	}

	public void setDayTemp(Integer dayTemp) {
		this.dayTemp = dayTemp;
	}
}
