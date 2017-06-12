package com.example.administrator.englishmathapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MathProblemActivity extends AppCompatActivity {
    int rangeForProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        rangeForProblems = intent.getIntExtra(MainActivity.RANGEMESSAGE,2);
        setContentView(R.layout.math_problem_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createDisplay(rangeForProblems);



    }

    private void createDisplay(int range){
        //setContentView(R.layout.solve_problem);



        final String[] problem = MathProblemGenerator.getProblem(range);
        TextView first = (TextView) findViewById(R.id.math_firstvalue);
        TextView second = (TextView) findViewById(R.id.math_secondvalue);
        TextView symbol = (TextView) findViewById(R.id.math_symbol);

        first.setText(problem[0]);
        second.setText(problem[1]);
        symbol.setText(problem[2]);


        //set listeners for the text to speak on click
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tV = (TextView) v;
                TextToSpeak textToSpeak = new TextToSpeak();
                textToSpeak.execute(tV.getText().toString());
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tV = (TextView) v;
                TextToSpeak textToSpeak = new TextToSpeak();
                textToSpeak.execute(tV.getText().toString());
            }
        });


        //set listener for when te enter key is pressed
        EditText answerBox = (EditText) findViewById(R.id.math_answer);
        answerBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    EditText answerBox = (EditText) v;
                    checkAnswer(answerBox.getText().toString(),problem[3]);
                }
                return false;
            }
        });

        //set listener for when the check answer button is pressed
        FloatingActionButton answerCheckbutton = (FloatingActionButton) findViewById(R.id.checkAnswer);
        answerCheckbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText answerBox = (EditText) findViewById(R.id.math_answer);
                checkAnswer(answerBox.getText().toString(),problem[3]);
            }
        });
    }

    private void checkAnswer(String a, String b) {
        if(a.equalsIgnoreCase(b)){
            showAlert("Correct!");
            createDisplay(rangeForProblems);
        } else {
            showAlert("Try again.");
        }
    }

    public void showAlert(String string){
        AlertDialog.Builder diag = new AlertDialog.Builder(this);
        //diag.setTitle(R.string.popTitle);
        if (string==null) {
            diag.setMessage(R.string.popMessage);
        } else {
            diag.setMessage(string);
        }
        diag.setPositiveButton(R.string.popYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diag.show();
    }

}
