package com.oneot.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneot.weather.model.Forecast;

@Repository("forecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

}
