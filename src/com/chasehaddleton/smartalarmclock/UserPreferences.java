/*
 * Copyright (c) 2016. Chase Haddleton
 */

package com.chasehaddleton.smartalarmclock;

import com.sun.istack.internal.NotNull;

import java.io.*;

public class UserPreferences implements Serializable, Runnable {
    private String email = null;
    private String cityName = null, country = null, country_code = null;
    private boolean connectEmail = false;

    public UserPreferences(String email, String cityName) {
        this.email = email;
        this.cityName = cityName;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    @NotNull
    public String getCityName() {
        return cityName;
    }

    public boolean isConnectEmail() {
        return connectEmail;
    }

    @Override
    public void run() {
        File outFile = new File("resources/datastore/user.conf");
        boolean fileCreated = false;

        try {
            fileCreated = outFile.createNewFile();
        } catch (IOException ex) {

        }

        if (fileCreated) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)))) {
                oos.writeObject(this);
            } catch (IOException ex) {

            }
        }
    }

    public void savePreferences() {
        run();
    }
}
