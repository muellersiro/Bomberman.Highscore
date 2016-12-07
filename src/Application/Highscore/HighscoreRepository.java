package Application.Highscore;

import application.network.protocol.HiscoreEntry;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by siro on 07.12.16.
 */


public class HighscoreRepository extends Highscore {

    private String dbname = "PLAYERSCORE";

    private SqliteUtil sqliteUtil = new SqliteUtil();


    public HighscoreRepository() {
        boolean exists;

        exists  = sqliteUtil.ifDBExists();

        if (exists == false){
            sqliteUtil.createDB();
        }
        sqliteUtil.createTable();
    }

    @Override
    public boolean updatePlayerScore(String playername, Integer score) {

        Boolean state = false;
        Boolean userExists;


        userExists = ifPlayerExists(playername);
        if (userExists) {
            sqliteUtil.update(playername, score);
            System.out.println("Record updated successfully");
            state = true;
        } else {
            insertPlayerScore(playername, score);
        }

        return state;
    }

    @Override
    public Integer readPlayerScore(String playername) {
        Connection dbcon;
        Statement stmt;
        Integer score = null;

        dbcon = getDBConnection();

        try {

            stmt = dbcon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + this.dbname + " WHERE username = " + playername);

            while (rs.next()) {

            }
            rs.close();
            stmt.close();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("ifUserexists Operation done successfully");


        return score;
    }


    @Override
    public boolean updatePlayerScore_new(HiscoreEntry Highscore) {

        Connection dbcon;
        Statement stmt;
        Boolean state = false;
        Boolean userExists;

        dbcon = getDBConnection();

        userExists = ifPlayerExists(Highscore.getPlayerName());
        if (userExists) {
            try {
                stmt = dbcon.createStatement();
                String sql = "UPDATE " + this.dbname + " SET SCORE = " + Highscore.getScore() + "WHERE playername = " + Highscore.getPlayerName();

                stmt.executeUpdate(sql);

                stmt.close();
                dbcon.commit();
                dbcon.close();

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Record updated successfully");
            state = true;
        } else {
            insertPlayerScore(Highscore.getPlayerName(), Highscore.getScore());
        }

        return state;
    }

    @Override
    public Integer readPlayerScore_new(HiscoreEntry Highscore) {
        Connection dbcon;
        Statement stmt;
        Integer score = null;

        dbcon = getDBConnection();

        try {

            stmt = dbcon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + this.dbname + " WHERE username = " + Highscore.getPlayerName());

            while (rs.next()) {

            }
            rs.close();
            stmt.close();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("ifUserexists Operation done successfully");


        return score;
    }


    private boolean ifPlayerExists(String playername) {
        Boolean state = false;


        sqliteUtil.getHighscore(playername);

        System.out.println("ifUserexists Operation done successfully");


        return state;
    }

    private boolean insertPlayerScore(String playername, Integer score) {
        Connection dbcon;
        Statement stmt;
        Boolean state = false;

        dbcon = getDBConnection();

        try {
            stmt = dbcon.createStatement();
            String sql = "INSERT INTO " + this.dbname + " ( USERNAME,SCORE ) " +
                    "VALUES ( " + playername + "," + score + ")";
            stmt.execute(sql);

            stmt.close();
            dbcon.commit();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Record created successfully");
        state = true;

        return state;
    }


    private Connection getDBConnection() {
        Connection dbcon = null;

        try {
            Class.forName("org.sqlite.JDBC");
            dbcon = DriverManager.getConnection("jdbc:sqlite:" + this.dbname + ".db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database" + this.dbname + "successfully");
        return dbcon;
    }

    private void createTable() {
        Statement stmt = null;
        Connection dbcon = null;

        dbcon = getDBConnection();
        try {
            stmt = dbcon.createStatement();
            String sql = "CREATE TABLE " + dbname +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " USERNAME       TEXT    NOT NULL, " +
                    " SCORE          INT     NOT NULL " +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

    }

    private boolean ifDBExists() {
        boolean exists = false;

        File file = new File (dbname + ".db");

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


    private void createDB() {

        Statement stmt = null;
        Connection dbcon = null;

        dbcon = getDBConnection();

        try {
            stmt = dbcon.createStatement();
            String sql = "CREATE DATABASE if not exists" + dbname;
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Database created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }
}

