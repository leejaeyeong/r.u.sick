package com.example.weather_retro;

import java.util.List;

public class Repo {
    private Main main;
    private Wind wind;
    private List<Weather> weather;
    private String name;

    public Main getMain() {
        return main;
    }

    public Wind getWind() { return wind; }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getName() { return name; }
}