package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private AdView mAdview;
    private Button mButton;
    private MainActivity mMainActivity;
    InterstitialAd mInterstititalAd;
    View mRoot;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_main, container, false);

        mAdview = (AdView) mRoot.findViewById(R.id.adView);
        mButton = (Button) mRoot.findViewById(R.id.main_button);
        mMainActivity = (MainActivity) getActivity();
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdview.loadAd(adRequest);

        //Add interstitial ad
        mInterstititalAd = new InterstitialAd(getContext());
        mInterstititalAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstititalAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                mMainActivity.tellJoke(mButton);
            }
        });
        requestNewInterstitial();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstititalAd.isLoaded()) {
                    mInterstititalAd.show();
                } else {
                    mMainActivity.tellJoke(mButton);
                }
            }
        });
        return mRoot;
    }

    private void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("TEST_EMULATOR")
                .build();
        mInterstititalAd.loadAd(adRequest);
    }
}
