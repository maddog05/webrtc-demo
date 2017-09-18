package com.maddog05.webrtc.pares;

/**
 * Created by andree on 15/09/2017.
 */

import android.util.Log;

import org.webrtc.CameraVideoCapturer;

/**
 * Webrtc_Step2
 * Created by vivek-3102 on 11/03/17.
 */

public class CustomCameraEventsHandler implements CameraVideoCapturer.CameraEventsHandler {

    private String TAG = "#CameraHandler";


    @Override
    public void onCameraError(String s) {
        Log.d(TAG, "onCameraError() called with: s = [" + s + "]");
    }

    //nuevo
    @Override
    public void onCameraDisconnected() {

    }

    @Override
    public void onCameraFreezed(String s) {
        Log.d(TAG, "onCameraFreezed() called with: s = [" + s + "]");
    }

    //nuevo
    @Override
    public void onCameraOpening(String s) {
        Log.d(TAG, "onCameraOpening() called with: i = [" + s + "]");
    }

    //borrado
    /*@Override
    public void onCameraOpening(int i) {
        Log.d(TAG, "onCameraOpening() called with: i = [" + i + "]");
    }*/

    @Override
    public void onFirstFrameAvailable() {
        Log.d(TAG, "onFirstFrameAvailable() called");
    }

    @Override
    public void onCameraClosed() {
        Log.d(TAG, "onCameraClosed() called");
    }
}
