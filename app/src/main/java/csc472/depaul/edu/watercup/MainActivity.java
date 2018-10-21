package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView sugarCup;
    private ImageView waterCup;
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private ProgressBar progressBar;
    private TextView dialogString;
    private int currentConsumption = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize cups
        sugarCup = (ImageView) findViewById(R.id.sugarCupId);
        waterCup = (ImageView) findViewById(R.id.waterCupId);
        alarmIc = (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        progressBar = findViewById(R.id.progressBar);
        dialogString = findViewById(R.id.DialogString);

        sugarCup.setOnClickListener(this);
        waterCup.setOnClickListener(this);
        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);

        setProgressBar();
        setDialogString();
    }

    private void setProgressBar() {
        //get the values for weight and age from sharedprefs
       SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
       int totalWater = pref.getInt("baseWater", 0);
       progressBar.setMax(totalWater);
    }

    private void setDialogString () {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int totalWater = pref.getInt("baseWater", 0);
        if (currentConsumption < totalWater ) {
            dialogString.setText("Keep it up! You've only drank " + currentConsumption + "/" + totalWater + " fl. oz");
        } else {
            dialogString.setText("You've reached your daily fluid intake! Good Job!");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sugarCupId:
                Toast.makeText(this, "Sugar Cup", Toast.LENGTH_SHORT).show();

                break;
            case R.id.waterCupId:
                Toast.makeText(this, "WaterCup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.alarmIcon:
                startActivity(new Intent(MainActivity.this, Alarms.class));
                break;
            case R.id.settingsIcon:
                startActivity(new Intent(MainActivity.this, Settings.class));
                break;
            case R.id.statsIcon:
                startActivity(new Intent(MainActivity.this, Stats.class));
                break;

        }
    }
}
