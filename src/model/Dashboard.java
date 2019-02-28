package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dashboard {
    private User currentUser;
    private Profile userProfile;
    private Library library;
    private SongPlayer songPlayer;


    public Dashboard (GuestUser g){
        currentUser = g;
        userProfile = null;
        library = g.getLibrary();
    }

    public Dashboard (RegisteredUser rg){
        currentUser = rg;
        userProfile = rg.getProfile();
        library = rg.getLibrary();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public SongPlayer getSongPlayer() {
        return songPlayer;
    }

    public void setSongPlayer(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }
}
