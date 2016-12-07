package Application.Highscore;

/**
 * Created by siro on 20.11.16.
 */

import application.network.protocol.HiscoreEntry;

import java.io.File;
import java.sql.*;

public class SqliteUtil {


    public SqliteUtil() {

    }


    public void update(String playername, Integer score) {
        String sql = "UPDATE PLAYERSCORE SET SCORE = " + score + "WHERE USERNAME = " + playername;
        executeSQL(sql);

    }

    public void insert(String playername, Integer score) {
        String sql = "INSERT INTO PLAYERSCORE ( USERNAME,SCORE ) VALUES(" +"'" + playername + "'," + "'" + score + "'" +")";
        executeSQL(sql);
    }

    public void delete(String playername) {
        String sql = "DELETE FROM PLAYERSCORE WHERE USERNAME = " + playername;
        executeSQL(sql);
    }


    //public boolean getAll() {

    //}

    public Integer getHighscore(String playername) {
        ResultSet res;
        String sql = "SELECT id FROM PLAYERSCORE WHERE USERNAME = " +"'"+ playername+"'";
        res = executeSQLwithResult(sql);
        try {
            while (res.next()) {

            }
        } catch (Exception e) {
        }

        return 0;
    }

    public boolean ifDBExists() {
        boolean exists = false;

        File file = new File ("PLAYERSCORE.db");

        if(file.exists()) //here's how to check
        {
            System.out.print("This database already exists");
            exists = true;
        }
        else{
            exists = false;
        }

        return exists;
    }


    public void createDB() {
        Connection con = DBConnection.getInstance().getDBConnection();
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS PLAYERSCORE (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     "USERNAME       TEXT    NOT NULL, " +
                     "SCORE          INT     NOT NULL " +
                     ")";

        executeSQL(sql);

    }


    private ResultSet executeSQLwithResult(String sql) {
        Connection con = DBConnection.getInstance().getDBConnection();
        Statement stmt;
        ResultSet res = null;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private void executeSQL(String sql) {
        Connection con = DBConnection.getInstance().getDBConnection();
        Statement stmt;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}