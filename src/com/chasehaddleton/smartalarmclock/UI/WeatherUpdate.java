/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import com.chasehaddleton.smartalarmclock.weather.Weather;

public class WeatherUpdate implements Runnable {
    final String location;
    final Clock clock = new Clock();
    boolean updated;

    @Override
    public void run() {
        Weather weather = new Weather(this.location);

        if (clock.getHour() > 6 && clock.getHour() < 9) {
            updateWeatherExtended(weather);
        } else if (!this.updated) {
            {
                updateWeather(weather);
            }
        }

        this.updated = !this.updated;
    }

    public WeatherUpdate(String location) {
        this.location = location;
        this.updated = false;
    }

    private void updateWeather(Weather weather) {
        System.out.println(weather.getWeatherType());
        System.out.println(weather.getTemperature());
    }

    private void updateWeatherExtended(Weather weather) {
        updateWeather(weather);
        System.out.println(weather.getMinTemperature());
        System.out.println(weather.getMaxTemperature());
    }
}
