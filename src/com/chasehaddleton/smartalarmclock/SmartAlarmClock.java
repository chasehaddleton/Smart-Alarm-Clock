/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock;

import com.chasehaddleton.smartalarmclock.UI.TimeUpdate;
import com.chasehaddleton.smartalarmclock.UI.WeatherUpdate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartAlarmClock {

    public static void main(String[] args) {
        Runnable timeUpdate = new TimeUpdate();
        Runnable weatherUpdate = new WeatherUpdate("Toronto");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(timeUpdate, 0, 1, TimeUnit.MINUTES);
        executor.scheduleAtFixedRate(weatherUpdate, 0, 30, TimeUnit.MINUTES);
    }
}
