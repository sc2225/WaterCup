package csc472.depaul.edu.watercup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmReciever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent resultIntent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);

        Notification.Builder notification = new Notification.Builder(context, "notification")
                .setSmallIcon(R.drawable.clock)
                .setContentTitle("Water Reminder")
                .setContentText("Drink some water! Make sure you are keeping up with your daily fluid requirements!")
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

        if (Build.VERSION.SDK_INT < 16) {
            notificationManager.notify(1, notification.getNotification());
        } else {
            notificationManager.notify(1, notification.build());
        }
    }


}
