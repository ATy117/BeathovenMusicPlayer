package model_rework;

import java.io.File;

public class RegisteredUserBuilder {
    private RegisteredUser userTMP;

    public RegisteredUserBuilder (){
        userTMP = new RegisteredUser();
    }

    public RegisteredUser build(){
        return userTMP;
    }

    public RegisteredUserBuilder withUserID(int user_id){
        userTMP.setUser_id(user_id);
        return this;
    }

    public RegisteredUserBuilder withUsername(String username){
        userTMP.setUsername(username);
        return this;
    }

    public RegisteredUserBuilder withPassword(String password){
        userTMP.setPassword(password);
        return this;
    }

    public RegisteredUserBuilder withFirstName(String first_name){
        userTMP.setFirst_name(first_name);
        return this;
    }

    public RegisteredUserBuilder withLastName(String last_name){
        userTMP.setLast_name(last_name);
        return this;
    }

    public RegisteredUserBuilder withAvatar(File avatar){
        userTMP.setAvatarURL(avatar);
        return this;
    }
}
