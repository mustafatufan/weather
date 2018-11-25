package com.oneot.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oneot.weather.model.Forecast;

@Repository("forecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

	@Query("SELECT f FROM Forecast f WHERE f.forecastDate = (SELECT MAX(forecastDate) FROM Forecast)")
	public List<Forecast> findForecastListOfLastDay();

}
