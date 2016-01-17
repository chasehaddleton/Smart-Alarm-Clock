/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.*;

public class Weather {
    private final URL queryURL;

    public Weather(String location) {
        final String apiKey = "4eeab82836963ac33f44a142f8ff01e9";
        URL tempURL = null;

        try {
            tempURL = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey +
                    "&units=metric");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } finally {
            this.queryURL = (tempURL != null) ? tempURL : null;
        }
    }

    private OWM updateWeather() {
        BufferedReader br = null;

        try {
            URLConnection con = this.queryURL.openConnection();
            InputStream is = con.getInputStream();

            br = new BufferedReader(new InputStreamReader(is));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(br, OWM.class);
    }

    public String getWeatherType() {
        OWM currentWeather = updateWeather();

        return currentWeather.weather[0].description;
    }

    public int getWeatherID() {
        OWM currentWeather = updateWeather();

        return currentWeather.weather[0].id;
    }

    public double getTemperature() {
        OWM currentWeather = updateWeather();

        return currentWeather.main.temp;
    }

    public double getMaxTemperature() {
        OWM currentWeather = updateWeather();

        return currentWeather.main.temp_max;
    }

    public double getMinTemperature() {
        OWM currentWeather = updateWeather();

        return currentWeather.main.temp_min;
    }
}
