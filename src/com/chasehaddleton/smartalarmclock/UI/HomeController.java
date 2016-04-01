/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock.UI;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;

public class HomeController extends GridPane {
    private static HomeController instance = null;

    @FXML
    private Text textTime;

    @FXML
    private Text textDate;

    @FXML
    private Text textTemperature;

    @FXML
    private ImageView imgWeather;

    public HomeController() {
        if (instance == null) instance = this;
    }

    public static HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    void setTime(String time) {
        textTime.setText(time);
    }

    void setDate(String date) {
        textDate.setText(date);
    }

    void setTemperature(double temperature) {
        textTemperature.setText(temperature + "°C");
    }

    void setWeather(String imageName) {
        try {
            URL image = getClass().getClassLoader().getResource("images/" + imageName + ".png");
            imgWeather.setImage(new Image(image.toString()));
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
