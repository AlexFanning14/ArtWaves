package com.alexfanning.artwaves.utils;

import android.util.Log;

import com.alexfanning.artwaves.Venue;

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
    private static final String URL_STR = "https://jsonblob.com/api/jsonBlob/2b07e136-7084-11e7-9e0d-81ecfb10f882";
    private static final String EMPTY_STRING = "";
    private static final String VENUES_KEY = "venues";
    private static final String VENUE_KEY = "venue";
    private static final String NAME_KEY = "name";
    private static final String LOCATION_KEY = "location";
    private static final String DESCRIPTION_KEY = "description";
    private static final String COORDINATES_KEY = "coordinates";

    private static Venue[] vens;

    public static Venue[] getVens(){
        String json = EMPTY_STRING;
        try{
            json = getJsonResponse();
        }catch (IOException e){
            Log.e(TAG, "getVens: " + e.getMessage() );
        }catch (Exception e){
            Log.e(TAG, "getVens: " + e.getMessage() );
        }
        Venue[] vens =  getVenuesFromJson(json);
        return vens;
    }

    private static String getJsonResponse() throws IOException{
       URL url = null;
        try{
            url = new URL(URL_STR);
        }catch (MalformedURLException e){
            Log.e(TAG, "getJsonResponse: error " );
        }catch (Exception e){
            Log.e(TAG, "getJsonResponse: error " );
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

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
                String description = venue.getString(DESCRIPTION_KEY);
                String coordinates = venue.getString(COORDINATES_KEY);
                Venue v = new Venue(name,location,description,coordinates);
                vens[i] = v;
            }

        }catch(JSONException e){
            Log.e(TAG, "getVenuesFromJson: " + e.getMessage() );
        }catch(Exception e){
            Log.e(TAG, "getVenuesFromJson: " + e.getMessage() );
        }
        return null;
    }
}
