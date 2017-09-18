package com.maddog05.webrtc.util;

import android.content.Context;
import android.util.Log;

import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;

/**
 * Created by andree on 18/09/2017.
 */

public class VideoCapturerMaker {

    private static final String TAG = "#Maker";

    public static VideoCapturer make(Context context, CameraVideoCapturer.CameraEventsHandler handler) {
        VideoCapturer videoCapturer;
        if (isCamera2Available(context)) {
            videoCapturer = _make(new Camera2Enumerator(context), handler);
        } else {
            videoCapturer = _make(new Camera1Enumerator(false), handler);
        }
        return videoCapturer;
    }

    private static VideoCapturer _make(CameraEnumerator cameraEnumerator, CameraVideoCapturer.CameraEventsHandler handler) {
        final String[] deviceNames = cameraEnumerator.getDeviceNames();
        // First, try to find front facing camera
        Log.d(TAG, "Looking for front facing cameras.");
        for (String deviceName : deviceNames) {
            if (cameraEnumerator.isFrontFacing(deviceName)) {
                Log.d(TAG, "Creating front facing camera capturer.");
                VideoCapturer videoCapturer = cameraEnumerator.createCapturer(deviceName, handler);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        // Front facing camera not found, try something else
        Log.d(TAG, "Looking for other cameras.");
        for (String deviceName : deviceNames) {
            if (!cameraEnumerator.isFrontFacing(deviceName)) {
                Log.d(TAG, "Creating other camera capturer.");
                VideoCapturer videoCapturer = cameraEnumerator.createCapturer(deviceName, handler);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }
        return null;
    }

    private static boolean isCamera2Available(Context context) {
        return Camera2Enumerator.isSupported(context);
    }
}
