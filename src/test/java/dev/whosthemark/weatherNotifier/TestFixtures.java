package dev.whosthemark.weatherNotifier;

import java.util.List;

import dev.whosthemark.weatherNotifier.model.Forecast;
import dev.whosthemark.weatherNotifier.model.HourlyForecast;
import dev.whosthemark.weatherNotifier.model.WeatherCondition;

public class TestFixtures {

	public static HourlyForecast getFixtureOk() {
		Forecast firstForecast = new Forecast(25f, WeatherCondition.Rain);
		Forecast secondForecast = new Forecast(30f, WeatherCondition.Clear);
		List<Forecast> forcasts = List.of(firstForecast, secondForecast);

		HourlyForecast hourlyForecast = new HourlyForecast();
		hourlyForecast.setForcasts(forcasts);
		return hourlyForecast;
	}

	public static HourlyForecast getFixtureKO() {
		Forecast firstForecast = new Forecast(25f, WeatherCondition.Snow);
		Forecast secondForecast = new Forecast(30f, WeatherCondition.Snow);
		List<Forecast> forcasts = List.of(firstForecast, secondForecast);

		HourlyForecast hourlyForecast = new HourlyForecast();
		hourlyForecast.setForcasts(forcasts);
		return hourlyForecast;
	}

}
