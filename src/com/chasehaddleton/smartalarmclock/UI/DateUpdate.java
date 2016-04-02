/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import javafx.application.Platform;

public class DateUpdate implements Runnable {
    private Clock clock;
    private HomeController controller;

    public DateUpdate(Clock clock, HomeController controller) {
        this.clock = clock;
        this.controller = controller;
    }

    @Override
    public void run() {
        Platform.runLater(this::updateDate);
    }

    private void updateDate() {
        controller.setDate(clock.getDay() + "  |  " + clock.getMonth());
    }
}
