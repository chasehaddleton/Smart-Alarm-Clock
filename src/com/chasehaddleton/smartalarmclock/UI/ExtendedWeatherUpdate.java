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
        System.out.println(new Weather(this.location).getWeatherType());
        System.out.println(new Weather(this.location).getWeatherID());
    }
}
