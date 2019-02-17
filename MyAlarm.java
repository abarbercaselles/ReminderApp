package estudios.com.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import static estudios.com.myapplication.Notification.CHANNEL_1_ID;


/**
 * Created by Belal on 8/29/2017.
 */

//class extending the Broadcast Receiver
public class MyAlarm extends BroadcastReceiver {

    public int code,code1;
    public String reminder;
    public DatabaseHelper myDb;
    private NotificationManagerCompat notificationManager;


    @Override
    public void onReceive(Context context, Intent intent) {

        myDb = new DatabaseHelper(context);
        notificationManager = NotificationManagerCompat.from(context);

        code = intent.getIntExtra("id",0);

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
        // showNotification(context);


       // PendingIntent contentIntent = PendingIntent.getBroadcast(context, code,
               // intent, 0);


        Cursor res = myDb.getAllData(code);
        if (res.getCount() == 0) {

            return;
        }

        while (res.moveToNext()) {
            reminder = res.getString(6);
        }


            Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("Do not forget !")
                    .setContentText(reminder)
                    //.setContentIntent(MainActivity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            notificationManager.notify(1, notification);



    }
}