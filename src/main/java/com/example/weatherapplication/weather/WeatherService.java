package com.example.weatherapplication.weather;

import com.example.weatherapplication.util.Const;
import com.google.gson.Gson;
import io.jsonwebtoken.io.IOException;
import okhttp3.OkHttpClient;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public WeatherStatus callCurrentWeather(String city) throws IOException {
        String resaulturl = String.format(Const.C_URL_ADDRESS, Const.URL_VERSION, city, Const.C_TOKEN);
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(resaulturl)
                .build();
        try {
            String fullResponse = client.newCall(request).execute().body().string();
            Gson gson = new Gson();
            WeatherStatus weatherStatus = gson.fromJson(fullResponse, WeatherStatus.class);
            return new WeatherStatus(weatherStatus.main, weatherStatus.name, weatherStatus.weather);
        } catch (Exception e) {
            return new WeatherStatus("Exception");
        }
    }

    public WeatherStatus callForecastWeather(String city) throws IOException {
        String resulturl = String.format(Const.F_URL_ADDRESS, Const.F_TOKEN, city, Const.DAYS);
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(resulturl)
                .build();
        try {
            String fullResponse = client.newCall(request).execute().body().string();
            Gson gson = new Gson();
            WeatherStatus weatherStatus = gson.fromJson(fullResponse, WeatherStatus.class);
            return new WeatherStatus(weatherStatus.forecast, weatherStatus.location);
        } catch (Exception e) {
            return new WeatherStatus("Exception");
        }
    }
}