/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;

public class TimeUpdate implements Runnable {
    @Override
    public void run() {
        updateTime();
    }

    private void updateTime() {
        System.out.println(new Clock().getTime());
    }
}
