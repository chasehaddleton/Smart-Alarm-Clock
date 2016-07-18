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

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang.WordUtils;

import java.net.URL;

public class ClockController extends AnchorPane {
    private static ClockController instance = null;

    @FXML
    private Stage stage;

    @FXML
    private Text textTime;

    @FXML
    private Text textDate;

    @FXML
    private Text textTemperature;

    @FXML
    private ImageView imgWeather;

    @FXML
    private DialogPane paneNotification;

    public ClockController() {
        if (instance == null) instance = this;
    }

    public static ClockController getInstance() {
        if (instance == null) {
            instance = new ClockController();
        }
        return instance;
    }

    void setTime(String time) {
        textTime.setText(time);
    }

    void setDate(String date) {
        textDate.setText(date);
    }

    void setTemperature(String temperature) {
        textTemperature.setText(temperature + "°C");
    }

    void setWeather(String imageName) {
        try {
            URL image = getClass().getClassLoader().getResource("scene/images/" + WordUtils.capitalize(imageName)
                    .replaceAll("[^A-z]", "-") + ".png");
            imgWeather.setImage(new Image(image.toString()));
        } catch (NullPointerException ex) {
            System.err.println("Request file: " + WordUtils.capitalize(imageName).replaceAll("[^A-z]", "") + ".png");
            ex.printStackTrace();
        }
    }
}
