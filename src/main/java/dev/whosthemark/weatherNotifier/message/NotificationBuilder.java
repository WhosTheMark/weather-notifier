package dev.whosthemark.weatherNotifier.message;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import dev.whosthemark.weatherNotifier.model.Forecast;
import dev.whosthemark.weatherNotifier.model.HourlyForecast;
import dev.whosthemark.weatherNotifier.model.Notification;
import dev.whosthemark.weatherNotifier.model.WeatherCondition;

@Service
public class NotificationBuilder {

	public Notification buildMessage(HourlyForecast hourlyForecast) {

		List<Forecast> forecasts = hourlyForecast.getForcasts();

		var mostRecentForecast = forecasts.get(0);
		
		String nowMessage = "Maintenant : " + buildTemperatureMessage(mostRecentForecast)
				+ buildWeatherConditionMessage(mostRecentForecast) + ". ";

		var inAFewHoursForecast = forecasts.get(1);

		String inAFewHoursMessage = "Dans quelques heures : " + buildTemperatureMessage(inAFewHoursForecast)
				+ buildWeatherConditionMessage(inAFewHoursForecast);

		return new Notification(nowMessage, inAFewHoursMessage);
	}

	private String buildTemperatureMessage(Forecast forecast) {
		float temperature = forecast.getTemperature();
		return "Il fera " + temperature + " degrés. ";
	}

	@NonNull
	private String buildWeatherConditionMessage(Forecast forecast) {
		WeatherCondition weatherCondition = forecast.getWeatherCondition();
		String result = null;

		switch (weatherCondition) {
		case Atmosphere:
			result = "Attention ! Des problèmes atmosphériques seront présents !";
			break;
		case Clear:
			result = "Belle journée !";
			break;
		case Clouds:
			result = "Il y aura quelques nuages";
			break;
		case Drizzle:
			result = "Il y aura de la pluie légère";
			break;
		case Rain:
			result = "Il y aura de la pluie";
			break;
		case Snow:
			result = "Il y aura de la neige";
			break;
		case Thunderstorm:
			result = "Il y aura des orages";
			break;
		}
		
		return result;
	}
}
