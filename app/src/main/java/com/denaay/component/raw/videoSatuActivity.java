package com.denaay.component.raw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.denaay.R;

public class videoSatuActivity extends AppCompatActivity {

    private VideoView mVideo;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_satu);

        mVideo = (VideoView) findViewById(R.id.video1);
        mController = new MediaController(videoSatuActivity.this);
        mVideo.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video1);
        mController.setAnchorView(mVideo);
        mVideo.setMediaController(mController);
        mVideo.start();

    }
}
