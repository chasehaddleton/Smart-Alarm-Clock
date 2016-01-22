/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.weather.Weather;

public class ExtendedWeatherUpdate implements Runnable {
    final String location;

    @Override
    public void run() {
        updateWeather();
    }

    public ExtendedWeatherUpdate(String location) {
        this.location = location;
    }

    private void updateWeather () {
        Weather weather = new Weather(this.location);
        System.out.println(weather.getWeatherType());
        System.out.println(weather.getWeatherID());
        System.out.println(weather.getTemperature());
        System.out.println(weather.getMinTemperature());
        System.out.println(weather.getMaxTemperature());
    }
}
