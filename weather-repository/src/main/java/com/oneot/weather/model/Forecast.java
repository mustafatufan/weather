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
public class Forecast {

	@Id
	@GeneratedValue(generator = "forecast_generator")
	@SequenceGenerator(name = "forecast_generator", sequenceName = "forecast_sequence", initialValue = 1000)
	private Long forecast_id;

	@DateTimeFormat
	@Column(name = "forecast_date", nullable = false)
	private Date forecastDate;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "time_of_day", nullable = false)
	private TimeOfDay timeOfDay;

	@Column(name = "phenomenon", nullable = false)
	private String phenomenon;

	@Column(name = "temp", nullable = false)
	private Integer temp;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TimeOfDay getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(TimeOfDay timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public String getPhenomenon() {
		return phenomenon;
	}

	public void setPhenomenon(String phenomenon) {
		this.phenomenon = phenomenon;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

}
