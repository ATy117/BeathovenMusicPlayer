package model_rework;

public class PlaylistBuilder {
    private Playlist playlistTMP;

    public PlaylistBuilder(){
        playlistTMP = new Playlist();
    }

    public Playlist build(){
        return playlistTMP;
    }

    public PlaylistBuilder withPlaylistID(int playlist_id){
        playlistTMP.setPlaylist_id(playlist_id);
        return this;
    }

    public PlaylistBuilder withName (String name){
        playlistTMP.setName(name);
        return this;
    }

    public PlaylistBuilder withOwner(int user_id){
        playlistTMP.setUser_id(user_id);
        return this;
    }

    public PlaylistBuilder withFavoriteStatus (boolean status){
        playlistTMP.setFavorite(status);
        return this;
    }


}
