package model_rework;

import java.io.File;

public class User {
    protected static int instances = 0;

    private int user_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private File avatarURL;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public File getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(File avatarURL) {
        this.avatarURL = avatarURL;
    }
}
