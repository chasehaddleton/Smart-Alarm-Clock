/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.weather.Weather;

public class WeatherUpdate implements Runnable {
    final String location;

    @Override
    public void run() {
        updateWeather();
    }

    public WeatherUpdate(String location) {
        this.location = location;
    }

    private void updateWeather () {
        Weather weather = new Weather(this.location);
        System.out.println(weather.getWeatherType());
        System.out.println(weather.getTemperature());
    }
}
