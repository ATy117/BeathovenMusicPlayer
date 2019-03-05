package model_rework;

import java.io.File;

public class Album {
    private static int instances = 0;

    private int album_id;
    private String name;
    private int user_id;
    private File cover_URL;
    private int artist_id;
    private String artist_name;


    public Album(){
        this.album_id = instances;
        instances++;
    }


    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public File getCover_URL() {
        return cover_URL;
    }

    public void setCover_URL(File cover_URL) {
        this.cover_URL = cover_URL;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
