package csc472.depaul.edu.watercup;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import im.delight.android.location.SimpleLocation;

import static android.content.Context.MODE_PRIVATE;

public class Fetch extends AsyncTask<Void, Void, Void> {
    private String city = "London";
    private MainActivity activity;

    public Fetch(MainActivity act) {

        activity = new MainActivity();

        activity = act;
    }


    @Override
    protected Void doInBackground(Void... urls) {
        try {

            SimpleLocation location = new SimpleLocation(activity);

            //checks if location is enabled. if not prompts user to enable it
            if(!location.hasLocationEnabled()) {
                Toast toast = Toast.makeText(activity, "Location not enabled. Please enable location to retrieve weather", Toast.LENGTH_SHORT);
                toast.show();

                location.openSettings(activity);

            }

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();


            location.endUpdates();

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&APPID=412e1164d49908e6ecaa1b64520addf4");


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line = "";


                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                reader.close();
                JSONObject obj = null;

                try {
                    obj = new JSONObject(stringBuilder.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JSONParser.Parser(obj);
                JSONParser.setActivity(activity);


                cancel(true);

                return null;


            } finally {
                if (connection != null) {
                    connection.disconnect();

                }
                cancel(true);

            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
            return null;
        }


    }

}


