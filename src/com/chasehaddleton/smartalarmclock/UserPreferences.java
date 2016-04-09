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
