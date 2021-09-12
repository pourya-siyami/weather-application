package com.example.weatherapplication.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherStatus weatherStatus;

    @GetMapping("weather")
    public String showWeatherPage() {
        return "weather";
    }

    @GetMapping("call-current-weather")
    public String callWeather(Model model) {
        model.addAttribute("callWeather", weatherStatus);
        return "callcurrentweather";
    }

    @PostMapping("current-weather")
    public String showCurrentWeather(@ModelAttribute(value = "callWeather") WeatherStatus ws, Model model) {
        WeatherStatus weatherStatus = weatherService.callCurrentWeather(ws.city);
        if (weatherStatus != null) {
            model.addAttribute("currentWeather", weatherStatus);
            return "currentweather";
        } else {
            return "404";
        }
    }

    @GetMapping("call-forecast-weather")
    public String callForecastWeather(Model model) {
        model.addAttribute("callWeather", weatherStatus);
        return "callforecastweather";
    }

    @PostMapping("forecast-weather")
    public String showForecastWeather(@ModelAttribute(value = "callWeather") WeatherStatus ws, Model model) {
        WeatherStatus weatherStatus = weatherService.callForecastWeather(ws.city);
        if (weatherStatus != null) {
            model.addAttribute("forecastWeather", weatherStatus);
            return "forecastweather";
        } else {
            return "404";
        }
    }
}