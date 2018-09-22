package com.example.android.musicplayer;



import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;



public class SongActivity extends AppCompatActivity  {

    ImageButton playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;

    TextView txtSong;
    TextView txtAlbum;
    TextView txtArtist;
    ImageView imgThumbnail;

    int sonoro = 0;
    int totalTime;

    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Log.v("SongActivity","onCreate");

        //keep data
        Intent intent = getIntent();
        String ArtistName = intent.getExtras().getString("ArtistName");
        String AlbumName = intent.getExtras().getString("AlbumName");
        String TitleSong = intent.getExtras().getString("TitleSong");
        int Thumbnail = intent.getExtras().getInt("Thumbnail");
        sonoro = intent.getExtras().getInt("Sonoro");

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


        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.elapsedTimeLabel);


        mp = MediaPlayer.create(getApplicationContext(),sonoro);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f,0.5f);
        totalTime = mp.getDuration();



        positionBar =  findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp.seekTo(progress);
                    positionBar.setProgress(progress);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeBar =  findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volumeNum = progress /100f;
                mp.setVolume(volumeNum,volumeNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Thread (update positionBar e timeLabel
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mp != null){
                 try{
                     Message msg = new Message();
                     msg.what = mp.getCurrentPosition();
                     handler.sendMessage(msg);
                     Thread.sleep(1000);
                 }catch (InterruptedException e){}
                }
            }
        }).start();
    }



    //private
     Handler handler = new MyVeryOwnHandler();


    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time / 1000 / 60 ;
        int sec = time /1000 % 60 ;

        timeLabel =  min + ":";
        if(sec <10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view) {
        if (!mp.isPlaying()) {
            mp.start();
            playBtn.setBackgroundResource(R.drawable.pause);

        } else if (mp.isPlaying()) {
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.stop);

        }else{
            if(mp.isPlaying())
                mp.stop();

            mp.reset();
            if(!mp.isPlaying())
            playBtn.setBackgroundResource(R.drawable.play);
        }
    }

      class MyVeryOwnHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentPosition = msg.what;

            //Update PositionBar
            positionBar.setProgress(currentPosition);

            //Update labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText("-" + remainingTime);
        }
    }
    }











