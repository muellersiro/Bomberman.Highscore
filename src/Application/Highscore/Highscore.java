package Application.Highscore;

/**
 * Created by siro on 20.11.16.
 */
public abstract class Highscore {


    public abstract boolean updatePlayerScore(String playername, Integer score);
    public abstract Integer readPlayerScore(String playername);


}

