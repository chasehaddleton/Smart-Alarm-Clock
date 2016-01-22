package com.chasehaddleton.smartalarmclock.scheduleEvents;

import com.chasehaddleton.smartalarmclock.SmartAlarmClock;
import com.chasehaddleton.smartalarmclock.UI.ExtendedWeatherUpdate;
import com.chasehaddleton.smartalarmclock.clock.Clock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Chase on 2016-01-22.
 */
public class ScheduleEvents implements Runnable {
    String location;

    public ScheduleEvents(String location) {
        this.location = location;
    }

    @Override
    public void run() {
        Clock clock = new Clock();

        if (clock.getHour() > 6 && clock.getHour() < 9) {
            SmartAlarmClock.executor.scheduleAtFixedRate(new ExtendedWeatherUpdate(this.location), 0, 10, TimeUnit.MINUTES);
        }
    }
}
