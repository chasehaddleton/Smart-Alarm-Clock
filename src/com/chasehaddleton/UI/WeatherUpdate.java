/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.UI;

import com.chasehaddleton.weather.Weather;

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
        System.out.println(new Weather(this.location).getWeatherType());
    }
}
