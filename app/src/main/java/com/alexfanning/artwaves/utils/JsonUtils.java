package com.alexfanning.artwaves.utils;

import android.util.Log;

import com.alexfanning.artwaves.venueitems.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by alex.fanning on 24/07/2017.
 */

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private static final String URL_STR = "https://artwaves.000webhostapp.com/arttrail-date.txt";
    private static final String EMPTY_STRING = "";
    private static final String VENUES_KEY = "venues";
    private static final String NAME_KEY = "name";
    private static final String LOCATION_KEY = "location";
    private static final String DATE_KEY = "date";
    private static final String DESCRIPTION_KEY = "description";
    private static final String COORDINATES_KEY = "coordinates";
    private static final String SCANNER_DELIMETTER = "\\A";
    private static final String COORDINATE_SPLITTER = "\\s*,\\s*";

    private static Venue[] vens;

    public static Venue[] getVens(){
        String json = EMPTY_STRING;
        try{
            String a = getJsonResponse();
            json = getJsonResponse();
        }catch (Exception e) {
            return null;
        }
        Venue[] vens =  getVenuesFromJson(json);
        return vens;
    }

    private static String getJsonResponse() throws IOException{
       URL url = null;
        try{
            url = new URL(URL_STR);
        }catch (Exception e) {
            return null;
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter(SCANNER_DELIMETTER);

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();

        }
    }

    private static Venue[] getVenuesFromJson(String jsonStr ){
        Venue[] vens = null;
        JSONObject jsonObj = null;
        try{
            jsonObj = new JSONObject(jsonStr);
            JSONArray venueJsonArray = jsonObj.getJSONArray(VENUES_KEY);
            int numVens = venueJsonArray.length();
            vens = new Venue[numVens];

            for (int i = 0; i < numVens; i++){
                JSONObject venue = venueJsonArray.getJSONObject(i);
                String name = venue.getString(NAME_KEY);
                String location = venue.getString(LOCATION_KEY);
                String date = venue.getString(DATE_KEY);
                String description = venue.getString(DESCRIPTION_KEY);
                String coordinatesStr = venue.getString(COORDINATES_KEY);
                String[] coordinates = coordinatesStr.split(COORDINATE_SPLITTER);
                Venue v = new Venue(name,location,date,description,coordinates);
                vens[i] = v;
            }
            return vens;
        }catch(Exception e) {
            return null;
        }

    }
}
