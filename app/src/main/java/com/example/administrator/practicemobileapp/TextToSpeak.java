package com.example.administrator.practicemobileapp;

import android.os.AsyncTask;

import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;




/**
 * Created by Administrator on 2017-06-06.
 */

public class TextToSpeak extends AsyncTask<String, Void, String> {
    //private String accessToken;
    //private Date lastTimeRetrieved = Calendar.getInstance().getTime();

    public TextToSpeak(){

    }

    public void sayThis(String textToSay){
        String serviceURL = "https://api.microsofttranslator.com/V2/Http.svc/GetLanguagesForSpeak";
        //String serviceURL = "Https://google.com";
        String token = getToken();


        try
        {
            String charset = StandardCharsets.UTF_8.name();
            URL url = new URL(serviceURL);

            HttpsURLConnection  connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("appid", "Bearer" + " " + token);
            connection.setDoOutput(true);
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.close();

            int responseCode = connection.getResponseCode();

            while (responseCode == 307){
                connection.disconnect();
                String loc = connection.getHeaderField("Location");
                url = new URL(loc);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("appid", "Bearer" + " " + token);
                connection.setDoOutput(true);
                connection.connect();
                out = new OutputStreamWriter(connection.getOutputStream());
                out.close();
            }
            if ( responseCode == HttpURLConnection.HTTP_OK)
            {
                // OK
                int o = 0;
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset)))
                {
                    StringBuffer res = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        res.append(line);
                    }

                    int i = 0;
                }


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }





    }

    private String getToken(){
        String token = null;

        String prKey = "6b9b1c5af4a945afa19d9c0985710bc0";
        String serviceURL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

        try
        {
            String charset = StandardCharsets.UTF_8.name();
            URL url = new URL(serviceURL);

            HttpsURLConnection  connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", prKey);
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.close();

            int responseCode = connection.getResponseCode();
            if ( responseCode == HttpURLConnection.HTTP_OK)
            {
                // OK
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset)))
                {
                    StringBuffer res = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        res.append(line);
                    }

                    token = res.toString();
                }


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return token;
    }

    @Override
    protected String doInBackground(String... params) {
        sayThis(params[0]);
        return "Done";
    }
}
