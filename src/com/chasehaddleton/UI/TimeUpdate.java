/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.UI;

import com.chasehaddleton.clock.Clock;

public class TimeUpdate implements Runnable {
    @Override
    public void run() {
        updateTime();
    }

    private void updateTime() {
        System.out.println(new Clock().getTime());
    }
}
