package Application.Highscore;/**
 * Created by siro on 23.11.16.
 */

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

        score =  db.readPlayerScore(h1);

        System.out.println("Score von h1: " +score);

        List<HiscoreEntry> highscores;
        highscores = db.readHighscores();


        System.out.println("Highscores:");
        for (int i = 0; i < highscores.size(); i++) {
            System.out.println(highscores.get(i).getPlayerName() + " | " +highscores.get(i).getScore());
        }

        //db.deleteAllHighscore();
        //db.deleteHighscore(h1);
    }
}
