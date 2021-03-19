package com.example.nfc_android_sport.api;

import com.example.nfc_android_sport.model.Client;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Utility {


    public static final String BASE_URL = "http://1229a3c2051c.ngrok.io/api/";

    public enum FRAGMENT {
        WRITE_CARD,
        MAIN,
        ADD_CLIENT
    }

    public enum ACTION {
        ACCES_CLUB,
        BOISSON,
        COURS_COLLECTIFS
    }

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


    public static void post(String url1, Client client) throws IOException {

        /*Zakaria Gasmi*/
        Gson gson = new Gson();
        URL url = new URL(url1);
        String jsonInputString= gson.toJson(client);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

    }




}

