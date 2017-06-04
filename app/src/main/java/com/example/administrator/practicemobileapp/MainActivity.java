package com.example.administrator.practicemobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_bar);

        setSupportActionBar(toolbar);

        Button showButton = (Button) findViewById(R.id.button);
        showButton.setText("Solve Problem");
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveAProblem();
            }
        });



    }

    public void returnToMainScreen(){
        setContentView(R.layout.activity_main);
        Button showButton = (Button) findViewById(R.id.button);
        showButton.setText("Solve Problem");
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveAProblem();
            }
        });
    }

    public void solveAProblem(){
        setContentView(R.layout.solve_problem);
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainScreen();
            }
        });

        final String[] problem = MathProblemGenerator.getProblem();
        TextView first = (TextView) findViewById(R.id.math_firstvalue);
        TextView second = (TextView) findViewById(R.id.math_secondvalue);
        TextView symbol = (TextView) findViewById(R.id.math_symbol);
        TextView answer = (TextView) findViewById(R.id.math_answer);

        first.setText(problem[0]);
        second.setText(problem[1]);
        symbol.setText(problem[2]);

        FloatingActionButton answerCheckbutton = (FloatingActionButton) findViewById(R.id.checkAnswer);
        answerCheckbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText answerBox = (EditText) findViewById(R.id.math_answer);
                if(answerBox.getText().toString().equalsIgnoreCase(problem[3])){
                    showAlert("Correct!");
                    solveAProblem();
                } else {
                    showAlert(answerBox.getText().toString().toLowerCase());
                }
            }
        });

    }

    public void showAlert(String string){
        AlertDialog.Builder diag = new AlertDialog.Builder(this);
        diag.setTitle(R.string.popTitle);
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
        diag.setNegativeButton(R.string.popNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        diag.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.close){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}
