package dev.whosthemark.weatherNotifier.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class HourlyForecast {

	@Getter
	@Setter
	@JsonProperty("list")
	private List<Forecast> forcasts;
}
