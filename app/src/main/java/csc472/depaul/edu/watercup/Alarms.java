package csc472.depaul.edu.watercup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Alarms extends AppCompatActivity implements View.OnClickListener {
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private ImageView homeIc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        alarmIc = (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        homeIc = findViewById(R.id.homeIcon);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        homeIc.setOnClickListener(this);

//
//        // get a Calendar object with current time
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 57);
//        cal.set(Calendar.SECOND, 0);
//        Intent intent = new Intent(Alarms.this, AlarmReciever.class);
//        intent.putExtra("alarm_message", "O'Doyle Rules!");
//// In reality, you would want to have a static variable for the request code instead of 192837
//        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//// Get the AlarmManager service
//        AlarmManager am = (AlarmManager) Alarms.this.getSystemService(ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);

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
            case R.id.homeIcon:
                startActivity(new Intent(Alarms.this, MainActivity.class));
                break;

        }
    }

}
