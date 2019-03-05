package model_rework;

import java.io.File;

public class SongBuilder {
    private Song songTMP;

    public SongBuilder(){
        songTMP = new Song();
    }

    public Song build(){
        return songTMP;
    }

    public SongBuilder withSongID(int sond_id){
        songTMP.setSong_id(sond_id);
        return this;
    }

    public SongBuilder withName(String name){
        songTMP.setSong_name(name);
        return this;
    }

    public SongBuilder withGenre(String genre){
        songTMP.setGenre(genre);
        return this;
    }

    public SongBuilder withYear(int year){
        songTMP.setYear(year);
        return this;
    }

    public SongBuilder withOwner(int user_id){
        songTMP.setUploader_id(user_id);
        return this;
    }

    public SongBuilder withAlbumID(int album_id){
        songTMP.setAlbum_id(album_id);
        return this;
    }

    public SongBuilder withArtistName(String artistName){
        songTMP.setArtist_name(artistName);
        return this;
    }

    public SongBuilder withFavoriteStatus(boolean status){
        songTMP.setFavorite(status);
        return this;
    }

    public SongBuilder withFile(File song_URL){
        songTMP.setSong_URL(song_URL);
        return this;
    }

    public SongBuilder withTimesPlayed(int timesPlayed){
        songTMP.setTimes_played(timesPlayed);
        return this;
    }

}
