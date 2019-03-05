package model_rework;

import java.io.File;

public class GuestUserBuilder {
    private GuestUser userTMP;

    public GuestUserBuilder (){
        userTMP = new GuestUser();
    }

    public GuestUser build(){
        return userTMP;
    }

    public GuestUserBuilder withUserID(int user_id){
        userTMP.setUser_id(user_id);
        return this;
    }

    public GuestUserBuilder withUsername(String username){
        userTMP.setUsername(username);
        return this;
    }

    public GuestUserBuilder withPassword(String password){
        userTMP.setPassword(password);
        return this;
    }

    public GuestUserBuilder withFirstName(String first_name){
        userTMP.setFirst_name(first_name);
        return this;
    }

    public GuestUserBuilder withLastName(String last_name){
        userTMP.setLast_name(last_name);
        return this;
    }

    public GuestUserBuilder withAvatar(File avatar){
        userTMP.setAvatarURL(avatar);
        return this;
    }
}
