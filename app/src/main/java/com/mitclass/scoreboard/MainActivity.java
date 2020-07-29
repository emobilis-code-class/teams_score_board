package com.mitclass.scoreboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText edtNameTeamA,edtNameTeamB;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view binding
        edtNameTeamA = findViewById(R.id.edt_teamA);
        edtNameTeamB = findViewById(R.id.edt_teamB);

        preferences = getApplicationContext().getSharedPreferences("score_pref",MODE_PRIVATE);

        String teamA = preferences.getString("teamA",null);
        if (teamA!=null){
            startActivity(new Intent(MainActivity.this,ScoresActivity.class));
            finish();
        }

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if name A and B
                //
                if (edtNameTeamA.getText().toString().isEmpty()){
                    edtNameTeamA.setError("Kindly Provide name of Team A");
                }else {
                    if (edtNameTeamB.getText().toString().isEmpty()){
                        edtNameTeamB.setError("Kindly Provide name of Team B");
                    }else{
                        saveTeams(edtNameTeamA.getText().toString(),edtNameTeamB.getText().toString());
                    }
                }
            }
        });
    }

    private void saveTeams(String teamA,String teamB){
        //save these teams A, initialiaze
        //instantiate the sharedpreference
      //   preferences = getApplicationContext().getSharedPreferences("score_pref",MODE_PRIVATE);
        //instance
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("teamA",teamA);
        editor.putString("teamB",teamB);
        //initiliazes scores values
        editor.putString("teamAScore","0");
        editor.putString("teamBScore","0");
        //commit
        editor.commit();

        //finish this activity
        finish();
        startActivity(new Intent(MainActivity.this,ScoresActivity.class));

    }

    /*
    * First We have an Activity - Someone will create teams - 2teams Names - teamA,teamB
    * - teamAScore = 0
    * - teamBScore = 0
    * -ScoreBoard - Activity - we would recording
    * -Summary - We have all Teams Scores ofcourse the winner
    * Alert
    *
    * -
    *
    * */
}
