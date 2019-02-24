package model;

public class Dashboard {
    private User currentUser;
    private Profile userProfile;
    private Library library;

    public Dashboard (GuestUser g){
        currentUser = g;
        userProfile = null;
        library = new Library();
    }

    public Dashboard (RegisteredUser rg){
        currentUser = rg;
        userProfile = new Profile();
        library = rg.getLibrary();
    }

    public void setRegisteredLibraryFromGuest (){
        currentUser.setLibrary(library);
    }


}
