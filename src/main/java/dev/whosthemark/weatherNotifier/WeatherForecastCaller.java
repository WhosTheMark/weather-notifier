package dev.whosthemark.weatherNotifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import dev.whosthemark.weatherNotifier.model.HourlyForecast;

@Service
public class WeatherForecastCaller {

	@Value("${forcastUrl}")
	private String forecastUrl;
	
	@Value("${city}")
	private String city;
	
	@Value("${openWeatherAPIKey}")
	private String openWeatherAPIKey;
	
	@Value("${units}")
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
