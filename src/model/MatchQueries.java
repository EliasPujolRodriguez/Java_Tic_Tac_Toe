package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MatchQueries {

    DatabaseConnection dbConn = new DatabaseConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean option = false;

    public boolean scoreRegistration(String playerName, int score, String date, String time) {
        try {
            //método que sirve para agregar registro de las nuevas puntuaciones de los jugadores
            dbConn.getConnection();

            String query = "INSERT INTO score_table (player_name, score, score_date, score_time) VALUES(?,?,?,?)";
            ps = dbConn.conn.prepareStatement(query);
            ps.setString(1, playerName);
            ps.setInt(2, score);
            ps.setString(3, date);
            ps.setString(4, time);

            ps.execute();
            option = true;

            ps.close();
            dbConn.closeConnection();

        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        return option;
    }

    public ArrayList<String> getPlayerName() throws SQLException { //Método que va a permitir obtener el nombre del jugador a buscar
        ArrayList<String> playersList = new ArrayList<>();
        String playerName = "";
        dbConn.getConnection();
        String query = "SELECT player_name from score_table ORDER BY score DESC";
        ps = dbConn.conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            playersList.add(rs.getString("player_name"));
        }

        ps.close();
        dbConn.closeConnection();
        return playersList;
    }

    public ArrayList<String> getMatchScore() throws SQLException { //Método que va a permitir obtener la puntuación de la partida
        ArrayList<String> playersScore = new ArrayList<>();
        dbConn.getConnection();
        String query = "SELECT score from score_table ORDER BY score DESC";
        ps = dbConn.conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            playersScore.add(rs.getString("score"));
        }

        ps.close();
        dbConn.closeConnection();
        return playersScore;
    }

    public ArrayList<String> getMatchScoreDate() throws SQLException { //Método que va a permitir obtener la puntuación de la partida
        ArrayList<String> playerScoreDate = new ArrayList<>();
        dbConn.getConnection();
        String query = "SELECT score_date from score_table ORDER BY score DESC";
        ps = dbConn.conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            playerScoreDate.add(rs.getString("score_date"));
        }

        ps.close();
        dbConn.closeConnection();
        return playerScoreDate;
    }

    public ArrayList<String> getMatchScoreTime() throws SQLException { //Método que va a permitir obtener la puntuación de la partida
        dbConn.getConnection();
        ArrayList<String> playerScoreDate = new ArrayList<>();
        String query = "SELECT score_time from score_table ORDER BY score DESC";
        ps = dbConn.conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            playerScoreDate.add(rs.getString("score_time"));
        }

        ps.close();
        dbConn.closeConnection();
        return playerScoreDate;
    }
}
