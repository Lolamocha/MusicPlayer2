package com.example.android.musicplayer;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import static android.media.MediaPlayer.*;
import static com.example.android.musicplayer.Song.getSonoro;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton playBtn, pauseBtn, stopBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;

    TextView txtSong;
    TextView txtAlbum;
    TextView txtArtist;
    ImageView imgThumbnail;

    int pauseCurrentPosition;

    public static MediaPlayer mp;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
   // private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
       // @Override
       // public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
           // releaseMediaPlayer();
       // }
   // };


    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);


        //keep data
        Intent intent = getIntent();
        String ArtistName = intent.getExtras().getString("ArtistName");
        String AlbumName = intent.getExtras().getString("AlbumName");
        String TitleSong = intent.getExtras().getString("TitleSong");
        int Thumbnail = intent.getExtras().getInt("Thumbnail");

        ////che ci metto qui ?
        final int Sonoro = intent.getExtras().getInt("Sonoro");


        txtSong = findViewById(R.id.txt_title);
        txtAlbum = findViewById(R.id.txt_album);
        txtArtist = findViewById(R.id.txt_artist);
        imgThumbnail = findViewById(R.id.img_thumbnail);


        //setting value
        txtSong.setText(TitleSong);
        txtArtist.setText(AlbumName);
        txtAlbum.setText(ArtistName);
        imgThumbnail.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgThumbnail.setImageResource(Thumbnail);

        playBtn = findViewById(R.id.suona);
        pauseBtn = findViewById(R.id.pause);
        stopBtn = findViewById(R.id.stop);

        playBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (()){//e qui ???
//// cosa ci faccio qui?
            case  R.id.suona:
                if(mp==null){
               mp = MediaPlayer.create(SongActivity.this,Song.getSonoro());

                mp.start();}
                break;
            case R.id.pause:
                break;
            case R.id.stop:
                mp.stop();
                break;
        }

    }


}




