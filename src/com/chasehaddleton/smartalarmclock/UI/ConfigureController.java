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
