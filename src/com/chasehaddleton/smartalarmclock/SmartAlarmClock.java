/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock;

import com.chasehaddleton.smartalarmclock.UI.DateUpdate;
import com.chasehaddleton.smartalarmclock.UI.HomeController;
import com.chasehaddleton.smartalarmclock.UI.TimeUpdate;
import com.chasehaddleton.smartalarmclock.UI.WeatherUpdate;
import com.chasehaddleton.smartalarmclock.clock.Clock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartAlarmClock extends Application {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private Locale locale = Locale.getDefault();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new FXMLLoader(getClass().getClassLoader().getResource("scene/home.fxml")).load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(600);
        primaryStage.setHeight(425);
        primaryStage.setTitle("Smart Alarm Clock");

        /*
           Clock code
        */

        String location;
        location = "Toronto";
        Clock clock = new Clock();

        int timeUntilNewDay = (24 - clock.getHour()) * 60 - clock.getMinute();
        int timeUntilNewMinute = 60 - clock.getSecond();
        int timeUntilNewHour = 60 - clock.getHour();

        DateUpdate updateDate = new DateUpdate(clock, HomeController.getInstance());
        TimeUpdate updateTime = new TimeUpdate(clock, HomeController.getInstance());
        WeatherUpdate updateWeather = new WeatherUpdate(location, HomeController.getInstance());

        updateDate.run();
        updateTime.run();
        updateWeather.run();

        SmartAlarmClock.executor.scheduleAtFixedRate(updateDate, timeUntilNewDay, 1440, TimeUnit.MINUTES);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateTime, timeUntilNewMinute, 60, TimeUnit.SECONDS);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateWeather, timeUntilNewHour, 20, TimeUnit.MINUTES);

        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

}