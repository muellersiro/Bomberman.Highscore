package Application.Highscore;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by siro on 07.12.16.
 */
public class DBConnection {

    private static DBConnection instance = new DBConnection();

    private Connection dbConnection;

    private final static String CONNECTION = "jdbc:sqlite:PLAYERSCORE.db";

    private DBConnection(){

    }

    public static DBConnection getInstance(){
        return instance;
    }

    public Connection getDBConnection() {
        if (dbConnection != null) {

            return dbConnection;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection(CONNECTION);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database PLAYERSCORE successfully");
        return dbConnection;
    }

    public void close(){
        try {
            dbConnection.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}
