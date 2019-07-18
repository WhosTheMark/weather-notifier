# weather-notifier

### How to run the application?

1. Copy the **file src/main/resources/application.properties.skeleton** to **src/main/resources/application.properties**
2. To retrieve the weather forcasts, an APIKey is needed. You must create an OpenWeather account (don't worry, it is __FREE__). Link : https://home.openweathermap.org/users/sign_up. Copy this APIKey to the property **forecast.APIKey**.

3. To send messages to your phone, you need an IFTTT account. Link : https://ifttt.com/
4. In your IFTTT account a Webhook applet must be created. Call the event **weather_notification**. In the message to display add the ingredients **value1** **value2**. (You must install the IFTTT mobile app too).
5. The creation of the webhook generates a IFTTT key. Copy this key to the property **ifttt.key**.
6. Add the project to your favorite IDE and run it as any Spring Boot application :tada:
