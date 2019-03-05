package dbservice;
import java.sql.*;

public class dbConnection {
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/beathovendb";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "password";

    public Connection getConnection() {
        try {
            byte[] a = new byte[] {12*10+2,10*10+1,114,111,118,105,116,48,57,56};


            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL +"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);

            System.out.println("[MYSQL] CONNECTED SUCCESSFULLY");
            return connection;

			/*
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM person");

			while(rs.next()) {
				System.out.println(rs.getString("idstatus") + ", " + rs.getString("name"));
			}*/
        }catch(SQLException e) {
            System.out.println("[MYSQL] DID NOT CONNECT");
            e.printStackTrace();
            return null;
        }catch(ClassNotFoundException e) {
            System.out.println("[MYSQL] DID NOT CONNECT");
            e.printStackTrace();
            return null;
        }
    }
}
