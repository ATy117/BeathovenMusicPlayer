package model;

import java.io.Serializable;

public class RegisteredUser extends User implements Serializable {
    private static final long serialVersionUID = 5230549922091722630L;
    /////FOR DB PURPOSES ONLY//////
    public static final String TABLE = "registered_users";
    public static final String COL_USERNAME = "registered_users.username";
    public static final String COL_PASSWORD = "registered_users.password";
    public static final String COL_REGUSERBLOB = "registered_users.reguser_blob";
    ///////////////////////////////
    private Profile profile;

    public RegisteredUser(Profile profile){
        this.profile = profile;
    }

    public Profile getProfile(){
        return profile;
    }
}
