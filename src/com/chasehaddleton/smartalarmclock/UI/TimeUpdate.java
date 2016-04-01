/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import javafx.application.Platform;

public class TimeUpdate implements Runnable {
    private Clock clock;
    private HomeController controller;

    public TimeUpdate(Clock clock, HomeController controller) {
        this.clock = clock;
        this.controller = controller;
    }


    @Override
    public void run() {
        Platform.runLater(this::updateTime);
    }

    private void updateTime() {
        controller.setTime(clock.getTime());
    }
}
