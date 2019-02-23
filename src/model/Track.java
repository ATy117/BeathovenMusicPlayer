package model;

import java.io.Serializable;

public abstract class Track implements Serializable {

    private String name;
    private String trackURL;
    private String imageURL;
    private String collection;
    private String genre;
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getTrackURL() {
        return trackURL;
    }

    public void setTrackURL(String trackURL) {
        this.trackURL = trackURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
}
