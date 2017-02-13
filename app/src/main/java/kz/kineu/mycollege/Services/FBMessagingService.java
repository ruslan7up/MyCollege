package kz.kineu.mycollege.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import kz.kineu.mycollege.R;

/**
 * Created by ruslan on 04.02.2017.
 */

public class FBMessagingService extends FirebaseMessagingService {

    private static final String LOG_TAG = "MyCollegeApp";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage!=null) {
            if(remoteMessage.getData().size()>0) {
                Log.d(LOG_TAG,"FIREBASE MESSAGE RECIEVED "+remoteMessage.getData().get("message"));

                Intent intent = new Intent(this, FragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("fragment","notifications");

                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification2).setContentTitle("Мой колледж").setContentText(remoteMessage.getData().get("message")).setContentIntent(pendingIntent).setAutoCancel(true);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                manager.notify(0, builder.build());
            } else {
                Log.d(LOG_TAG,"FIREBASE EMPTY MESSAGE RECIEVED");
            }
        }
    }
}

