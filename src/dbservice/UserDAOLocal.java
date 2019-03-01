package dbservice;

import model_rework.GuestUser;
import model_rework.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOLocal implements UserDAO {

    private User localUser;

    @Override
    public boolean addUser(User user) {
        localUser = user;
        return false;
    }

    @Override
    public boolean deleteUser(int user_id) {
        localUser = null;
        return true;
    }

    @Override
    public void updateUser(User user) {
        localUser = user;
    }

    @Override
    public User getUser(String username, String password) {
        GuestUser newUser = new GuestUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        localUser = newUser;
        return newUser;
    }

    @Override
    public boolean checkUsername(String username) {
        return localUser.getUsername().equals(username);
    }
}
