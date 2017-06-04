package com.example.administrator.practicemobileapp;

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
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int rangeForProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainScreen();
    }

    private void mainScreen() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_bar);

        setSupportActionBar(toolbar);

        SeekBar problemRange = (SeekBar) findViewById(R.id.problemRange);
        rangeForProblems  = 20;

        problemRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView currentRange = (TextView) findViewById(R.id.currentRange);
                rangeForProblems = progress;
                String strRange = "" + progress;
                currentRange.setText(strRange);

                Button showButton = (Button) findViewById(R.id.button);
                showButton.setText("Solve Problem");
                showButton.setOnClickListener(new newProblemClickListener(rangeForProblems));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button showButton = (Button) findViewById(R.id.button);
        showButton.setText("Solve Problem");
        showButton.setOnClickListener(new newProblemClickListener(rangeForProblems));
    }


    public void solveAProblem(int range){
        setContentView(R.layout.solve_problem);
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainScreen();
            }
        });


        final String[] problem = MathProblemGenerator.getProblem(range);
        TextView first = (TextView) findViewById(R.id.math_firstvalue);
        TextView second = (TextView) findViewById(R.id.math_secondvalue);
        TextView symbol = (TextView) findViewById(R.id.math_symbol);

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
                    solveAProblem(rangeForProblems);
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
//        diag.setNegativeButton(R.string.popNo, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
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

    //******************
    //
    //    Listener fo onclick items that generate a new problem
    //
    //    TODO: Remove l8r
    //******************
    private class newProblemClickListener implements View.OnClickListener {
        int range;

        private newProblemClickListener(int range){
            super();
            this.range = range;
        }

        public void onClick(View v) {
            solveAProblem(range);
        }
    }
}


