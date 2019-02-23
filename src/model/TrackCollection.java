package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class TrackCollection implements Serializable {
    private String collectionName;
    private ArrayList<Track> trackList = new ArrayList<>();

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Track getTrack(int index){
        return trackList.get(index);
    }

    public void deleteTrack(int index){
        trackList.remove(index);
    }

    public void deleteTrack(Track track){
        trackList.remove(track);
    }
}
