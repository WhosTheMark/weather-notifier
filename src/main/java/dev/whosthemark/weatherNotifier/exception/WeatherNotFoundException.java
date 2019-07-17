package dev.whosthemark.weatherNotifier.exception;

public class WeatherNotFoundException extends RuntimeException {

	public WeatherNotFoundException() {
		super("404 Not Found : Il n'y a pas de neige à Toulouse ! ");
	}

}
