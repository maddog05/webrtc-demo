package com.maddog05.webrtc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maddog05.webrtc.util.VideoCapturerMaker;

import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.EglBase;
import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize PeerConnectionFactory globals.
        //Params are context, initAudio,initVideo and videoCodecHwAcceleration
        //deprecado
        //PeerConnectionFactory.initializeAndroidGlobals(this, true, true, true);
        //nuevo
        PeerConnectionFactory.initializeAndroidGlobals(MainActivity.this, true);

        //Create a new PeerConnectionFactory instance.
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        PeerConnectionFactory peerConnectionFactory = new PeerConnectionFactory(options);

        //Now create a VideoCapturer instance. Callback methods are there if you want to do something! Duh!
        VideoCapturer videoCapturerAndroid = VideoCapturerMaker.make(MainActivity.this, null);
        //Create MediaConstraints - Will be useful for specifying video and audio constraints. More on this later!
        MediaConstraints constraints = new MediaConstraints();

        //Create a VideoSource instance
        VideoSource videoSource = peerConnectionFactory.createVideoSource(videoCapturerAndroid);
        VideoTrack localVideoTrack = peerConnectionFactory.createVideoTrack("100", videoSource);

        //create an AudioSource instance
        AudioSource audioSource = peerConnectionFactory.createAudioSource(constraints);
        AudioTrack localAudioTrack = peerConnectionFactory.createAudioTrack("101", audioSource);

        //we will start capturing the video from the camera
        //width,height and fps
        videoCapturerAndroid.startCapture(1000, 1000, 30);

        //create surface renderer, init it and add the renderer to the track
        SurfaceViewRenderer videoView = (SurfaceViewRenderer) findViewById(R.id.surface_rendeer);
        //a small method to provide a mirror effect to the SurfaceViewRenderer
        videoView.setMirror(true);

        //create an EglBase instance
        EglBase rootEglBase = EglBase.create();
        //init the SurfaceViewRenderer using the eglContext
        videoView.init(rootEglBase.getEglBaseContext(), null);

        //Add the renderer to the video track
        localVideoTrack.addRenderer(new VideoRenderer(videoView));
    }
}
