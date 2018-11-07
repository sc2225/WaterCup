package csc472.depaul.edu.watercup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        Log.v("state:", "OnCreate");

        alarmIc =  findViewById(R.id.alarmIcon);
        settingsIc =  findViewById(R.id.settingsIcon);
        statsIc = findViewById(R.id.statsIcon);
        homeIc = findViewById(R.id.homeIcon);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        homeIc.setOnClickListener(this);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

       //set a flag for alarm registration. if register once, do not register again!
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

//        boolean flag = pref.getBoolean("alarmFlag", false);
//        if (flag == false) {
//            editor.putBoolean("alarmFlag", true);
//            editor.apply();
//            createChannel(notificationManager);
//            setAlarm();
//        }

        createChannel(notificationManager);
        setAlarm();

    }

    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 06);
        calendar.set(Calendar.MINUTE, 11);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(Alarms.this, AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarms.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) Alarms.this.getSystemService(Alarms.this.ALARM_SERVICE);

        //am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        //TODO: for testing, please remove for final deliiverable and use the commented one above
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

    }

    private void createChannel(NotificationManager notificationManager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = "Channel for notification system";
            CharSequence name = "notification";
            NotificationChannel channel = new NotificationChannel("notification", name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }
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
