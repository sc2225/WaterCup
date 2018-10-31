package csc472.depaul.edu.watercup;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class Fetch extends AsyncTask<Void, Void, String> {

    private String city = "London";


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s==null) {
            s = "there was an error with the request";
        }

    }

    @Override
    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=412e1164d49908e6ecaa1b64520addf4");


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                reader.close();

                System.out.println(stringBuilder.toString());

                return stringBuilder.toString();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
            return null;
        }
    }
}


