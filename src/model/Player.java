package model;

public class Player {
    private String player;
    private String player2;
    int player1Score;
    int player2Score;
    int pcScore;

    public Player() {

    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getScorePlayer1() {
        return player1Score;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.player1Score = scorePlayer1;
    }

    public int getScorePlayer2() {
        return player2Score;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.player2Score = scorePlayer2;
    }

    public int getPcScore() {
        return pcScore;
    }

    public void setPcScore(int pcScore) {
        this.pcScore = pcScore;
    }

}
