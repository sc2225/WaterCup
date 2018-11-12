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
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class Alarms extends AppCompatActivity implements View.OnClickListener {
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private ImageView homeIc;
    private Switch switchButton;
    private RadioButton rb;
    private PendingIntent pendingIntent;
    private AlarmManager am;
    private  RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);
        Log.v("state:", "OnCreate");

        Intent intent1 = new Intent(Alarms.this, AlarmReciever.class);
        pendingIntent = PendingIntent.getBroadcast(Alarms.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        am = (AlarmManager) Alarms.this.getSystemService(Alarms.this.ALARM_SERVICE);

        alarmIc =  findViewById(R.id.alarmIcon);
        settingsIc =  findViewById(R.id.settingsIcon);
        statsIc = findViewById(R.id.statsIcon);
        homeIc = findViewById(R.id.homeIcon);
        switchButton = findViewById(R.id.switchButton);
        rb = findViewById(R.id.radio_min);
        rg = findViewById(R.id.radio_group);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        homeIc.setOnClickListener(this);


        rg.setEnabled(false);
        switchButton.setChecked(false);



        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("YES HELLO");
                if (isChecked) {
                    //Show that alarm is set to once per day default
                    rb.setChecked(true);
                    setAlarm("min");
                    rb.setEnabled(true);


                } else if (!isChecked) {

                    if (am != null) {
                        am.cancel(pendingIntent);
                    }

                    rg.setEnabled(false);
                }

            }
        });



        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        createChannel(notificationManager);


    }

    private void setAlarm(String interval) {

        //cancel any previous alarms
        if (am != null) {
            am.cancel(pendingIntent);
        }

        switch(interval) {
            case "perday":
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_DAY, pendingIntent);
                break;
            case "halfhour":
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                break;
            case "everyother":
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_DAY * 2, pendingIntent);
                break;
            case "min":
                am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),1000 * 60, pendingIntent);
                break;
        }

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


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();



    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_per_day:
                if (checked)
                    setAlarm("perday");
                    break;
            case R.id.radio_every_other:
                if (checked)
                    setAlarm("everyother");
                    break;
            case R.id.radio_half_hour:
                if (checked)
                    setAlarm("halfhour");
                    break;
            case R.id.radio_min:
                if(checked) {
                    setAlarm("min");
                    break;
                }
        }

    }

}
