package dev.whosthemark.weatherNotifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class Notification {

	@Getter
	@Setter
	@JsonProperty("value1")
	private String nowForecast;

	@Getter
	@Setter
	@JsonProperty("value2")
	private String inAFewHoursForecast;

	public Notification(String nowForecast, String inAFewHoursForecast) {
		this.nowForecast = nowForecast;
		this.inAFewHoursForecast = inAFewHoursForecast;
	}
}
