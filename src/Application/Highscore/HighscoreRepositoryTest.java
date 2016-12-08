package Application.Highscore;

import application.network.protocol.HiscoreEntry;

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