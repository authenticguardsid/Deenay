package com.denaay.component.raw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.denaay.R;

public class videoTigaActivity extends AppCompatActivity {

    private VideoView mVideo;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tiga);

        mVideo = (VideoView) findViewById(R.id.video3);
        mController = new MediaController(videoTigaActivity.this);
        mVideo.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video3);
        mController.setAnchorView(mVideo);
        mVideo.setMediaController(mController);
        mVideo.start();
    }
}
