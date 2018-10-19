package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView sugarCup;
    private ImageView waterCup;
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;


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

        sugarCup.setOnClickListener(this);
        waterCup.setOnClickListener(this);
        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);

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
