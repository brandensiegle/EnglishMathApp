package com.example.administrator.practicemobileapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-02.
 */
public class changeText extends AppCompatActivity {

    TextView tView;

    public changeText(){

    }

    public void changeTo(TextView tView, String text){

        int i = 0;

        this.tView = tView;
        tView.setText(text);
    }

}
