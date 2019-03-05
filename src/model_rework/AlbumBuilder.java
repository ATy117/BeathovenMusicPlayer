package model_rework;

import java.io.File;

public class AlbumBuilder {
    private Album albumTMP;

    public AlbumBuilder(){
        albumTMP = new Album();
    }

    public Album build(){
        return albumTMP;
    }

    public AlbumBuilder withAlbumID(int album_id){
        albumTMP.setAlbum_id(album_id);
        return this;
    }

    public AlbumBuilder withName(String name){
        albumTMP.setName(name);
        return this;
    }

    public AlbumBuilder withOwner(int user_id){
        albumTMP.setUser_id(user_id);
        return this;
    }

    public AlbumBuilder withFileCover(File cover){
        albumTMP.setCover_URL(cover);
        return this;
    }

    public AlbumBuilder withArtistID(int artist_id){
        albumTMP.setArtist_id(artist_id);
        return this;
    }

    public AlbumBuilder withArtist(String artist){
        albumTMP.setArtist_name(artist);
        return this;
    }
}
