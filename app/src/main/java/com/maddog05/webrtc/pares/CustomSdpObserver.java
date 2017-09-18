package com.maddog05.webrtc.pares;

/**
 * Created by andree on 15/09/2017.
 */

import android.util.Log;

import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

/**
 * Webrtc_Step2
 * Created by vivek-3102 on 11/03/17.
 */

public class CustomSdpObserver implements SdpObserver {


    private String tag = "#" + this.getClass().getCanonicalName();

    public CustomSdpObserver(String logTag) {
        this.tag = this.tag + " " + logTag;
    }


    @Override
    public void onCreateSuccess(SessionDescription sessionDescription) {
        Log.d(tag, "onCreateSuccess() called with: sessionDescription = [" + sessionDescription + "]");
    }

    @Override
    public void onSetSuccess() {
        Log.d(tag, "onSetSuccess() called");
    }

    @Override
    public void onCreateFailure(String s) {
        Log.d(tag, "onCreateFailure() called with: s = [" + s + "]");
    }

    @Override
    public void onSetFailure(String s) {
        Log.d(tag, "onSetFailure() called with: s = [" + s + "]");
    }

}
