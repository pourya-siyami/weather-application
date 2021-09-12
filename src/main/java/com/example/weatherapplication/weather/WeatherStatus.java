package com.example.weatherapplication.weather;

import com.example.weatherapplication.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStatus {
    //current weather fields
    public Main main;
    public List<Weather> weather;
    public String base;
    public int visibility;
    public int dt;
    public int timezone;
    public int id;
    public String name;
    public int cod;
    public String city;

    //forecast weather fields
    public Location location;
    public Current current;
    public Forecast forecast;

    //current-weather-constructor
    public WeatherStatus(Main main, String name, List<Weather> weather) {
        this.main = main;
        this.name = name;
        this.weather = weather;
    }

    ///forecast-weather-constructor
    public WeatherStatus(Forecast forecast, Location location) {
        this.forecast = forecast;
        this.location = location;
    }

    public WeatherStatus() {
    }

    public WeatherStatus(String message) {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
