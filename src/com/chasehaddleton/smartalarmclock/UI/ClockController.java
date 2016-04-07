/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.ui;

import javafx.fxml.FXML;
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
            URL image = getClass().getClassLoader().getResource("scene/images/" + WordUtils.capitalize(imageName).replaceAll("[^A-z]", "") + ".png");
            imgWeather.setImage(new Image(image.toString()));
        } catch (NullPointerException ex) {
            System.err.println("Request file: " + WordUtils.capitalize(imageName).replaceAll("[^A-z]", "") + ".png");
            ex.printStackTrace();
        }
    }
}
