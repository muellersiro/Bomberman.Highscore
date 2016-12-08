package Application.Highscore;

import application.network.protocol.HiscoreEntry;

import java.util.List;

/**
 * Created by siro on 07.12.16.
 */


public class HighscoreRepository extends Highscore {

    private SqliteUtil sqliteUtil = new SqliteUtil();


    public HighscoreRepository() {
        boolean exists;

        exists  = sqliteUtil.ifDBExists();

        if (exists == false){
            sqliteUtil.createDB();
        }
        sqliteUtil.createTable();
    }

    @Override
    public void updatePlayerScore(HiscoreEntry hiscoreEntry) {

        Boolean userExists;

        userExists = ifPlayerExists(hiscoreEntry);
        if (userExists) {
            sqliteUtil.update(hiscoreEntry);
            System.out.println("Record updated successfully");

        } else {
            sqliteUtil.insert(hiscoreEntry);
            System.out.print("Record inserted successfully");
        }

    }

    @Override
    public Integer readPlayerScore(HiscoreEntry hiscoreEntry) {

        Integer score = null;

        score = sqliteUtil.getHighscore(hiscoreEntry);

        return score;
    }


    @Override
    public List<HiscoreEntry> readHighscores() {
        List<HiscoreEntry> list;


        list = sqliteUtil.getAllHighscores();

        return list;
    }

    @Override
    public  void deleteHighscore(HiscoreEntry hiscoreEntry){
        sqliteUtil.delete(hiscoreEntry);
    }

    @Override
    public  void deleteAllHighscore(){
        sqliteUtil.deleteAll();
    }

    private boolean ifPlayerExists(HiscoreEntry hiscoreEntry) {
        Boolean state = false;
        Integer score;

        score = sqliteUtil.getHighscore(hiscoreEntry);

        if (score != null){
            state = true;
            System.out.println("User already exists");
        }


        return state;
    }

}

