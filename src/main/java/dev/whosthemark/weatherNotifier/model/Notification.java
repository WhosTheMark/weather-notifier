package dev.whosthemark.weatherNotifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class Notification {

	@Getter
	@Setter
	@JsonProperty("value1")
	private String message;

}
