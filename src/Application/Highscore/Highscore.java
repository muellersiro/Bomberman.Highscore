package Application.Highscore;

import application.network.protocol.HiscoreEntry;

/**
 * Created by siro on 20.11.16.
 */
public abstract class Highscore {


    public abstract boolean updatePlayerScore(String playername, Integer score);
    public abstract Integer readPlayerScore(String playername);

    public abstract boolean updatePlayerScore_new(HiscoreEntry Highscore);
    public abstract Integer readPlayerScore_new(HiscoreEntry Highscore);

}

