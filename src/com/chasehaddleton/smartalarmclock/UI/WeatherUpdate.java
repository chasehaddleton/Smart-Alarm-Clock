/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.ui;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import com.chasehaddleton.smartalarmclock.weather.Weather;
import javafx.application.Platform;

import java.text.DecimalFormat;

public class WeatherUpdate implements Runnable {
    private final static DecimalFormat tempFormat = new DecimalFormat("0");
    private final String location;
    private final Clock clock = new Clock();
    private boolean updated;
    private ClockController controller;
    private Weather currentWeather;

    public WeatherUpdate(String location, ClockController controller) {
        this.location = location;
        this.controller = controller;
        this.updated = false;
    }

    @Override
    public void run() {
        currentWeather = new Weather(this.location);
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
        controller.setWeather(Weather.WeatherIDToImage(currentWeather.getWeatherID()));
        controller.setTemperature(tempFormat.format(currentWeather.getTemperature()));
    }

    private void updateWeatherExtended() {
        updateWeather();
        //controller.setTemperature(tempFormat.format(currentWeather.getTemperature()));
        //controller.setTemperature(tempFormat.format(currentWeather.getTemperature()));
    }
}
