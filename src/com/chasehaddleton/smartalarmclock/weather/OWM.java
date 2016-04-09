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

class OWM {
    public final Coord coord;
    public final Weather weather[];
    public final String base;
    public final Main main;
    public final double visibility;
    public final Wind wind;
    public final Clouds clouds;
    public final double dt;
    public final Sys sys;
    public final double id;
    public final String name;
    public final double cod;

    public OWM(Coord coord, Weather[] weather, String base, Main main, double visibility, Wind wind, Clouds clouds, double
            dt, Sys sys, double id, String name, double cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public final class Coord {
        public final double lon;
        public final double lat;

        public Coord(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
        }
    }

    public final class Weather {
        public final double id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(double id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public final class Main {
        public final double temp;
        public final double pressure;
        public final double humidity;
        public final double temp_min;
        public final double temp_max;

        public Main(double temp, double pressure, double humidity, double temp_min, double temp_max) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }
    }

    public final class Wind {
        public final double speed;
        public final double deg;

        public Wind(double speed, double deg) {
            this.speed = speed;
            this.deg = deg;
        }
    }

    public final class Clouds {
        public final double all;

        public Clouds(double all) {
            this.all = all;
        }
    }

    public final class Sys {
        public final double type;
        public final double id;
        public final double message;
        public final String country;
        public final double sunrise;
        public final double sunset;

        public Sys(double type, double id, double message, String country, double sunrise, double sunset) {
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }
    }
}
