/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock;

import com.chasehaddleton.smartalarmclock.UI.TimeUpdate;
import com.chasehaddleton.smartalarmclock.UI.WeatherUpdate;
import com.chasehaddleton.smartalarmclock.scheduleEvents.ScheduleEvents;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartAlarmClock {

    public static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        SmartAlarmClock.executor.scheduleAtFixedRate(new TimeUpdate(), 0, 1, TimeUnit.MINUTES);
        SmartAlarmClock.executor.scheduleAtFixedRate(new WeatherUpdate("Toronto"), 0, 30, TimeUnit.MINUTES);
        SmartAlarmClock.executor.scheduleAtFixedRate(new ScheduleEvents("Toronto"), 0, 20, TimeUnit.MINUTES);
    }
}