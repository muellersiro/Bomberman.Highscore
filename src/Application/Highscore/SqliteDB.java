package Application.Highscore;

/**
 * Created by siro on 20.11.16.
 */

import application.network.protocol.HiscoreEntry;
import jdk.nashorn.internal.ir.IfNode;
import sun.tools.tree.IfStatement;

import java.sql.*;

public class SqliteDB extends Highscore {

    private String dbname = "PLAYERSCORE";


    public SqliteDB() {
        boolean exists;
        exists = ifDBExists();

        if (exists = false) {
            createDB();
            createTable();
        } else {
            createTable();
        }

    }

    @Override
    public boolean updatePlayerScore(String playername, Integer score) {

        Connection dbcon;
        Statement stmt;
        Boolean state = false;
        Boolean userExists;

        dbcon = getDBConnection();

        userExists = ifPlayerExists(playername);
        if (userExists) {
            try {
                stmt = dbcon.createStatement();
                String sql = "UPDATE " + this.dbname + " SET SCORE = " + score + "WHERE playername = " + playername;

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
        Connection dbcon;
        Statement stmt;
        Boolean state = false;

        dbcon = getDBConnection();

        try {

            stmt = dbcon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + this.dbname + " WHERE username = " + playername);

            while (rs.next()) {
                state = true;
            }
            rs.close();
            stmt.close();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
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
            String sql = "INSERT INTO " + this.dbname + " (USERNAME,SCORE) " +
                    "VALUES ( " + playername + "," + score + ")";
            stmt.executeUpdate(sql);

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
        Statement stmt = null;
        Connection dbcon = null;
        boolean exists = false;

        dbcon = getDBConnection();
        try {
            stmt = dbcon.createStatement();

            ResultSet resultSet = dbcon.getMetaData().getCatalogs();

            while (resultSet.next()) {
                // Get the database name, which is at position 1
                String databaseName = resultSet.getString(1);
                if (dbname == databaseName) {
                    exists = true;
                }
            }
            resultSet.close();

            System.out.println("DB exist");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

        return exists;
    }


    private void createDB() {

        Statement stmt = null;
        Connection dbcon = null;

        dbcon = getDBConnection();

        try {
            stmt = dbcon.createStatement();
            String sql = "CREATE DATABASE" + dbname;
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Database created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }
}

