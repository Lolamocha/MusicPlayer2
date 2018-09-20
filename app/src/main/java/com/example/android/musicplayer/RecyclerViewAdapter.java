package com.example.android.musicplayer;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Song> mData;

    RecyclerViewAdapter(Context mContext, List<Song> mData) {
        this.mContext = mContext;
        this.mData = (ArrayList<Song>) mData;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_song,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_song_title.setText(mData.get(position).getTitleSong());
        holder.img_song_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,SongActivity.class);

                //passing data to the song activity

                intent.putExtra("AlbumName",mData.get(position).getAlbumName());
                intent.putExtra("ArtistName",mData.get(position).getArtistName());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                intent.putExtra("TitleSong",mData.get(position).getTitleSong());
               intent.putExtra("Sonoro",mData.get(position).getSonoro());

                //start the activity
                mContext.startActivity(intent);
            }});}

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_song_title;
        ImageView img_song_thumbnail;
        CardView cardView;

        MyViewHolder(View itemView) {
            super(itemView);

            tv_song_title = itemView.findViewById(R.id.song_title_id);
            img_song_thumbnail = itemView.findViewById(R.id.song_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);}
    }
}
