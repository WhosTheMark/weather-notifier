package dev.whosthemark.weatherNotifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.whosthemark.weatherNotifier.connectors.IFTTTWebhookConnector;
import dev.whosthemark.weatherNotifier.connectors.WeatherForecastConnector;

@Component
public class WeatherNotifierLauncher {

	@Autowired
	private WeatherForecastConnector forecastConnector;
	
	@Autowired
	private IFTTTWebhookConnector iftttConnector;

	@PostConstruct
	public void init() {
		System.out.println(forecastConnector.getForecast());
		iftttConnector.sendNotification(null);
	}
}
