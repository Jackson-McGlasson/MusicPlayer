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

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song1;
    private MediaPlayer song2;
    public Button playButtonVar;
    public Button pauseButtonVar;
    public Button stopButtonVar;
    public Button jumpForwardButtonVar;
    public Button jumpBackwardButtonVar;
    public Button changeSongButtonVar;
    public double endTimeMS;
    public double currentTimeMS;

    public double currentMinutes;
    public double currentSeconds;
    public TextView endTimeViewVar;
    public TextView currentTimeViewVar;
    private Handler myHandler = new Handler();
    private SeekBar mySongBarVar;
    private double seekTime;
    private double startTimeMS;
    private String songTitle;
    private String songArtist;
    private int songID;
    private TextView songTitleViewVar;
    private TextView songArtistViewVar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        song1 = MediaPlayer.create(this, R.raw.ardoustask);
          song1 = MediaPlayer.create(this, songID);
//        song2 = MediaPlayer.create(this, R.raw.bargainsinatuxedo);
        Intent thisIntent = getIntent();
        String songID = thisIntent.getStringExtra("songMessage");
        MediaMetadataRetriever songInfo = new MediaMetadataRetriever();
//        Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ardoustask);
        Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + songID);
        songInfo.setDataSource(this, mediaPath);
        songTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        songArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        songID = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        MediaPlayer.create(this, Integer.parseInt(songID));



        playButtonVar = (Button) findViewById(R.id.playButton);
        stopButtonVar = (Button) findViewById(R.id.stopButton);
        pauseButtonVar = (Button) findViewById(R.id.pauseButton);
        changeSongButtonVar = (Button) findViewById(R.id.changeSongButton);


        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        stopButtonVar.setEnabled(false);

        currentTimeViewVar = (TextView) findViewById(R.id.currentTime);
        endTimeViewVar = (TextView) findViewById(R.id.timeLeft);
        songTitleViewVar = (TextView) findViewById(R.id.title);
       songArtistViewVar = (TextView) findViewById(R.id.artist);
        songTitleViewVar.setText(songTitle);
        songArtistViewVar.setText(songArtist);
        jumpForwardButtonVar = (Button) findViewById(R.id.jumpForwardButton);
        jumpBackwardButtonVar = (Button) findViewById(R.id.jumpBackwardButton);
        mySongBarVar= (SeekBar) findViewById(R.id.mySongBar);
        endTimeMS = song1.getDuration();
        currentTimeMS = song1.getCurrentPosition();
        mySongBarVar.setMax((int) endTimeMS);
        mySongBarVar.setProgress((int) currentTimeMS);

        int endMinutes = (int) (endTimeMS / 1000 / 60);
        int endSeconds = ((int) (endTimeMS / 1000)) %60;
        endTimeViewVar.setText(endMinutes + "min" + endSeconds + "sec");
        currentMinutes =(int) (currentTimeMS/1000/60);
        currentSeconds = ((int)(currentTimeMS/1000)) %60;



            mySongBarVar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekTime=progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                startTimeMS = currentTimeMS;
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                song1.seekTo((int)seekTime);
                startTimeMS = seekTime;
                }

        });
        myHandler.postDelayed(UpdateSongTime, 100);
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeViewVar.getText();
            currentTimeMS = song1.getCurrentPosition();
            currentMinutes =(int) (currentTimeMS/1000/60);
            currentSeconds = ((int)(currentTimeMS/1000)) %60;
            currentTimeViewVar.setText(currentMinutes + "min" + currentSeconds + "sec");
            mySongBarVar.setProgress((int) currentTimeMS);
            myHandler.postDelayed(this,100);
        }
    };
    public void playSong(View playButton){
        song1.start();
        pauseButtonVar.setEnabled(true);
        playButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(true);
        jumpForwardButtonVar.setEnabled(true);
        jumpBackwardButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "Song Playing";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();

    }
    public void pauseSong(View pauseButton){
        song1.pause();
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        stopButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "Song Paused";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
    }
    public void stopSong(View stopButton){

        song1.seekTo(0);
        song1.pause();
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        stopButtonVar.setEnabled(false);
        jumpForwardButtonVar.setEnabled(false);
        jumpBackwardButtonVar.setEnabled(false);
        Context context = getApplicationContext();
        CharSequence text = "Song Stopped";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
    }
    public void jumpForwardSong(View stopButton){
        song1.seekTo( (int) (currentTimeMS + 5000) );
        stopButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "Skipped ahead 5 seconds";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();

    }
    public void jumpBackwardSong(View stopButton){
        song1.seekTo( (int) (currentTimeMS - 5000) );
        stopButtonVar.setEnabled(true);
        Context context = getApplicationContext();
        CharSequence text = "Skipped back 5 seconds";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();

    }
    public void changeSong(View songPickerButton){
        Intent launchSongPicker = new Intent(this, MainActivity2.class);
        startActivity(launchSongPicker);
    }
}

