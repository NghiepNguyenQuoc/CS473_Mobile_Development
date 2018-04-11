package com.durgasoft.mediaplayertest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    SeekBar sBar;
    android.os.Handler handler=new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sBar=(SeekBar)findViewById(R.id.sBar);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mPlayer=MediaPlayer.create(getApplicationContext(),R.raw.saripovu);
        sBar.setMax(mPlayer.getDuration());
        updateSeekBar();
    }


    public void updateSeekBar(){
        sBar.setProgress(mPlayer.getCurrentPosition());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        },5000);
    }

    public void test(View v){
       switch (v.getId()){
            case R.id.backward:
                mPlayer.seekTo(mPlayer.getCurrentPosition()-mPlayer.getDuration()/10);
                break;

            case R.id.play:
                    mPlayer.start();
                    break;
            case R.id.pause:
                    mPlayer.pause();
                    break;
            case R.id.stop:
                   mPlayer.stop();
                    mPlayer=MediaPlayer.create(getApplicationContext(),
                    R.raw.saripovu);
                    break;

            case R.id.forward:
                mPlayer.seekTo(mPlayer.getCurrentPosition()+mPlayer.getDuration()/10);
                break;
        }

    }

}
