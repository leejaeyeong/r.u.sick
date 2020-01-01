package com.example.weather_retro;

public class Main {
    Double temp;
    Integer pressure;
    Integer humidity;
    Double temp_min;
    Double temp_max;

    public Double getTemp() {
        temp = Double.parseDouble(String.format("%.1f", temp - 273.15));
        return temp;
    }


    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getTemp_min() {
        return temp_min - 273.15;
    }

    public Double getTemp_max() {
        return temp_max - 273.15;
    }
}