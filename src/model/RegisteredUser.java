package model;

public class RegisteredUser extends User{
    private Profile profile;

    public RegisteredUser(Profile profile){
        this.profile = profile;
    }

    public Profile getProfile(){
        return profile;
    }
}
