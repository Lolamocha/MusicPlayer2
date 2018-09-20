package com.example.android.musicplayer;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;



import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final ArrayList <Song>Songs = new ArrayList<>();

        Songs.add(new Song("guarda che luna", "Carosone", "chaimamolo", R.drawable.star, R.raw.guarda_che_luna));
        Songs.add(new Song("Teresa non sparare", "Artist1", "AlbumName1", R.drawable.star, R.raw.teresa_non_sparare));
        Songs.add(new Song("Io piaccio", "Artist1", "AlbumName1", R.drawable.star, R.raw.io_piaccio));
        Songs.add(new Song("Non partir", "Artist1", "AlbumName1", R.drawable.star, R.raw.non_partir));


        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, Songs);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);
        }}



