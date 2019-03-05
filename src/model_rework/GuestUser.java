package model_rework;

public class GuestUser extends User {
    public GuestUser (){
        setUser_id(instances);
        instances++;
    }
}
