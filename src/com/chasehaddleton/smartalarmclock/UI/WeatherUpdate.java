/*
 * MIT License
 *
 * Copyright (c) 2016. Chase Haddleton
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
            Platform.runLater(this::updateWeather);
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
