/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.weather;

class OWM {
    public final Coord coord;
    public final Weather weather[];
    public final String base;
    public final Main main;
    public final int visibility;
    public final Wind wind;
    public final Clouds clouds;
    public final long dt;
    public final Sys sys;
    public final long id;
    public final String name;
    public final long cod;

    public OWM(Coord coord, Weather[] weather, String base, Main main, int visibility, Wind wind, Clouds clouds, long
            dt, Sys sys, long id, String name, long cod) {
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
        public final int id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public final class Main {
        public final double temp;
        public final int pressure;
        public final int humidity;
        public final double temp_min;
        public final double temp_max;

        public Main(double temp, int pressure, int humidity, double temp_min, double temp_max) {
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
        public final int all;

        public Clouds(int all) {
            this.all = all;
        }
    }

    public final class Sys {
        public final int type;
        public final int id;
        public final double message;
        public final String country;
        public final long sunrise;
        public final long sunset;

        public Sys(int type, int id, double message, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }
    }
}
