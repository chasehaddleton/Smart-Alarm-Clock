/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.weather;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
            this.queryURL = tempURL;
        }
    }

    public static String WeatherIDToImage(Double id) {
        return WeatherID.valueOf("ID_" + String.valueOf((long) Math.floor(id))).imageName;
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

    public double getWeatherID() {
        OWM currentWeather = updateWeather();

        return currentWeather.weather[0].id;
    }

    public double getTemperature() {
        OWM currentWeather = updateWeather();

        return Math.round(currentWeather.main.temp);
    }

    public double getMaxTemperature() {
        OWM currentWeather = updateWeather();

        return Math.round(currentWeather.main.temp_max);
    }

    public double getMinTemperature() {
        OWM currentWeather = updateWeather();

        return Math.round(currentWeather.main.temp_min);
    }

    private enum WeatherID {
        ID_200("thunderstorm with light rain", "thunderstorm"),
        ID_201("thunderstorm with rain", "thunderstorm"),
        ID_202("thunderstorm with heavy rain", "thunderstorm"),
        ID_210("light thunderstorm", "thunderstorm"),
        ID_211("thunderstorm", "thunderstorm"),
        ID_212("heavy thunderstorm", "thunderstorm"),
        ID_221("ragged thunderstorm", "thunderstorm"),
        ID_230("thunderstorm with light drizzle", "thunderstorm"),
        ID_231("thunderstorm with drizzle", "thunderstorm"),
        ID_232("thunderstorm with heavy drizzle", "thunderstorm"),
        ID_300("light intensity drizzle", "rain"),
        ID_301("drizzle", "rain"),
        ID_302("heavy intensity drizzle", "rain"),
        ID_310("light intensity drizzle rain", "rain"),
        ID_311("drizzle rain", "rain"),
        ID_312("heavy intensity drizzle rain", "rain"),
        ID_313("shower rain and drizzle", "rain"),
        ID_314("heavy shower rain and drizzle", "rain"),
        ID_321("shower drizzle", "rain"),
        ID_500("light rain", "rain"),
        ID_501("moderate rain", "rain"),
        ID_502("heavy intensity rain", "rain"),
        ID_503("very heavy rain", "rain"),
        ID_504("extreme rain", "rain"),
        ID_511("freezing rain", "rain"),
        ID_520("light intensity shower rain", "rain"),
        ID_521("shower rain", "rain"),
        ID_522("heavy intensity shower rain", "rain"),
        ID_531("ragged shower rain", "rain"),
        ID_600("light snow", "snow"),
        ID_601("snow", "snow"),
        ID_602("heavy snow", "snow"),
        ID_611("sleet", "snow"),
        ID_612("shower sleet", "snow"),
        ID_615("light rain and snow", "snow"),
        ID_616("rain and snow", "snow"),
        ID_620("light shower snow", "snow"),
        ID_621("shower snow", "snow"),
        ID_622("heavy shower snow", "snow"),
        ID_701("mist", "mist"),
        ID_711("smoke", "smoke"),
        ID_721("haze", "haze"),
        ID_731("sand, dust whirls", "sand, dust whirls"),
        ID_741("fog", "fog"),
        ID_751("sand", "sand"),
        ID_761("dust", "dust"),
        ID_762("volcanic ash", "volcanic ash"),
        ID_771("squalls", "squalls"),
        ID_781("tornado", "tornado"),
        ID_800("clear sky", "clear"),
        ID_801("few clouds", "cloudy"),
        ID_802("scattered clouds", "cloudy"),
        ID_803("broken clouds", "cloudy"),
        ID_804("overcast clouds", "cloudy"),
        ID_900("tornado", "tornado"),
        ID_901("tropical storm", "tropical storm"),
        ID_902("hurricane", "hurricane"),
        ID_903("cold", "cold"),
        ID_904("hot", "hot"),
        ID_905("windy", "windy"),
        ID_906("hail", "hail"),
        ID_951("calm", "clear"),
        ID_952("light breeze", "light breeze"),
        ID_953("gentle breeze", "gentle breeze"),
        ID_954("moderate breeze", "moderate breeze"),
        ID_955("fresh breeze", "fresh breeze"),
        ID_956("strong breeze", "strong breeze"),
        ID_957("high wind, near gale", "high wind, near gale"),
        ID_958("gale", "gale"),
        ID_959("severe gale", "severe gale"),
        ID_960("storm", "storm"),
        ID_961("violent storm", "violent storm"),
        ID_962("hurricane", "hurricane");

        final String type, imageName;

        WeatherID(String type, String imageName) {
            this.type = type;
            this.imageName = imageName;
        }
    }
}
