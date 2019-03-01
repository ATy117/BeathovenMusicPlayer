package model_rework;

import java.io.File;

public class Album {
    private int album_id;
    private String name;
    private int user_id;
    private File cover_URL;

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
}
