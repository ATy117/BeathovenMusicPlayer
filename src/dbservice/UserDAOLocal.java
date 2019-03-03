package dbservice;

import model_rework.GuestUser;
import model_rework.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOLocal implements UserDAO {

    private static List<User> localUsers = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        localUsers.add(user);
        return true;
    }

    @Override
    public boolean deleteUser(int user_id) {
        for (User u: localUsers){
            if (u.getUser_id() == user_id){
                localUsers.remove(u);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
         for(User u : localUsers){
             if (user.getUser_id() == u.getUser_id()){
                 u.setAvatarURL(user.getAvatarURL());
                 u.setFirst_name(user.getFirst_name());
                 u.setLast_name(user.getLast_name());
                 u.setPassword(user.getPassword());
                 u.setUsername(user.getUsername());
                 break;
             }
         }
    }

    @Override
    public User getUser(String username, String password) {
        GuestUser newUser = new GuestUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return newUser;
    }

    @Override
    public boolean checkUsername(String username) {
        return true;
    }
}
