package dev.whosthemark.weatherNotifier.connectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.whosthemark.weatherNotifier.model.Notification;

@Service
public class IFTTTWebhookConnector {

	@Value("${ifttt.url}")
	private String ifttttUrl;

	@Value("${ifttt.key}")
	private String iftttKey;

	@Value("${ifttt.eventName}")
	private String eventName;

	private RestTemplate restTemplate = new RestTemplate();

	public void sendNotification(Notification notification) {
		
		String result = restTemplate.postForObject(ifttttUrl, notification, String.class, eventName, iftttKey);
		System.out.println(result);
	}
}
