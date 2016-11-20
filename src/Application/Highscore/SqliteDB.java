package Application.Highscore;

/**
 * Created by siro on 20.11.16.
 */
import java.sql.*;

public class SqliteDB extends Highscore{

    private String dbname = "PLAYERSCORE";

    @Override
    public boolean updatePlayerScore(String playername, Integer score) {

        Connection dbcon;
        Statement stmt;
        Boolean state = false;
        Boolean userExists;

        dbcon = getDBConnection();

        userExists = ifPlayerexists(playername);
        if (userExists){
            try {
                stmt = dbcon.createStatement();
                String sql = "UPDATE "+ this.dbname + " SET SCORE = " + score + "WHERE playername = "+ playername;

                stmt.executeUpdate(sql);

                stmt.close();
                dbcon.commit();
                dbcon.close();

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Record updated successfully");
            state = true;
        }else{
            insertPlayerScore(playername, score);
        }

        return state;
    }

    @Override
    public Integer readPlayerScore(String playername){
        Connection dbcon;
        Statement stmt;
        Integer score = null;

        dbcon = getDBConnection();

        try {

            stmt = dbcon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + this.dbname + " WHERE username = " + playername);

            while (rs.next()) {

            }
            rs.close();
            stmt.close();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("ifUserexists Operation done successfully");


        return score;
    }

    private boolean ifPlayerexists(String playername){
        Connection dbcon;
        Statement stmt;
        Boolean state = false;

        dbcon = getDBConnection();

        try {

            stmt = dbcon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + this.dbname + " WHERE username = " + playername);

            while (rs.next()) {
                state = true;
            }
            rs.close();
            stmt.close();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("ifUserexists Operation done successfully");


        return state;
    }

    private boolean insertPlayerScore(String playername, Integer score){
        Connection dbcon;
        Statement stmt;
        Boolean state = false;

        dbcon = getDBConnection();

        try {
            stmt = dbcon.createStatement();
            String sql = "INSERT INTO "+ this.dbname + " (USERNAME,SCORE) " +
                    "VALUES ( " + playername + ","+ score +")";
            stmt.executeUpdate(sql);

            stmt.close();
            dbcon.commit();
            dbcon.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Record created successfully");
        state = true;

        return state;
    }


    private Connection getDBConnection() {
        Connection dbcon = null;
        try {
            Class.forName("org.sqlite.JDBC");
            dbcon = DriverManager.getConnection("jdbc:sqlite:"+this.dbname+".db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database" + this.dbname + "successfully");
        return dbcon;
    }
}

