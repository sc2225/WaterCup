package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Alarms extends AppCompatActivity implements View.OnClickListener {
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        alarmIc = (ImageView) findViewById(R.id.alarmIcon);
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
                break;
            case R.id.settingsIcon:
                startActivity(new Intent(Alarms.this, Settings.class));
                break;
            case R.id.statsIcon:
                startActivity(new Intent(Alarms.this, Stats.class));
                break;

        }
    }
}
