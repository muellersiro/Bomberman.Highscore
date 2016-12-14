package Application.Highscore.tests;

import Application.Highscore.DBConnection;
import Application.Highscore.Highscore;
import Application.Highscore.HighscoreRepository;
import application.network.protocol.HiscoreEntry;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

/**
 * Created by siro on 14.12.16.
 */
public class SqliteUtilTest {
    @Test
    public void update() throws Exception {

        //Run
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("Siro");
        h1.setScore(15);

        Highscore db = new HighscoreRepository();

        db.updatePlayerScore(h1);


        //Check
        ResultSet res = null;
        int score = 0;
        Statement stmt;

        String sql = "SELECT SCORE FROM PLAYERSCORE WHERE USERNAME = " + "'" + h1.getPlayerName() + "'";

        Connection con = DBConnection.getInstance().getDBConnection();


        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                score = res.getInt("SCORE");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Test
        assertEquals(h1.getScore(), score);
    }

    @Test
    public void insert() throws Exception {
        //Run
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("INSERTTEST");
        h1.setScore(55);

        Highscore db = new HighscoreRepository();

        db.insertPlayer(h1);

        //Check
        ResultSet res = null;
        int score = -1;
        String username = null;
        Statement stmt;

        String sql = "SELECT SCORE, USERNAME FROM PLAYERSCORE WHERE USERNAME = " + "'" + h1.getPlayerName() + "'";

        Connection con = DBConnection.getInstance().getDBConnection();


        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                score = res.getInt("SCORE");
                username = res.getString("USERNAME");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Test
        assertEquals(h1.getScore(), score);
        assertEquals(h1.getPlayerName(), username);

    }

    @Test
    public void delete() throws Exception {
        //Prepare
        // 1. Run Insert-Test

        //Run
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("INSERTTEST");
        h1.setScore(55);

        Highscore db = new HighscoreRepository();

        db.deleteHighscore(h1);

        //Check
        ResultSet res = null;
        String username = null;
        Statement stmt;
        String sql = "SELECT USERNAME FROM PLAYERSCORE WHERE USERNAME = " + "'" + h1.getPlayerName() + "'";

        Connection con = DBConnection.getInstance().getDBConnection();


        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                username = res.getString("USERNAME");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Test
        assertEquals(null, username);


    }

    @Test
    public void deleteAll() throws Exception {
        //Prepare
        // 1. Run Insert-Test

        //Run
        Highscore db = new HighscoreRepository();

        db.deleteAllHighscore();

        //Check
        ResultSet res = null;
        String username = null;
        Statement stmt;
        String sql = "SELECT USERNAME FROM PLAYERSCORE";

        Connection con = DBConnection.getInstance().getDBConnection();


        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                username = res.getString("USERNAME");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Test
        assertEquals(null, username);
    }


    @Test
    public void getHighscore() throws Exception {

        //Run
        Highscore db = new HighscoreRepository();
        HiscoreEntry h1 = new HiscoreEntry();
        int scoreExpected;

        h1.setPlayerName("TestGetHighscore");
        h1.setScore(3);

        scoreExpected = db.readPlayerScore(h1);

        //Check
        ResultSet res = null;
        int score = -1;
        Statement stmt;
        String sql = "SELECT SCORE FROM PLAYERSCORE WHERE USERNAME = " + "'" + h1.getPlayerName() + "'";

        Connection con = DBConnection.getInstance().getDBConnection();

        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                score = res.getInt("SCORE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(scoreExpected, score);
    }

    @Test
    public void ifDBExists() throws Exception {

    }

    @Test
    public void createDB() throws Exception {

    }

    @Test
    public void createTable() throws Exception {

    }

}