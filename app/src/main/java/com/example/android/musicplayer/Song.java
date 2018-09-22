package com.example.android.musicplayer;


public class Song {

    private String TitleSong;
    private String ArtistName;
    private String AlbumName;
    private int Thumbnail;
    private  int Sonoro;

    public Song(String titleSong, String artistName, String albumName, int thumbnail,int sonoro) {
        TitleSong = titleSong;
        ArtistName = artistName;
        AlbumName = albumName;
        Thumbnail = thumbnail;
        Sonoro = sonoro;

}
    String getTitleSong() {return TitleSong;}
    String getArtistName() {return ArtistName;}
    String getAlbumName() {return AlbumName;}
    int getThumbnail() {return Thumbnail;}
     int getSonoro(){return Sonoro;}
}
