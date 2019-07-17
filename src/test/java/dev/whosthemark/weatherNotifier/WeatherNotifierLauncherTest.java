package dev.whosthemark.weatherNotifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.whosthemark.weatherNotifier.connectors.IFTTTWebhookConnector;
import dev.whosthemark.weatherNotifier.connectors.WeatherForecastConnector;
import dev.whosthemark.weatherNotifier.exception.WeatherNotFoundException;
import dev.whosthemark.weatherNotifier.message.WeatherMessageBuilder;
import dev.whosthemark.weatherNotifier.model.Forecast;
import dev.whosthemark.weatherNotifier.model.HourlyForecast;
import dev.whosthemark.weatherNotifier.model.Notification;
import dev.whosthemark.weatherNotifier.model.WeatherCondition;

public class WeatherNotifierLauncherTest {

	@InjectMocks
	private WeatherNotifierLauncher launcher;

	@Mock
	private WeatherForecastConnector forecastConnector;

	@Mock
	private IFTTTWebhookConnector iftttConnector;

	@Mock
	private WeatherMessageBuilder builder;

	@Captor
	private ArgumentCaptor<Notification> notificationCaptor;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private HourlyForecast getFixtureOk() {
		Forecast firstForecast = new Forecast(25f, WeatherCondition.Rain);
		Forecast secondForecast = new Forecast(30f, WeatherCondition.Clear);
		List<Forecast> forcasts = List.of(firstForecast, secondForecast);

		HourlyForecast hourlyForecast = new HourlyForecast();
		hourlyForecast.setForcasts(forcasts);
		return hourlyForecast;
	}

	private HourlyForecast getFixtureKO() {
		Forecast firstForecast = new Forecast(25f, WeatherCondition.Snow);
		Forecast secondForecast = new Forecast(30f, WeatherCondition.Snow);
		List<Forecast> forcasts = List.of(firstForecast, secondForecast);

		HourlyForecast hourlyForecast = new HourlyForecast();
		hourlyForecast.setForcasts(forcasts);
		return hourlyForecast;
	}

	@Test
	public void testOK() {

		HourlyForecast hourlyForecast = getFixtureOk();

		when(forecastConnector.getHourlyForecast()).thenReturn(hourlyForecast);

		when(builder.buildWeatherMessage(anyFloat(), any())).thenReturn("Mon message");
		
		//when(builder.buildWeatherMessage(anyFloat(), any())).thenReturn("Mon message").thenReturn("Mi mensaje");
		
		/*when(builder.buildWeatherMessage(anyFloat(), any())).thenAnswer(i -> {
			float temperature = i.getArgument(0);
			return temperature == 25f ? "Mon message" : "Mi mensaje";
		});*/

		//when(builder.buildWeatherMessage(eq(25f), eq(WeatherCondition.Rain))).thenReturn("Mon message");
		//when(builder.buildWeatherMessage(eq(30f), eq(WeatherCondition.Clear))).thenReturn("Mi mensaje");
		
		// when(builder.buildWeatherMessage(anyFloat(), any())).thenCallRealMethod();

		launcher.notifyWeather();

		verify(iftttConnector, times(1)).sendNotification(notificationCaptor.capture());

		Notification notification = notificationCaptor.getValue();

		assertEquals(notification.getNowForecast(), "Maintenant : Mon message");

	}

	@Test(expected = WeatherNotFoundException.class)
	public void testKO() {

		HourlyForecast hourlyForecast = getFixtureKO();
		when(forecastConnector.getHourlyForecast()).thenReturn(hourlyForecast);
		// when(builder.buildWeatherMessage(anyFloat(), any())).thenCallRealMethod();
		when(builder.buildWeatherMessage(anyFloat(), any())).thenThrow(new WeatherNotFoundException());
		launcher.notifyWeather();
	}



}
