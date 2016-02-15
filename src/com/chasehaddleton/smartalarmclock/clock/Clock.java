/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.clock;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    public String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getYear() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("u"));
    }

    public String getMonth() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M"));
    }

    public String getDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("d"));
    }

    public String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public int getHour() {
        return Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("HH")));
    }

    public int getMinute() {
        return Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));
    }

    public int getSecond() {
        return Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("ss")));
    }
}