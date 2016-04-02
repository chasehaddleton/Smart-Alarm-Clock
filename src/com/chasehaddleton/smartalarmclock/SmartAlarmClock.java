/*
 * Copyright (c) 2016. Chase Haddleton
 */

// TODO integrate Gmail and Google Calendar

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

import java.io.*;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartAlarmClock extends Application {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    public static UserPreferences usrPref;
    private static Stage homeStage, configurationStage = null;

    public static void main(String[] args) {
        launch(args);
    }

    public static void loadHomeStage() {
        if (configurationStage != null) {
            configurationStage.hide();
            configurationStage = null;
        }

        Parent root = null;
        try {
            root = new FXMLLoader(SmartAlarmClock.class.getClassLoader().getResource("scene/home.fxml")).load();
        } catch (IOException ex) {
            System.err.print("Error loading primary stage. Application must exit");
            System.exit(1);
        }

        homeStage.setScene(new Scene(root));
        homeStage.setWidth(600);
        homeStage.setHeight(400);
        homeStage.setTitle("Smart Alarm Clock");

        runClock();
        homeStage.show();
    }

    private static void runClock() {
        Clock clock = new Clock();

        int timeUntilNewDay = (24 - clock.getHour()) * 60 - clock.getMinute();
        int timeUntilNewMinute = 60 - clock.getSecond();
        int timeUntilNewHour = 60 - clock.getHour();

        DateUpdate updateDate = new DateUpdate(clock, HomeController.getInstance());
        TimeUpdate updateTime = new TimeUpdate(clock, HomeController.getInstance());
        WeatherUpdate updateWeather = new WeatherUpdate(usrPref.getCityName(), HomeController.getInstance());

        updateDate.run();
        updateTime.run();
        updateWeather.run();

        SmartAlarmClock.executor.scheduleAtFixedRate(updateDate, timeUntilNewDay, 1440, TimeUnit.MINUTES);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateTime, timeUntilNewMinute, 60, TimeUnit.SECONDS);
        SmartAlarmClock.executor.scheduleAtFixedRate(updateWeather, timeUntilNewHour, 20, TimeUnit.MINUTES);
    }

    @Override
    public void start(Stage homeStage) throws Exception {
        SmartAlarmClock.homeStage = homeStage;

        usrPref = null;
        URL userPrefFile = getClass().getClassLoader().getResource("datastore/user.conf");

        if (userPrefFile == null) {
            configurationStage = new Stage();
            Parent configure = new FXMLLoader(getClass().getClassLoader().getResource("scene/configure.fxml")).load();

            configurationStage.setTitle("Smart Alarm Clock - Configure");
            configurationStage.setScene(new Scene(configure));
            configurationStage.setWidth(800);
            configurationStage.setHeight(600);
            configurationStage.show();
        } else {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(userPrefFile.toURI()))));
            usrPref = (UserPreferences) ois.readObject();

            loadHomeStage();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

}