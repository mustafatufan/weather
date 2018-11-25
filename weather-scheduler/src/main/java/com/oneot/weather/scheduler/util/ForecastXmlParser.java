package com.oneot.weather.scheduler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.oneot.weather.model.Forecast;

public class ForecastXmlParser extends DefaultHandler {

	private static final Logger log = LoggerFactory.getLogger(ForecastXmlParser.class);

	private List<Forecast> forecastList = new ArrayList<>();
	private Map<String, Forecast> forecastMap = new HashMap<>();

	private StringBuilder data = null;

	private TimeOfDay timeOfDay;
	private Date forecastDate;
	private String nameValue;

	private boolean place = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) {
		if (qName.equals("forecast")) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				forecastDate = simpleDateFormat.parse(atts.getValue("date"));
			} catch (ParseException ex) {
				log.error(ex.getMessage(), ex);
			}
		} else if (qName.equals("night")) {
			timeOfDay = TimeOfDay.NIGHT;
		} else if (qName.equals("day")) {
			timeOfDay = TimeOfDay.DAY;
		} else if (qName.equals("place")) {
			place = true;
		} else if (qName.equals("wind")) {
			place = false;
		}
		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (place) {
			if ("name".equals(qName)) {
				nameValue = data.toString();
				if (!forecastMap.containsKey(nameValue)) {
					forecastMap.put(nameValue, new Forecast());
				}
			} else if ("phenomenon".equals(qName)) {
				if (timeOfDay.equals(TimeOfDay.NIGHT)) {
					forecastMap.get(nameValue).setNightPhenomenon(data.toString());
				} else if (timeOfDay.equals(TimeOfDay.DAY)) {
					forecastMap.get(nameValue).setDayPhenomenon(data.toString());
				}
			} else if (qName.contains("temp")) {
				if (timeOfDay.equals(TimeOfDay.NIGHT)) {
					forecastMap.get(nameValue).setNightTemp(Integer.valueOf(data.toString()));
				} else if (timeOfDay.equals(TimeOfDay.DAY)) {
					forecastMap.get(nameValue).setDayTemp(Integer.valueOf(data.toString()));
				}
			}
			if (timeOfDay.equals(TimeOfDay.DAY)) {
				if ("place".equals(qName)) {
					Forecast forecast = forecastMap.get(nameValue);
					forecast.setName(nameValue);
					forecast.setForecastDate(forecastDate);
					forecastList.add(forecast);
				}
			}
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}

	public List<Forecast> getForecastList() {
		return forecastList;
	}
}
