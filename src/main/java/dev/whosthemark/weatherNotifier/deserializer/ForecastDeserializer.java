package dev.whosthemark.weatherNotifier.deserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import dev.whosthemark.weatherNotifier.model.Forecast;

public class ForecastDeserializer extends StdDeserializer<Forecast> {

	private static final long serialVersionUID = 1L;

	public ForecastDeserializer() {
		super(Forecast.class);
	}

	public ForecastDeserializer(Class<Forecast> t) {
		super(t);
	}

	@Override
	public Forecast deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		var node = jp.getCodec().readTree(jp);

		float temperature = getTemperature(node);
		var weatherCondition = getWeatherCondition(node);
		var dateTime = getDateTime(node);
		
		Forecast forecast = new Forecast();
		forecast.setWeatherCondition(weatherCondition);
		forecast.setDateTime(dateTime);
		forecast.setTemperature(temperature);

		return forecast;
	}

	private LocalDateTime getDateTime(TreeNode node) {
		var dateTimeLong = ((NumericNode) node.get("dt")).asLong();
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(dateTimeLong), ZoneId.systemDefault());
	}

	private String getWeatherCondition(TreeNode node) {
		var jsonWeatherList = node.get("weather");
		var firstJsonWeather = jsonWeatherList.get(0);
		return ((TextNode) firstJsonWeather.get("main")).asText();
	}

	private float getTemperature(TreeNode node) {
		var mainNode = node.get("main");
		return (float) ((DoubleNode) mainNode.get("temp")).doubleValue();
	}

}
