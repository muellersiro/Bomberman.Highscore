package Application.Highscore.tests;
/**
 * Created by siro on 23.11.16.
 */

import Application.Highscore.Highscore;
import Application.Highscore.HighscoreRepository;
import application.network.protocol.HiscoreEntry;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

         HiscoreEntry h = new HiscoreEntry();

        h.setPlayerName("Iseli");
        h.setScore(6);


        HiscoreEntry h1 = new HiscoreEntry();

        h1.setPlayerName("Siro");
        h1.setScore(2);


        Highscore db = new HighscoreRepository();

        db.updatePlayerScore(h);
        db.updatePlayerScore(h1);

        Integer score;

        score = db.readPlayerScore(h1);

        System.out.println("Score von h1: " + score);

        List<HiscoreEntry> highscores;
        highscores = db.readHighscores();


        System.out.println("Highscores:");
        for (HiscoreEntry highscore : highscores) {
            System.out.println(highscore.getPlayerName() + " | " + highscore.getScore());
        }

        //db.deleteAllHighscore();
        //db.deleteHighscore(h1);
    }
}
