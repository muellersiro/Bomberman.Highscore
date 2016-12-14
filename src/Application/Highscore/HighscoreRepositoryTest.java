package Application.Highscore;

import application.network.protocol.HiscoreEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by siro on 08.12.16.
 */
public class HighscoreRepositoryTest {
    @org.junit.Test
    public void updatePlayerScore() throws Exception {
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("Siro");
        h1.setScore(15);

        Highscore db = new HighscoreRepository();

        db.updatePlayerScore(h1);



        //Überprüfen
        ResultSet res = null;
        int score = 0;

        String sql = "SELECT SCORE FROM PLAYERSCORE WHERE USERNAME = " + "'" + h1.getPlayerName() + "'";

        Connection con = DBConnection.getInstance().getDBConnection();
        Statement stmt;

        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (res.next()) {
                score = res.getInt("SCORE");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        assertEquals(h1.getScore(), score);

    }

    @org.junit.Test
    public void readPlayerScore() throws Exception {
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("Siro");
        Highscore db = new HighscoreRepository();

        Integer expected = 15;

        Integer score;

        score = db.readPlayerScore(h1);

        assertEquals(expected, score);
    }

    @org.junit.Test
    public void readHighscores() throws Exception {
        Highscore db = new HighscoreRepository();
        List<HiscoreEntry> highscores;

        highscores = db.readHighscores();
    }

    @org.junit.Test
    public void deleteHighscore() throws Exception {
        HiscoreEntry h1 = new HiscoreEntry();
        h1.setPlayerName("Siro");
        Highscore db = new HighscoreRepository();

        db.deleteHighscore(h1);


    }

    @org.junit.Test
    public void deleteAllHighscore() throws Exception {

        Highscore db = new HighscoreRepository();
        db.deleteAllHighscore();

    }

}