package com.example.administrator.englishmathapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String RANGEMESSAGE= "com.example.administrator.englishmathapp.RANGE";
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

        //difficulty buttons
        Button easyButton = (Button) findViewById(R.id.easyButton);
        Button midButton = (Button) findViewById(R.id.midButton);
        Button hardButton = (Button) findViewById(R.id.hardButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveAProblem( Integer.parseInt( ((Button) v).getText().toString()) );
            }
        });
        midButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveAProblem( Integer.parseInt( ((Button) v).getText().toString()) );
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveAProblem( Integer.parseInt( ((Button) v).getText().toString()) );
            }
        });

        setSupportActionBar(toolbar);



        Button showButton = (Button) findViewById(R.id.button);
        showButton.setText("Solve Problem");
        showButton.setOnClickListener(new newProblemClickListener(90));
    }


    public void solveAProblem(int range){
        Intent intendToSolveProblem = new Intent(this, MathProblemActivity.class);
        intendToSolveProblem.putExtra(RANGEMESSAGE, range);
        startActivity(intendToSolveProblem);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        mainScreen();
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


