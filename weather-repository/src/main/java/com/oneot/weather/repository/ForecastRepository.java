package com.oneot.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oneot.weather.model.Forecast;

@Repository("forecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

	// TODO: refactor this
	@Query("SELECT f FROM Forecast f WHERE f.forecastDate = (SELECT MAX(forecastDate) FROM Forecast) and f.forecastId = (SELECT MAX(forecastId) FROM Forecast t where f.name = t.name)")
	public List<Forecast> findForecastListOfLastDay();

}
