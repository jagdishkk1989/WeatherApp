# WeatherApp Android

Android Configuration Required:
1. Android Studio 3.1.2
2. minSdkVersion 15 and targetSdkVersion 27



Follow the below steps to run application.

First you need to create openweathermap API key.  
1. Got to https://openweathermap.org/
2. Sign up account and create default map api key.
3. Copy that key.
4. Go to file "gradle.properties" in project and add key to the variable below.
   OpenWeatherMapApiKey="YOUR KEY"
5. Sync Project
6. Run Project.

Note:- API key take few mins to get activiated. Please wait for mins before start test application.
This application will fetch weather data based on static lat and lng values.
