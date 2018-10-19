package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alarmIc= (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alarmIcon:
                startActivity(new Intent(Settings.this, Alarms.class));
                break;
            case R.id.settingsIcon:
                break;
            case R.id.statsIcon:
                startActivity(new Intent(Settings.this, Stats.class));
                break;

        }
    }
}
