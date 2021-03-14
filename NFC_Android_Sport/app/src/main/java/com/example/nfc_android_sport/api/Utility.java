package com.example.nfc_android_sport.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utility {

    public static String get(URL url) throws Exception {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            bufferedReader.close();


            return response.toString();

        }
        catch (IOException e) {
            e.printStackTrace();
            throw new Exception("no object returned");
        }

    }
}