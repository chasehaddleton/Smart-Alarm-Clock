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

package com.chasehaddleton.smartalarmclock.weather;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {
    private final URL queryURL;
    private OWM currentWeather;

    public Weather(String location) {
        final String apiKey = "4eeab82836963ac33f44a142f8ff01e9";
        URL tempURL = null;

        try {
            tempURL = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey +
                    "&units=metric");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } finally {
            queryURL = tempURL;
        }

        if (queryURL != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(queryURL.openConnection().getInputStream()))) {
                currentWeather = new Gson().fromJson(br, OWM.class);
            } catch (IOException | NullPointerException ex) {
                System.err.println("Error while fetching the weather");
            }
        } else {
            System.err.println("Error while creating the weather request URL");
        }
    }

    public static String WeatherIDToImage(Double id) {
        return WeatherID.valueOf("ID_" + String.valueOf((long) Math.floor(id))).imageName;
    }

    public void updateWeather() {
        if (queryURL != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(queryURL.openConnection().getInputStream()))) {
                currentWeather = new Gson().fromJson(br, OWM.class);
            } catch (IOException | NullPointerException ex) {
                System.err.println("Error while fetching the weather");
            }
        } else {
            System.err.println("Error while creating the weather request URL");
        }
    }

    public String getWeatherDescription() {
        return currentWeather.weather[0].description;
    }

    public double getWeatherID() {
        return currentWeather.weather[0].id;
    }

    public double getTemperature() {
        return Math.round(currentWeather.main.temp);
    }

    public double getMaxTemperature() {
        return Math.round(currentWeather.main.temp_max);
    }

    public double getMinTemperature() {
        return Math.round(currentWeather.main.temp_min);
    }

    private enum WeatherID {
        ID_200("thunderstorm with light rain", "light thunderstorm"),
        ID_201("thunderstorm with rain", "light thunderstorm"),
        ID_202("thunderstorm with heavy rain", "heavy thunderstorm"),
        ID_210("light thunderstorm", "thunderstorm"),
        ID_211("thunderstorm", "thunderstorm"),
        ID_212("heavy thunderstorm", "heavy thunderstorm"),
        ID_221("ragged thunderstorm", "heavy thunderstorm"),
        ID_230("thunderstorm with light drizzle", "light thunderstorm"),
        ID_231("thunderstorm with drizzle", "light thunderstorm"),
        ID_232("thunderstorm with heavy drizzle", "heavy thunderstorm"),
        ID_300("light intensity drizzle", "light rain"),
        ID_301("drizzle", "drizzle"),
        ID_302("heavy intensity drizzle", "heavy rain"),
        ID_310("light intensity drizzle rain", "drizzle"),
        ID_311("drizzle rain", "drizzle"),
        ID_312("heavy intensity drizzle rain", "heavy rain"),
        ID_313("shower rain and drizzle", "sun showers"),
        ID_314("heavy shower rain and drizzle", "heavy rain"),
        ID_321("shower drizzle", "drizzle"),
        ID_500("light rain", "light rain"),
        ID_501("moderate rain", "light rain"),
        ID_502("heavy intensity rain", "heavy rain"),
        ID_503("very heavy rain", "heavy rain"),
        ID_504("extreme rain", "heavy rain"),
        ID_511("freezing rain", "snow"),
        ID_520("light intensity shower rain", "light rain"),
        ID_521("shower rain", "sun showers"),
        ID_522("heavy intensity shower rain", "heavy rain"),
        ID_531("ragged shower rain", "heavy rain"),
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
        ID_701("mist", "fog"),
        ID_711("smoke", "heavy fog"),
        ID_721("haze", "heavy fog"),
        ID_731("sand, dust whirls", "heavy fog"),
        ID_741("fog", "heavy fog"),
        ID_751("sand", "sand"),
        ID_761("dust", "dust"),
        ID_762("volcanic ash", "meteor strike"),
        ID_771("squalls", "squalls"),
        ID_781("tornado", "tornado"),
        ID_800("clear sky", "clear"),
        ID_801("few clouds", "sun cloud"),
        ID_802("scattered clouds", "scattered clouds"),
        ID_803("broken clouds", "scattered clouds"),
        ID_804("overcast clouds", "scattered clouds"),
        ID_900("tornado", "tornado"),
        ID_901("tropical storm", "heavy thunderstorm"),
        ID_902("hurricane", "heavy thunderstorm"),
        ID_903("cold", "cold"),
        ID_904("hot", "hot"),
        ID_905("windy", "heavy wind"),
        ID_906("hail", "snow"),
        ID_951("calm", "clear"),
        ID_952("light breeze", "light wind"),
        ID_953("gentle breeze", "light wind"),
        ID_954("moderate breeze", "heavy wind"),
        ID_955("fresh breeze", "heavy wind"),
        ID_956("strong breeze", "squalls"),
        ID_957("high wind, near gale", "squalls"),
        ID_958("gale", "squalls"),
        ID_959("severe gale", "squalls"),
        ID_960("storm", "thunderstorm"),
        ID_961("violent storm", "heavy thunderstorm"),
        ID_962("hurricane", "heavy thunderstorm");

        final String type, imageName;

        WeatherID(String type, String imageName) {
            this.type = type;
            this.imageName = imageName;
        }
    }
}
