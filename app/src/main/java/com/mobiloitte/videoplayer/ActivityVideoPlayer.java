package com.mobiloitte.videoplayer;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class ActivityVideoPlayer extends AppCompatActivity implements SurfaceHolder.Callback{
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;
    private int position = 0;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        Button buttonPlayVideo = ( Button ) findViewById( R.id.playvideoplayer );
        getWindow().setFormat( PixelFormat.UNKNOWN );
        final VideoView mVideoView = ( VideoView ) findViewById( R.id.videoview );



        if (mediaC == null) {
            mediaC = new MediaController(ActivityVideoPlayer.this);
            mediaC.setAnchorView(mVideoView);
            mVideoView.setMediaController(mediaC);
        }
        try {

            int id = this.getRawResIdByName("video");
            mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.seekTo( position );
                if (position == 0) {
                    mVideoView.start();
                }
                mediaPlayer.setOnVideoSizeChangedListener( new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaC.setAnchorView( mVideoView );
                    }

                } );
            }
        });




       /* Bundle bundle = getIntent().getExtras();*/
      /*  if (bundle != null) {
            mVideoView = bundle.getString(Const "android.resource://com.example.demopageview/" + R.raw.video;);
        }*/


      String uriPath = "android.resource://com.mobiloitte.videoplayer/" + R.raw.videoplayer;


       // String uriPath="https://youtu.be/o-1dMLJwOe0";
        Uri uri = Uri.parse( uriPath );
        mVideoView.setVideoURI( uri );
        mVideoView.requestFocus();
        mVideoView.start();

        buttonPlayVideo.setOnClickListener( new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                VideoView mVideoView = (VideoView) findViewById( R.id.videoview );

               String uriPath = "android.resource://com.mobiloitte.videoplayer/" + R.raw.videoplayer;
           //String uriPath="https://youtu.be/o-1dMLJwOe0";
                Uri uri = Uri.parse( uriPath );
                mVideoView.setVideoURI( uri );
                mVideoView.requestFocus();
                mVideoView.start();

                mVideoView.setVideoPath(  "android.resource://com.mobiloitte.videoplayer/" + R.raw.video );
              //  mVideoView.setVideoPath(  "https://youtu.be/o-1dMLJwOe0" );
                mVideoView.setMediaController( mediaC );
                mVideoView.start();
            }


        } );


    }

    private int getRawResIdByName(String video) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(video, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + video + "==> Res ID = " + resID);

        return resID;
    }




    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
