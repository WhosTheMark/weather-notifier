package dev.whosthemark.weatherNotifier.connectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import dev.whosthemark.weatherNotifier.model.HourlyForecast;

@Service
public class WeatherForecastConnector {

	@Value("${forcast.url}")
	private String forecastUrl;
	
	@Value("${forecast.city}")
	private String city;
	
	@Value("${forecast.APIKey}")
	private String openWeatherAPIKey;
	
	@Value("${forecast.units}")
	private String units;
	
	private String expandedUrl;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public HourlyForecast getForecast() {
		return restTemplate.getForObject(expandedUrl, HourlyForecast.class);
	}
	
	@PostConstruct
	public void init() {
		var builder = UriComponentsBuilder.fromHttpUrl(forecastUrl)
				.queryParam("q", city)
				.queryParam("units", units)
				.queryParam("appid", openWeatherAPIKey);
		expandedUrl = builder.toUriString();
	}
}
