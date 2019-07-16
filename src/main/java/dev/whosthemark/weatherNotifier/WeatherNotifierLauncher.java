package dev.whosthemark.weatherNotifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.whosthemark.weatherNotifier.connectors.IFTTTWebhookConnector;
import dev.whosthemark.weatherNotifier.connectors.WeatherForecastConnector;
import dev.whosthemark.weatherNotifier.message.NotificationBuilder;
import dev.whosthemark.weatherNotifier.model.HourlyForecast;
import dev.whosthemark.weatherNotifier.model.Notification;

@Component
public class WeatherNotifierLauncher {

	@Autowired
	private WeatherForecastConnector forecastConnector;
	
	@Autowired
	private IFTTTWebhookConnector iftttConnector;

	@Autowired
	private NotificationBuilder builder;

	@PostConstruct
	public void init() {

		HourlyForecast hourlyForecast = forecastConnector.getForecast();
		Notification notification = builder.buildMessage(hourlyForecast);
		System.out.println(forecastConnector.getForecast());
		iftttConnector.sendNotification(notification);
	}
}
