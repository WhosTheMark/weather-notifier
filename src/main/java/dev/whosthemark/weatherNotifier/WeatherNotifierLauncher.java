package dev.whosthemark.weatherNotifier;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.whosthemark.weatherNotifier.connectors.IFTTTWebhookConnector;
import dev.whosthemark.weatherNotifier.connectors.WeatherForecastConnector;
import dev.whosthemark.weatherNotifier.message.WeatherMessageBuilder;
import dev.whosthemark.weatherNotifier.model.Forecast;
import dev.whosthemark.weatherNotifier.model.HourlyForecast;
import dev.whosthemark.weatherNotifier.model.Notification;

@Component
public class WeatherNotifierLauncher {

	@Autowired
	private WeatherForecastConnector forecastConnector;
	
	@Autowired
	private IFTTTWebhookConnector iftttConnector;

	@Autowired
	private WeatherMessageBuilder builder;

	@PostConstruct
	public void notifyWeather() {

		HourlyForecast hourlyForecast = forecastConnector.getForecast();
		Notification notification = buildNotification(hourlyForecast);
		iftttConnector.sendNotification(notification);
	}

	public Notification buildNotification(HourlyForecast hourlyForecast) {

		List<Forecast> forecasts = hourlyForecast.getForcasts();

		// First forecast
		var mostRecentForecast = forecasts.get(0);
		String nowMessage = "Maintenant : " + builder.buildWeatherMessage(mostRecentForecast);

		// Second forecast
		var inAFewHoursForecast = forecasts.get(1);
		String inAFewHoursMessage = "Dans quelques heures : " + builder.buildWeatherMessage(inAFewHoursForecast);

		return new Notification(nowMessage, inAFewHoursMessage);
	}
}
