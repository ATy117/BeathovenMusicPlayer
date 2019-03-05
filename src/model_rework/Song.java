package model_rework;

import java.io.File;

public class Song {
    private static int instances = 0;

    private int song_id;
    private String song_name;

    private String genre;
    private int year;

    private int uploader_id;

    private int album_id;

    private String artist_name;

    private boolean favorite;
    private File song_URL;

    private int times_played;

    public Song(){
        this.song_id = instances;
        instances++;
    }


    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(int uploader_id) {
        this.uploader_id = uploader_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public File getSong_URL() {
        return song_URL;
    }

    public void setSong_URL(File song_URL) {
        this.song_URL = song_URL;
    }

    public int getTimes_played() {
        return times_played;
    }

    public void setTimes_played(int times_played) {
        this.times_played = times_played;
    }
}
