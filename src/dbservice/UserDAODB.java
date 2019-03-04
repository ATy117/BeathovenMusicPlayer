package dbservice;

import java.io.*;
import java.sql.*;

import model_rework.RegisteredUser;
import model_rework.User;

public class UserDAODB implements UserDAO {
    private Connection connection;
    private final String TABLE = "user";
    private final String COL_USERID = "user.user_id";
    private final String COL_USERNAME = "user.username";
    private final String COL_PASSWORD = "user.password";
    private final String COL_LASTNAME = "user.last_name";
    private final String COL_FIRSTNAME = "user.first_name";
    private final String COL_AVATARURL = "user.avatar_url";

    public UserDAODB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(User user) {
        int userIDTemp = user.getUser_id();
        String usernameTemp = user.getUsername();
        String passwordTemp = user.getPassword();
        String lastNameTemp = user.getLast_name();
        String firstNameTemp = user.getFirst_name();
        byte[] avatarBlobTemp = toBlob(user.getAvatarURL());

        System.out.println(usernameTemp + passwordTemp+ firstNameTemp + lastNameTemp);
        String query = "INSERT INTO " +
                this.TABLE +
                " VALUES(NULL,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usernameTemp);
            statement.setString(2, passwordTemp);
            statement.setString(3, lastNameTemp);
            statement.setString(4, firstNameTemp);
            statement.setBytes(5, avatarBlobTemp);

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int user_id) {
        String query = "DELETE FROM " + this.TABLE + " WHERE " + this.COL_USERID + " = " + user_id;

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateUser(User user) {
        int userID = user.getUser_id();
        String usernameTemp = user.getUsername();
        String passwordTemp = user.getPassword();
        String lastNameTemp = user.getLast_name();
        String firstNameTemp = user.getFirst_name();
        byte[] avatarBlobTemp = toBlob(user.getAvatarURL());

        String query = "UPDATE " + this.TABLE + " SET " +
                this.COL_USERNAME + " = ?, " +
                this.COL_PASSWORD + " = ?, " +
                this.COL_LASTNAME + " = ?, " +
                this.COL_FIRSTNAME + " = ?, " +
                this.COL_AVATARURL + " = ? " +
                "WHERE " + this.COL_USERID + " = " + userID;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usernameTemp);
            statement.setString(2, passwordTemp);
            statement.setString(3, lastNameTemp);
            statement.setString(4, firstNameTemp);
            statement.setBytes(5, avatarBlobTemp);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username, String password) {
        User user = new RegisteredUser();
        String query = "SELECT * FROM " + this.TABLE;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String dbUsername = rs.getString(this.COL_USERNAME);
                String dbPassword = rs.getString(this.COL_PASSWORD);
                if(dbUsername.equals(username) && dbPassword.equals(password)){
                    user = toUser(rs);
                    return user;
                }
            }

            rs.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
    }

    private User toUser(ResultSet rs) throws SQLException{
        User user = new RegisteredUser();

        user.setUser_id(rs.getInt(this.COL_USERID));
        user.setUsername(rs.getString(this.COL_USERNAME));
        user.setPassword(rs.getString(this.COL_PASSWORD));
        user.setLast_name(rs.getString(this.COL_LASTNAME));
        user.setFirst_name(rs.getString(this.COL_FIRSTNAME));
        user.setAvatarURL(toFile(rs));

        return user;
    }

    private File toFile(ResultSet rs) throws SQLException{
        File file = null;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(this.COL_AVATARURL));
            ObjectInputStream ois = new ObjectInputStream(bais);){
            file = (File) ois.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public boolean checkUsername(String username) {
        String query = 	"SELECT " + this.COL_USERNAME +
                " FROM " + this.TABLE;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String usernameTemp = rs.getString(this.COL_USERNAME);
                if(usernameTemp.equals(username)){
                    return true;
                }

            }

            rs.close();
            statement.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private byte[] toBlob(Object object){
        byte[] stream = null;
        // This is used to convert Java objects into OutputStream
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)){
            oos.writeObject(object);
            stream=baos.toByteArray();
        }catch(IOException e){
            // Error in serialization
            e.printStackTrace();
        }

        return stream;
    }
}
