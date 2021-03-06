package com.krnblni.evetech.glaufirewallauthenticator.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.krnblni.evetech.glaufirewallauthenticator.R;
import com.krnblni.evetech.glaufirewallauthenticator.fragments.AboutFragment;
import com.krnblni.evetech.glaufirewallauthenticator.fragments.DashboardFragment;
import com.krnblni.evetech.glaufirewallauthenticator.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Logging - MainActivity";

    FrameLayout navigationPageContainer;
    Toolbar toolbar;
    TextView pageHeadingTextView;
    AdView bannerAdView;

    AboutFragment aboutFragment = new AboutFragment();
    DashboardFragment dashboardFragment = new DashboardFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_about:
                    pageHeadingTextView.setText(R.string.about);
                    return inflateRespectedFragmentPage(aboutFragment);
                case R.id.navigation_dashboard:
                    pageHeadingTextView.setText(R.string.dashboard);
                    return inflateRespectedFragmentPage(dashboardFragment);
                case R.id.navigation_settings:
                    pageHeadingTextView.setText(R.string.settings);
                    return inflateRespectedFragmentPage(settingsFragment);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeMobileAdsLoadAndShow();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pageHeadingTextView = findViewById(R.id.pageHeadingTextView);
        navigationPageContainer = findViewById(R.id.container);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        pageHeadingTextView.setText(R.string.dashboard);
        inflateRespectedFragmentPage(dashboardFragment);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initializeMobileAdsLoadAndShow() {
        Log.e(TAG, "initializeMobileAdsLoadAndShow: " + "initiating banner ad");
        bannerAdView = findViewById(R.id.bannerAdView);
        AdRequest bannerAdRequest = new AdRequest.Builder().build();
        bannerAdView.loadAd(bannerAdRequest);

        bannerAdView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "onAdLoaded: " + "banner ad loaded" );
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e(TAG, "onAdFailedToLoad: " + "banner ad failed to load" + loadAdError.toString() );
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e(TAG, "onAdOpened: " + "banner ad opened" );
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.e(TAG, "onAdClicked: " + "banner ad clicked" );
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e(TAG, "onAdClosed: " + "banner ad closed" );
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.e(TAG, "onAdImpression: " + "banner ad impression made" );
            }
        });

        Log.e(TAG, "initializeMobileAdsLoadAndShow: " + "the ad was loaded");
    }

    boolean inflateRespectedFragmentPage(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        return true;
    }
}
