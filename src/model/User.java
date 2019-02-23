package model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private Library library = new Library();

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

}
