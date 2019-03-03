package model;

import java.io.Serializable;

public class RegisteredUser extends User implements Serializable {

    private Profile profile;

    public RegisteredUser(Profile profile){
        this.profile = profile;
    }

    public Profile getProfile(){
        return profile;
    }
}
