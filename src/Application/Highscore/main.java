package Application.Highscore;/**
 * Created by siro on 23.11.16.
 */

import application.network.protocol.HiscoreEntry;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HiscoreEntry h = new HiscoreEntry();

        h.setPlayerName("Iseli");
        h.setScore(2);

        SqliteDB db = new SqliteDB();

        db.updatePlayerScore_new(h);
    }
}
