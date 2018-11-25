package com.oneot.weather.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;
import com.oneot.weather.TestApplication;
import com.oneot.weather.model.Forecast;
import com.oneot.weather.model.TimeOfDay;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class ForecastRepositoryTest {

	private ForecastRepository forecastRepository;

	@Test
	public void saveSingleForecastTest() {
		List<Forecast> forecastList = generateForecastList(1);
		List<Forecast> addedList = forecastRepository.saveAll(forecastList);
		Assert.assertEquals(forecastList, addedList);
	}

	@Test
	public void save100ForecastTest() {
		List<Forecast> forecastList = generateForecastList(100);
		List<Forecast> addedList = forecastRepository.saveAll(forecastList);
		Assert.assertEquals(forecastList, addedList);
	}

	private List<Forecast> generateForecastList(int size) {
		List<Forecast> forecastList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			forecastList.addAll(generateForecastPair());
		}
		return forecastList;
	}

	private List<Forecast> generateForecastPair() {
		List<Forecast> pair = new ArrayList<>();

		Date forecastDate = generateForecastDate();
		String name = generateName();

		Forecast day = new Forecast();
		day.setForecastDate(forecastDate);
		day.setName(name);
		day.setPhenomenon(generatePhenomenon());
		day.setTemp(generateTemp());
		day.setTimeOfDay(TimeOfDay.DAY);
		pair.add(day);

		Forecast night = new Forecast();
		night.setForecastDate(forecastDate);
		night.setName(name);
		night.setPhenomenon(generatePhenomenon());
		night.setTemp(generateTemp());
		night.setTimeOfDay(TimeOfDay.NIGHT);
		pair.add(night);

		return pair;
	}

	private Date generateForecastDate() {
		Date date = null;
		Faker faker = new Faker();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithTime = faker.date().future(3, TimeUnit.DAYS);
		try {
			date = sdf.parse(sdf.format(dateWithTime));
		} catch (ParseException e) {
			// TODO: log
		}
		return date;
	}

	private String generateName() {
		Faker faker = new Faker();
		return faker.address().cityName();
	}

	private String generatePhenomenon() {
		Faker faker = new Faker();
		return faker.weather().description();
	}

	private Integer generateTemp() {
		Faker faker = new Faker();
		return faker.number().numberBetween(-50, 50);
	}

	@Autowired
	public void setForecastRepository(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}

}
