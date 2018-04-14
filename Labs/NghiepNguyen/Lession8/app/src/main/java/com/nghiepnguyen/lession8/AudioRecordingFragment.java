package com.nghiepnguyen.lession8;


import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AudioRecordingFragment extends Fragment {

    // 1. Create an object for MeadiaRecorder
    MediaRecorder recorder;
    // To display the status of AudioRecording test
    TextView tv;
    // To play the recorded the audio from SDCard
    MediaPlayer mp;
    String path;

    public AudioRecordingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio_recording, container, false);
        tv = (TextView) view.findViewById(R.id.tv1);
        recorder = new MediaRecorder();
        //2. Set the audio source MIC for recording
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //3. Set the output format with the extension of amr with the high quality NB (WB-low quality) for audio
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        //4. Set the Encoder
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //5. Set the source to store the file
        recorder.setOutputFile("/sdcard/test.amr");
        return view;
    }

    //Start recording by clicking the Start Button
    public void start(View v) {
        // prepare method need to surround with try catch
        try {
            recorder.prepare();
            tv.setText("Start Recording");
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Stop recording by clicking the Stop Button
    public void stop(View v) {
        tv.setText("Stop Recording");
        recorder.stop();
    }

    public void stplay(View v) {
        tv.setText("Playing Audio");
        mp = new MediaPlayer();
        try {
            mp.setDataSource("/sdcard/test.amr");
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void spplay(View v) {
        tv.setText("Stopped Playing");
        mp.stop();
        mp.release();
    }
}
