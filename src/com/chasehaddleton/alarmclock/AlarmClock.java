/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.alarmclock;

import com.chasehaddleton.UI.TimeUpdate;
import com.chasehaddleton.UI.WeatherUpdate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlarmClock {

    public static void main(String[] args) {
        Runnable timeUpdate = new TimeUpdate();
        Runnable weatherUpdate = new WeatherUpdate("Toronto");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(timeUpdate, 0, 1, TimeUnit.MINUTES);
        executor.scheduleAtFixedRate(weatherUpdate, 0, 30, TimeUnit.MINUTES);
    }
}
