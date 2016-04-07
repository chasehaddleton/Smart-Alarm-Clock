/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.ui;

import com.chasehaddleton.smartalarmclock.SmartAlarmClock;
import com.chasehaddleton.smartalarmclock.UserPreferences;
import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConfigureController extends GridPane {
    private static ConfigureController instance = null;

    @FXML
    private Stage stage;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textCityName;

    public ConfigureController() {
        if (instance == null) instance = this;
    }

    public static ConfigureController getInstance() {
        if (instance == null) {
            instance = new ConfigureController();
        }
        return instance;
    }

    public void submitAction() {
        if (textEmail.getText() != null && textCityName.getText() != null) {
            SmartAlarmClock.usrPref = new UserPreferences(getEmail(), getCityName());
            SmartAlarmClock.usrPref.savePreferences();
        }

        Platform.runLater(SmartAlarmClock::loadHomeStage);
    }

    @NotNull
    public String getEmail() {
        return textEmail.getText();
    }

    @NotNull
    public String getCityName() {
        return textCityName.getText();
    }
}
