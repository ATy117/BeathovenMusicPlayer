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
        setAppDirectory();
    }

    @Override
    public boolean addUser(User user) {
        int userIDTemp = user.getUser_id();
        String usernameTemp = user.getUsername();
        String passwordTemp = user.getPassword();
        String lastNameTemp = user.getLast_name();
        String firstNameTemp = user.getFirst_name();
        FileInputStream userStream = null;
        try {
            userStream = new FileInputStream(user.getAvatarURL());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            statement.setBinaryStream(5, userStream);

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
        FileInputStream userStream = null;
        try {
            userStream = new FileInputStream(user.getAvatarURL());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            statement.setBinaryStream(5, userStream);

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
                    try {
                        user = toUser(rs);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    rs.close();
                    statement.close();
                    return user;
                }
            }

            rs.close();
            statement.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User toUser(ResultSet rs) throws SQLException, IOException {
        User user = new RegisteredUser();

        user.setUser_id(rs.getInt(this.COL_USERID));
        user.setUsername(rs.getString(this.COL_USERNAME));
        user.setPassword(rs.getString(this.COL_PASSWORD));
        user.setLast_name(rs.getString(this.COL_LASTNAME));
        user.setFirst_name(rs.getString(this.COL_FIRSTNAME));
        user.setAvatarURL(toFile(rs));

        return user;
    }

    private File toFile(ResultSet rs) throws SQLException, IOException {
        File file = new File("src/resources/" + rs.getString(this.COL_USERNAME)+".png");
        OutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = rs.getBinaryStream(this.COL_AVATARURL);
        byte[] buffer = new byte[4096];
        while (inputStream.read(buffer) > 0){
            outputStream.write(buffer);
        }
        System.out.println(file.getAbsolutePath());
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

    private void setAppDirectory() {
        String appdirectory = System.getProperty("user.home") + "/Documents/Beathoven/";
        File appfolder = new File(appdirectory);
        if(!appfolder.exists()) {
            appfolder.mkdir();
        }
    }

}
