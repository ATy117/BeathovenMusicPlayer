package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TrackCollection implements Serializable, Favoritable {
    private String collectionName;
    private List<Track> trackList = new ArrayList<>();
    private Boolean isFavorite = false;

    public String getCollectionName() {
        return collectionName;
    }

    public void rename(String collectionName) {
        this.collectionName = collectionName;
    }

    public Track getTrack(int index){
        return trackList.get(index);
    }

    public void addTrack (Track track){
        trackList.add(track);
    }

    public void deleteTrack(int index){
        trackList.remove(index);
    }

    public void deleteTrack(Track track){
        trackList.remove(track);
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public int getTrackCount(){
        return trackList.size();
    }

    @Override
    public void mark(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
