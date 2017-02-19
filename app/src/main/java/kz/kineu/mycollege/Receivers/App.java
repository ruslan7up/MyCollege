package kz.kineu.mycollege.Receivers;

import android.app.Application;

/**
 * Created by ruslan on 19.02.2017.
 */

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkStateReciever.ConnectivityReceiverListener listener) {
        NetworkStateReciever.connectivityReceiverListener = listener;
    }
}
