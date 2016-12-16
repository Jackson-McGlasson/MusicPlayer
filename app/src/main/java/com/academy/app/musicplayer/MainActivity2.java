package com.academy.app.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private Button changeSongButtonVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        changeSongButtonVar = (Button) findViewById(R.id.song1Button);
    }
    public void song1(View stopButton){
        Intent launchSongPlayer = new Intent(this, MainActivity.class);
        startActivity(launchSongPlayer);
        String message = String.valueOf(R.raw.ardoustask);
        launchSongPlayer.putExtra("songMessage", message);

    }
    public void launchBargains(View view){
        String songID = String.valueOf(R.raw.bargainsinatuxedo);
        launchPlayer(songID);
    }

    public void launchArduous(View view){
        String songID = String.valueOf(R.raw.ardoustask);
        launchPlayer(songID);
    }public void launchPlayer(String songID){
//Create your Intent:  Intent launchSongPlayer
        String message = String.valueOf(R.raw.bargainsinatuxedo);
//        launchSongPlayer.putExtra("songMessage", message);
//Launch your Intent
    }
}


