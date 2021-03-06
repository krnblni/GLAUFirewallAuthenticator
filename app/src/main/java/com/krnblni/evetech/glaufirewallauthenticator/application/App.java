package com.krnblni.evetech.glaufirewallauthenticator.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.krnblni.evetech.glaufirewallauthenticator.R;

public class App extends Application {

    String notificationChannelIdForHelperService = "1000";
    String notificationChannelIdForAdsAndService = "2000";
    String notificationChannelNameForHelperService = "Helper Foreground Service Channel";
    String notificationChannelNameForAdsAndService = "Support Service Channel";

    final String TAG = "Logging - App";

    @Override
    public void onCreate() {
        createNotificationChannels();
        initializeMobileSdk();
        super.onCreate();
    }

    private void initializeMobileSdk() {
//        MobileAds.initialize(getApplicationContext(), getApplicationContext().getString(R.string.admob_app_id));
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.e(TAG, "onInitializationComplete: " + initializationStatus.toString());
            }
        });
    }

    private void createNotificationChannels() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannelForHelperService = new NotificationChannel(
                    notificationChannelIdForHelperService,
                    notificationChannelNameForHelperService,
                    NotificationManager.IMPORTANCE_MIN
            );

            NotificationChannel notificationChannelForAdsAndService = new NotificationChannel(
                    notificationChannelIdForAdsAndService,
                    notificationChannelNameForAdsAndService,
                    NotificationManager.IMPORTANCE_LOW
            );


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannelForHelperService);
                notificationManager.createNotificationChannel(notificationChannelForAdsAndService);
            }
        }
    }

}
