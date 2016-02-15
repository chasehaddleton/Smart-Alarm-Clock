/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;

public class DateUpdate implements Runnable {
    Clock clock = new Clock();

    @Override
    public void run() {
        UpdateDate();
    }

    private void UpdateDate() {
        System.out.println(clock.getDay() + "|" + clock.getMonth());
    }
}
