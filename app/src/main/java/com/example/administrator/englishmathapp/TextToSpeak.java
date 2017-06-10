package com.example.administrator.englishmathapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;


/**
 * Created by Administrator on 2017-06-06.
 */

public class TextToSpeak extends AsyncTask<String, Void, String> {
    //private String accessToken;
    //private Date lastTimeRetrieved = Calendar.getInstance().getTime();

    public TextToSpeak(){

    }

    public void sayThis(String textToSay){
        String serviceURL = "https://api.microsofttranslator.com/v2/http.svc/Speak";
        String token = AuthToken.getToken();
        serviceURL = serviceURL+"?appid=Bearer "+token;
        serviceURL = serviceURL+"&language=en";
        serviceURL = serviceURL+"&text="+textToSay;

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        try
        {
            mediaPlayer.setDataSource(serviceURL);
            mediaPlayer.prepare();
            mediaPlayer.start();
            int df = 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    @Override
    protected String doInBackground(String... params) {
        sayThis(params[0]);
        return "Done";
    }
}
