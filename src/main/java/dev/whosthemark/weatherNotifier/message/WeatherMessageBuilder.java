package dev.whosthemark.weatherNotifier.message;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import dev.whosthemark.weatherNotifier.exception.WeatherNotFoundException;
import dev.whosthemark.weatherNotifier.model.WeatherCondition;

@Service
public class WeatherMessageBuilder {

	public String buildWeatherMessage(float temperature, WeatherCondition weatherCondition) {
		return buildTemperatureMessage(temperature) + buildWeatherConditionMessage(weatherCondition) + ". ";
	}

	private String buildTemperatureMessage(float temperature) {
		return "Il fera " + temperature + " degrés. ";
	}

	@NonNull
	private String buildWeatherConditionMessage(WeatherCondition weatherCondition) {
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
			throw new WeatherNotFoundException();
		case Thunderstorm:
			result = "Il y aura des orages";
			break;
		}
		
		return result;
	}
}
