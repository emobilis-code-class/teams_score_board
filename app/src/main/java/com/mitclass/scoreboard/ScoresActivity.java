package com.mitclass.scoreboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScoresActivity extends AppCompatActivity {

    TextView txtTeamAName,txtTeamBName,txtScoreA,txtScoreB;
    ImageButton imgAddScoreA,imgAddScoreB;
    Button btnGameOver;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        preferences = getApplicationContext().getSharedPreferences("score_pref",MODE_PRIVATE);

        txtTeamAName = findViewById(R.id.txtTeamAName);
        txtTeamBName = findViewById(R.id.txtTeamBName);
        txtScoreA = findViewById(R.id.txtTeamAScore);
        txtScoreB = findViewById(R.id.txtTeamBScore);

        imgAddScoreA = findViewById(R.id.imgBtnAddScoreA);
        imgAddScoreB = findViewById(R.id.imgBtnAddScoreB);

        imgAddScoreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we shall increament score value by one the update on shared prefernce
                //get value of score
                //increament by 1
                //save the result
                //String key ="teamAScore";
                saveScore("teamAScore");

            }
        });

        imgAddScoreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key ="teamBScore";
                saveScore(key);
            }
        });

        //get team A
        txtTeamAName.setText(preferences.getString("teamA",null));
        //get team B
        txtTeamBName.setText(preferences.getString("teamB",null));

        //get scores
        txtScoreA.setText(preferences.getString("teamAScore","0"));
        txtScoreB.setText(preferences.getString("teamBScore","0"));

        //get the teams scores and the names prefences
    }

    private void saveScore(String key){
        String currentScore  = preferences.getString(key,"0");
        int result = Integer.parseInt(currentScore)+1;
        //save the result
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,""+result);
        editor.commit();

        //
        Toast.makeText(this,"Score added to "+key,Toast.LENGTH_SHORT).show();
        //update the user interface
        if (key.contains("teamAScore")){
            txtScoreA.setText(""+result);
        }else {
            txtScoreB.setText(String.valueOf(result));
        }
    }
}
