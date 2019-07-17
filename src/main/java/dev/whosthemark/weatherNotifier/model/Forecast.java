package dev.whosthemark.weatherNotifier.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import dev.whosthemark.weatherNotifier.deserializer.ForecastDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonDeserialize(using = ForecastDeserializer.class)
public class Forecast {

	@Getter
	@Setter
	private float temperature;

	@Getter
	@Setter
	private LocalDateTime dateTime;

	@Getter
	@Setter
	private WeatherCondition weatherCondition;

	public Forecast(float temperature, LocalDateTime dateTime, WeatherCondition weatherCondition) {
		this.temperature = temperature;
		this.dateTime = dateTime;
		this.weatherCondition = weatherCondition;
	}

	public Forecast(float temperature, WeatherCondition weatherCondition) {
		this.temperature = temperature;
		this.weatherCondition = weatherCondition;
	}
}
