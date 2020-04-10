package com.mrcaracal.seskayit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    MediaRecorder recorder;
    MediaPlayer player;
    String dosyamiz = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dosyamiz = Environment.getExternalStorageDirectory().getAbsolutePath()+"/seskaydı.mp3";
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(dosyamiz);

    }

    public void ses_kaydet(View view) {
        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(context, "Kayıt Başladı", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ses_kayit_durdur(View view) {
        recorder.stop();
        recorder.release();
        recorder = null;
        Toast.makeText(context, "Kayıt Durdu", Toast.LENGTH_SHORT).show();
    }

    public void ses_oynat(View view) {
        player = new MediaPlayer();
        try {
            player.setDataSource(dosyamiz);
            player.prepare();
            player.start();
            Toast.makeText(context, "Kayıt Oynatılıyor", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void oynatmayi_durdur(View view) {
        if (player != null){
            player.stop();
            player.release();
            player = null;
            Toast.makeText(context, "Ses Oynatma Durdu", Toast.LENGTH_SHORT).show();
        }
    }
}
