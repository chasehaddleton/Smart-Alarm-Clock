/*
 * MIT License
 *
 * Copyright (c) 2016. Chase Haddleton
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// TODO integrate Gmail and Google Calendar

package com.chasehaddleton.smartalarmclock;

import com.chasehaddleton.smartalarmclock.clock.Clock;
import com.chasehaddleton.smartalarmclock.ui.ClockController;
import com.chasehaddleton.smartalarmclock.ui.DateUpdate;
import com.chasehaddleton.smartalarmclock.ui.TimeUpdate;
import com.chasehaddleton.smartalarmclock.ui.WeatherUpdate;
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
    public static UserPreferences usrPref = null;
    private static Stage homeStage = null, configurationStage = null;

    public static void main(String[] args) {
        launch(args);
    }

    public static void loadHomeStage() {
        if (configurationStage != null) {
            configurationStage.hide();
            configurationStage = null;
        }

        homeStage = new Stage();
        Parent root = null;

        try {
            root = new FXMLLoader(SmartAlarmClock.class.getClassLoader().getResource("scene/clock.fxml")).load();
        } catch (IOException ex) {
            System.err.print("Error loading primary stage. Application must exit");
            System.exit(1);
        }

        homeStage.setScene(new Scene(root));
        homeStage.setWidth(800);
        homeStage.setHeight(500);
        homeStage.setTitle("Smart Alarm Clock");

        runClock();
        homeStage.show();
    }

    private static void loadConfigureStage() {
        if (homeStage != null) {
            homeStage.hide();
            homeStage = null;
        }

        configurationStage = new Stage();
        Parent configure = null;

        try {
            configure = new FXMLLoader(SmartAlarmClock.class.getClassLoader().getResource("scene/configure.fxml")).load();
        } catch (IOException ex) {
            System.err.print("Error loading primary stage. Application must exit");
            System.exit(1);
        }

        configurationStage.setTitle("Smart Alarm Clock - Configure");
        configurationStage.setScene(new Scene(configure));
        configurationStage.setWidth(800);
        configurationStage.setHeight(600);
        configurationStage.show();
    }

    private static void runClock() {
        ClockController clockController = ClockController.getInstance();
        Clock clock = new Clock();

        int timeUntilNewDay = (24 - clock.getHour()) * 60 - clock.getMinute();
        int timeUntilNewMinute = 60 - clock.getSecond();
        int timeUntilNextThirdHour = (60 - clock.getMinute()) % 20;

        DateUpdate updateDate = new DateUpdate(clock, clockController);
        TimeUpdate updateTime = new TimeUpdate(clock, clockController);
        WeatherUpdate updateWeather = new WeatherUpdate(usrPref.getCityName(), clockController);

        updateDate.run();
        updateTime.run();
        updateWeather.run();

        executor.scheduleAtFixedRate(updateDate, timeUntilNewDay, 1440, TimeUnit.MINUTES);
        executor.scheduleAtFixedRate(updateTime, timeUntilNewMinute, 60, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(updateWeather, timeUntilNextThirdHour, 20, TimeUnit.MINUTES);
    }

    @Override
    public void start(Stage homeStage) {
        try {
            URL userPrefFile = getClass().getClassLoader().getResource("datastore/user.conf");

            if (userPrefFile == null) {
                loadConfigureStage();
            } else {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(userPrefFile.toURI()))));
                usrPref = (UserPreferences) ois.readObject();

                loadHomeStage();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}