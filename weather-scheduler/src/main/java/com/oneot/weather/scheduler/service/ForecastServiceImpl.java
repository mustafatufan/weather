package com.oneot.weather.scheduler.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.oneot.weather.model.Forecast;
import com.oneot.weather.repository.ForecastRepository;
import com.oneot.weather.scheduler.util.ForecastXmlParser;

@Service("forecastService")
public class ForecastServiceImpl implements ForecastService {

	private static final Logger log = LoggerFactory.getLogger(ForecastServiceImpl.class);

	private ForecastRepository forecastRepository;

	@Override
	public void saveForecastListFromUrl(URL url) throws ForecastServiceUnavailableException {
		try {
			List<Forecast> forecastList = getForecastListFromUrl(url);
			List<Forecast> savedList = forecastRepository.saveAll(forecastList);
			log.info(savedList.size() + " forecast records saved.");
		} catch (IOException | ParserConfigurationException | SAXException ex) {
			log.error(ex.getMessage(), ex);
			throw new ForecastServiceUnavailableException(ex);
		}
	}

	private List<Forecast> getForecastListFromUrl(URL url)
			throws IOException, ParserConfigurationException, SAXException {
		InputStream xml = getXmlFromUrl(url);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		ForecastXmlParser forecastParser = new ForecastXmlParser();
		saxParser.parse(xml, forecastParser);
		xml.close();
		return forecastParser.getForecastList();
	}

	private InputStream getXmlFromUrl(URL url) throws IOException {
		InputStream inputStream = null;
		HttpURLConnection connection = getConnectionToUrl(url);
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			inputStream = connection.getInputStream();
		}
		return inputStream;
	}

	private HttpURLConnection getConnectionToUrl(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.addRequestProperty("User-Agent", "");
		connection.connect();
		return connection;
	}

	@Autowired
	@Qualifier("forecastRepository")
	public void setForecastRepository(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}
}
