package dbservice;

import model_rework.User;

public class UserDAODB implements UserDAO {
    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int user_id) {
        return false;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUser(String username, String password) {
        return null;
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }
}
