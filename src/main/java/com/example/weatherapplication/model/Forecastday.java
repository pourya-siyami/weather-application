package com.example.weatherapplication.model;

import java.util.List;

public class Forecastday{
    public String date;
    public int date_epoch;
    public Day day;
    public Astro astro;
    public List<Hour> hour;
}