package Application.Highscore;

import application.network.protocol.HiscoreEntry;

import java.util.List;

/**
 * Created by siro on 20.11.16.
 */
public abstract class Highscore {

    public abstract void updatePlayerScore(HiscoreEntry Highscore);

    public abstract int readPlayerScore(HiscoreEntry Highscore);

    public abstract boolean ifPlayerExists(HiscoreEntry Highscore);

    public abstract void insertPlayer(HiscoreEntry Highscore);

    public abstract void deleteHighscore(HiscoreEntry Highscore);

    public abstract void deleteAllHighscore();

    public abstract List<HiscoreEntry> readHighscores();


}

