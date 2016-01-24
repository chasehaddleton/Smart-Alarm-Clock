/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock;

import com.chasehaddleton.smartalarmclock.UI.DateUpdate;
import com.chasehaddleton.smartalarmclock.UI.TimeUpdate;
import com.chasehaddleton.smartalarmclock.UI.WeatherUpdate;
import com.chasehaddleton.smartalarmclock.clock.Clock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartAlarmClock {

    public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        String location = "Toronto";
        Clock clock = new Clock();

        int timeUntilNewDay = (24 - clock.getHour()) * 60 - clock.getMinute();
        int timeUntilNewMinute = 60 - clock.getSecond();
        int timeUntilNewHour = 60 - clock.getHour();

        System.out.println(timeUntilNewDay);
        System.out.println(timeUntilNewMinute);

        DateUpdate updateDate = new DateUpdate();
        TimeUpdate updateTime = new TimeUpdate();
        WeatherUpdate updateWeather = new WeatherUpdate(location);

        updateDate.run();
        updateTime.run();
        updateWeather.run();

        SmartAlarmClock.executor.scheduleAtFixedRate(updateDate, timeUntilNewDay, 3600, TimeUnit.MINUTES);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateTime, timeUntilNewMinute, 60, TimeUnit.SECONDS);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateWeather, timeUntilNewHour, 15, TimeUnit.MINUTES);
    }
}