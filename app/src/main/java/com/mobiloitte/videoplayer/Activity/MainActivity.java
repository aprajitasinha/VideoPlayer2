package com.mobiloitte.videoplayer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mobiloitte.videoplayer.ActivityVideoPlayer;
import com.mobiloitte.videoplayer.R;
import com.mobiloitte.videoplayer.databinding.AcivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AcivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(MainActivity.this,R.layout.acivity_main);
        binding.btnAudio.setOnClickListener(this);
        binding.btnVideo.setOnClickListener(this);
        binding.btnSqlite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.btn_audio:
        Intent intent=new Intent(MainActivity.this,ActivityAudioPlayer.class);
        startActivity(intent);
        break;

    case R.id.btnVideo:
        Intent i= new Intent(MainActivity.this, ActivityVideoPlayer.class);
    startActivity(i);
    break;
    case R.id.btnSqlite:
        Intent intent1=new Intent(MainActivity.this,ActivitySqliteDatabase.class);
         startActivity(intent1);
          break;
}
    }
}
