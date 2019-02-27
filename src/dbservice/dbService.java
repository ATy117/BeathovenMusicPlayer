package dbservice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegisteredUser;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;


public class dbService {
    private dbConnection connection;

    public dbService(dbConnection connection){
        this.connection = connection;
    }

    public byte[] toBlob(Object object){
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

    public static RegisteredUser toRegUser(ResultSet rs) throws SQLException{
        RegisteredUser toRegUser = null;
        try(ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(RegisteredUserDB.COL_REGUSERBLOB));
            ObjectInputStream ois = new ObjectInputStream(bais);){
            toRegUser = (RegisteredUser) ois.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return toRegUser;
    }

    public boolean checkUsername(String username){
        ArrayList<RegisteredUser> regUsers = getAllRegUsers();
        for(RegisteredUser registeredUser: regUsers){
            String tempRegisteredUser = registeredUser.getProfile().getUsername();
            if(tempRegisteredUser.equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String username, String password, RegisteredUser registeredUser) {
        Connection connect = connection.getConnection();
        byte[] stream = toBlob(registeredUser);
        if(!checkUsername(username)) {
            String query = "INSERT INTO " +
                    RegisteredUserDB.TABLE +
                    " VALUES(?,?,?)";
            try {
                PreparedStatement statement = connect.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setBytes(3, stream);

                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }

    public ArrayList<RegisteredUser> getAllRegUsers() {
        ArrayList<RegisteredUser> regUsers = new ArrayList<>();
        Connection connect = connection.getConnection();
        String query = 	"SELECT " + RegisteredUserDB.COL_REGUSERBLOB +
                " FROM " + RegisteredUserDB.TABLE;


        try {
            PreparedStatement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                regUsers.add(toRegUser(rs));
            }

            rs.close();
            statement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return regUsers;
    }


    public RegisteredUser checkLogIn(String username, String password){
        Connection connect = connection.getConnection();
        String query = 	"SELECT *" +
                " FROM " + RegisteredUserDB.TABLE;


        try {
            PreparedStatement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String dbUsername = rs.getString(RegisteredUserDB.COL_USERNAME);
                String dbPassword = rs.getString(RegisteredUserDB.COL_PASSWORD);
                if(dbUsername.equals(username) && dbPassword.equals(password)){
                    return toRegUser(rs);
                }

            }

            rs.close();
            statement.close();
            connect.close();
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
