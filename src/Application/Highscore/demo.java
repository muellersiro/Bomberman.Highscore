package Application.Highscore;

import application.network.protocol.HiscoreEntry;

/**
 * Created by siro on 20.11.16.
 */
public class demo {
    public void updatePlayerScore(Highscore highscore){
        highscore.updatePlayerScore("bob", 2);
    }

    HiscoreEntry h = new HiscoreEntry();


}