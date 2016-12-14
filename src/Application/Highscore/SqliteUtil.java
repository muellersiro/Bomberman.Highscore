package Application.Highscore;

/**
 * Created by siro on 20.11.16.
 */

import application.network.protocol.HiscoreEntry;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUtil {


    public SqliteUtil() {

    }


    public void update(HiscoreEntry hiscoreEntry) {
        String sql = "UPDATE PLAYERSCORE SET SCORE = " + "'" + hiscoreEntry.getScore() + "'" + "WHERE USERNAME = " + "'" + hiscoreEntry.getPlayerName() + "'";
        executeSQL(sql);

    }

    public void insert(HiscoreEntry hiscoreEntry) {
        String sql = "INSERT INTO PLAYERSCORE ( USERNAME,SCORE ) VALUES(" + "'" + hiscoreEntry.getPlayerName() + "'," + "'" + hiscoreEntry.getScore() + "'" + ")";
        executeSQL(sql);
    }

    public void delete(HiscoreEntry hiscoreEntry) {
        String sql = "DELETE FROM PLAYERSCORE WHERE USERNAME = " + "'" + hiscoreEntry.getPlayerName() + "'";
        executeSQL(sql);
        System.out.println("Deleted Highscore from " + hiscoreEntry.getPlayerName());
    }

    public void deleteAll() {
        String sql = "DELETE FROM PLAYERSCORE";
        executeSQL(sql);
        System.out.println("Deleted all Highscores");
    }


    public List<HiscoreEntry> getAllHighscores() {
        List<HiscoreEntry> highscores;
        ResultSet res;

        String sql = "SELECT USERNAME, SCORE FROM PLAYERSCORE";
        res = executeSQLwithResult(sql);
        highscores = new ArrayList<>();
        try {
            while (res.next()) {
                HiscoreEntry h = new HiscoreEntry();

                h.setPlayerName(res.getString("USERNAME"));
                h.setScore(res.getInt("SCORE"));
                highscores.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return highscores;
    }

    public Integer getHighscore(HiscoreEntry hiscoreEntry) {
        ResultSet res;
        Integer score = null;

        String sql = "SELECT SCORE FROM PLAYERSCORE WHERE USERNAME = " + "'" + hiscoreEntry.getPlayerName() + "'";
        res = executeSQLwithResult(sql);
        try {
            while (res.next()) {
                score = res.getInt("SCORE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }

    public boolean ifDBExists() {
        boolean exists;

        File file = new File("PLAYERSCORE.db");

        if (file.exists()) //here's how to check
        {
            System.out.println("This database already exists");
            exists = true;
        } else {
            exists = false;
        }

        return exists;
    }


    public void createDB() {
        Connection con = DBConnection.getInstance().getDBConnection();


    }

    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS PLAYERSCORE (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "USERNAME       TEXT    NOT NULL UNIQUE, " +
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


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private void executeSQL(String sql) {
        Connection con = DBConnection.getInstance().getDBConnection();
        Statement stmt;
        try {
            con.setAutoCommit(false);

            stmt = con.createStatement();
            stmt.executeUpdate(sql);

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}