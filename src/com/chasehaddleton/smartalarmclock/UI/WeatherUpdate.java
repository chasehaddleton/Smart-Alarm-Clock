/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import com.chasehaddleton.smartalarmclock.weather.Weather;
import javafx.application.Platform;

public class WeatherUpdate implements Runnable {
    private final String location;
    private final Clock clock = new Clock();
    private boolean updated;
    private HomeController controller;

    public WeatherUpdate(String location, HomeController controller) {
        this.location = location;
        this.controller = controller;
        this.updated = false;
    }

    @Override
    public void run() {
        if (clock.getHour() > 6 && clock.getHour() < 9) {
            Platform.runLater(this::updateWeatherExtended);
        } else if (!this.updated) {
            {
                Platform.runLater(this::updateWeather);
            }
        }

        this.updated = !this.updated;
    }

    private void updateWeather() {
        Weather weather = new Weather(this.location);
        controller.setWeather(Weather.WeatherIDToImage(weather.getWeatherID()));
        controller.setTemperature(weather.getTemperature());
    }

    private void updateWeather(Weather weather) {
        controller.setWeather(Weather.WeatherIDToImage(weather.getWeatherID()));
        controller.setTemperature(weather.getTemperature());
    }

    private void updateWeatherExtended() {
        Weather weather = new Weather(this.location);
        updateWeather(weather);
        controller.setTemperature(weather.getMinTemperature());
        controller.setTemperature(weather.getMaxTemperature());
    }
}
