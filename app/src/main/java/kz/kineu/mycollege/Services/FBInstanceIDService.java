package kz.kineu.mycollege.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by ruslan on 04.02.2017.
 */

public class FBInstanceIDService extends FirebaseInstanceIdService{

    private static final String LOG_TAG = "MyCollegeApp";

    @Override
    public void onTokenRefresh() {
        Log.d(LOG_TAG,"TOKEN REFRESHED "+ FirebaseInstanceId.getInstance().getToken());
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/notifications");
    }
}
