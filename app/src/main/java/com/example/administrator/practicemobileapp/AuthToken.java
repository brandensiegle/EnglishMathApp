package com.example.administrator.practicemobileapp;

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

public class AuthToken {

    protected static String getToken(){
        String token = null;

        String prKey = "6b9b1c5af4a945afa19d9c0985710bc0";
        String serviceURL = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";

        try
        {
            String charset = StandardCharsets.UTF_8.name();
            URL url = new URL(serviceURL);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
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

            connection.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return token;
    }
}
