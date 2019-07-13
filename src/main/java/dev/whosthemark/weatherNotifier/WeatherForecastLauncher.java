package dev.whosthemark.weatherNotifier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherForecastLauncher {

	@Autowired
	WeatherForecastCaller caller;
	
	@PostConstruct
	public void init() {
		System.out.println(caller.getForecast());
	}
}
