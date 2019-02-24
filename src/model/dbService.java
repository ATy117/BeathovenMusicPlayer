package model;
import java.sql.*;
import java.io.*;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        try(ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(registeredUser.COL_REG_USER_BLOB));
            ObjectInputStream ois = new ObjectInputStream(bais);){
            toRegUser = (RegisteredUser) ois.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return toRegUser;
    }

    public void addUser(RegisteredUser registeredUser) {
        Connection connect = connection.getConnection();
        String username = registeredUser.getProfile().getUsername();
        String password = null;
        byte[] stream = toBlob(registeredUser);

        String query = "INSERT INTO " +
                registeredUser.TABLE +
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
        List<RegisteredUser> regUsers = FXCollections.observableArrayList();
        Connection connect = connection.getConnection();
        String query = 	"SELECT " + registeredUser.COL_REG_USER_BLOB +
                " FROM " + registeredUser.TABLE;


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

        return (ObservableList<registeredUser>) regUsers;
    }


}
