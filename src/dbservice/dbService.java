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

    public void addUser(String username, String password, RegisteredUser registeredUser) {
        Connection connect = connection.getConnection();
        byte[] stream = toBlob(registeredUser);

        String query = "INSERT INTO " +
                RegisteredUserDB.TABLE +
                " VALUES(?,?,?)";
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBytes(3, stream);

            statement.executeUpdate();
            System.out.println("INSERT TO [REGISTERED_USER] TABLE SUCCESS!");
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("INSERT TO [REGISTERED_USER] TABLE FAILED!");
        }
    }

    public ObservableList<RegisteredUser> getAllRegUsers() {
        ObservableList<RegisteredUser> regUsers = FXCollections.observableArrayList();
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

            System.out.println("[REGISTERED_USER] SELECT SUCCESS!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[REGISTERED_USER] SELECT FAILED!");
            return null;
        }

        return regUsers;
    }

    public boolean checkExistingUsername(String username){
        Connection connect = connection.getConnection();
        ObservableList<RegisteredUser> regUsers = getAllRegUsers();
        String query = 	"SELECT " + RegisteredUserDB.COL_USERNAME +
                " FROM " + RegisteredUserDB.TABLE;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                String currDBUsername = rs.toString();
                for(RegisteredUser registeredUser: regUsers){
                    String tempUsername = registeredUser.getProfile().getUsername();
                    if(tempUsername.equalsIgnoreCase(currDBUsername)){
                        return true;
                    }
                }
            }

            rs.close();
            statement.close();
            connect.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[REGISTERED_USER] SELECT FAILED!");
            return false;
        }
    }

}
