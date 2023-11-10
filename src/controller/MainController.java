package controller;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.MatchQueries;
import model.Player;
import view.AboutView;
import view.ChangePlayerNameView;
import view.DrawView;
import view.MainGame;
import view.MainView;
import view.PlayerNameView;
import view.ScoreView;
import view.SelectGameTypeView;
import view.WinnerView;

public class MainController implements ActionListener, MouseListener {

    MainView mainView = new MainView();
    SelectGameTypeView selectGame = new SelectGameTypeView();
    MainGame mainGame = new MainGame();
    AboutView aboutView = new AboutView();
    PlayerNameView playerNameView = new PlayerNameView();
    WinnerView winnerView = new WinnerView();
    ChangePlayerNameView updPlayerNameView = new ChangePlayerNameView();
    ScoreView scoreView = new ScoreView();
    String gameType,
            gameLevel,
            optionSelected, playerShift, player2Shift, pcShift, vod;
    DrawView drawView = new DrawView();
    int pos;
    String matrix[][] = {{"0,0", "0,1", "0,2"}, {"1,0", "1,1", "1,2"}, {"2,0", "2,1", "2,2"}}; //Matriz asignada con sus respectivas coordenadas que serán usadas para asignación de las fichas
    String[] numPos = {"0,0", "0,1", "0,2", "1,0", "1,1", "1,2", "2,0", "2,1", "2,2"}; //Array de números aleatorios que serán ingresados y que van a representar una posición en específico
    ArrayList<Integer> usedElements = new ArrayList<Integer>(); //Aray que contiene elementos usados
    ArrayList<String> availableElements = new ArrayList<String>(); //Aray que contiene elementos que aún no han sido usados pero que pueden ser utilizados para ser reaisgnados el valor del número aleatorio
    public Timer time;
    Player player = new Player();
    int pcScore = 0;
    int score = 0,
            levelAssigned = 1;
    MatchQueries matchQuery = new MatchQueries();
    DefaultTableModel scoreModel = (DefaultTableModel) scoreView.scoreTable.getModel();

    public MainController(MainView mainView, SelectGameTypeView selectGame, MainGame mainGame, AboutView aboutView, PlayerNameView playerNameView, WinnerView winnerView, ChangePlayerNameView updPlayerNameView, ScoreView scoreView, DrawView drawView) {
        this.mainView = mainView;
        this.selectGame = selectGame;
        this.mainGame = mainGame;
        this.aboutView = aboutView;
        this.mainView.playBtn.addMouseListener(this);
        this.mainView.btnInfo.addMouseListener(this);
        this.playerNameView = playerNameView;
        this.winnerView = winnerView;
        this.scoreView = scoreView;
        this.drawView = drawView;
        this.updPlayerNameView = updPlayerNameView;
        this.selectGame.btnReturnMainMenu.addMouseListener(this);
        this.selectGame.btnPlayAgainstPC.addMouseListener(this);
        this.selectGame.btn1.addMouseListener(this);
        this.selectGame.btn2.addMouseListener(this);
        this.selectGame.btn3.addMouseListener(this);
        this.selectGame.btn4.addMouseListener(this);
        this.mainGame.a1.addMouseListener(this);
        this.mainGame.a2.addMouseListener(this);
        this.mainGame.a3.addMouseListener(this);
        this.mainGame.a4.addMouseListener(this);
        this.mainGame.a5.addMouseListener(this);
        this.mainGame.a6.addMouseListener(this);
        this.mainGame.a7.addMouseListener(this);
        this.mainGame.a8.addMouseListener(this);
        this.mainGame.a9.addMouseListener(this);
        this.selectGame.btnOptionO.addMouseListener(this);
        this.selectGame.btnOptionX.addMouseListener(this);
        this.mainGame.btnReturnMainMenu.addMouseListener(this);
        this.aboutView.btnReturnMainMenu.addMouseListener(this);
        this.playerNameView.btnExitGame.addMouseListener(this);
        this.playerNameView.btnContinue.addMouseListener(this);
        this.mainView.btnScore.addMouseListener(this);
        this.mainView.btnSettings.addMouseListener(this);
        this.mainView.btnExitGame.addMouseListener(this);
        this.winnerView.btnContinue.addMouseListener(this);
        this.winnerView.btnRestartGame.addMouseListener(this);
        this.updPlayerNameView.btnContinue.addMouseListener(this);
        this.updPlayerNameView.btnExitGame.addMouseListener(this);
        this.scoreView.btnReturnMainMenu.addMouseListener(this);
        this.drawView.btnContinue.addMouseListener(this);
        this.drawView.btnRestartGame.addMouseListener(this);
        assignAvlElements();
        setToolTip();
        showPlayerName();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void mouseClicked(MouseEvent me) { //Asignación de eventos correspondientes a las acciones que se irán presentando conforme se vayan seleccionando los elementos
        if (me.getSource().equals(this.mainView.playBtn)) {
            this.mainView.setVisible(false);
            selectGame.setVisible(true);
        }

        if (me.getSource().equals(selectGame.btnReturnMainMenu)) {
            this.selectGame.setVisible(false);
            this.mainView.setVisible(true);
        }

        if (me.getSource().equals(mainView.btnInfo)) {
            this.aboutView.setVisible(true);
            this.mainView.setVisible(false);
        }

        if (me.getSource().equals(aboutView.btnReturnMainMenu)) {
            this.aboutView.setVisible(false);
            this.mainView.setVisible(true);
        }

        if (me.getSource().equals(this.selectGame.btnPlayAgainstPC)) {
            gameType = "PC";
        }

        if (me.getSource().equals(this.selectGame.btn1)) {
            gameLevel = "1";
        }

        if (me.getSource().equals(this.selectGame.btn2)) {
            gameLevel = "2";
        }

        if (me.getSource().equals(this.selectGame.btn3)) {
            gameLevel = "3";
        }

        if (me.getSource().equals(this.selectGame.btn4)) {
            gameLevel = "4";
        }

        if (me.getSource().equals(this.selectGame.btnOptionO)) {
            optionSelected = "O";
        }

        if (me.getSource().equals(this.selectGame.btnOptionX)) {
            optionSelected = "X";
        }

        if (me.getSource().equals(mainGame.btnReturnMainMenu)) {
            mainGame.setVisible(false);
            mainView.setVisible(true);
            gameType = null;
            gameLevel = null;
            optionSelected = null;
        }

        if (me.getSource().equals(playerNameView.btnContinue)) {
            startGame();
        }

        if (me.getSource().equals(scoreView.btnReturnMainMenu)) {
            this.scoreView.setVisible(false);
            this.mainView.setVisible(true);
        }

        if (mainView.isActive()) { //Si la interfaz de juego principal donde se encuentra el menú principal está activa se va a motrar el nombre del jugador 
            mainView.playerName.setText("Welcome " + player.getPlayer());
        }

        if (playerNameView.isActive()) {  //Mientras la interfaz donde se ingresa el nombre del jugador esté activa, se va limpiar el campo de texto donde se ingresa el nombre del jugador
            playerNameView.playerName.setText("");
        }

        if (me.getSource().equals(playerNameView.btnExitGame)) {
            System.exit(0);
        }

        if (me.getSource().equals(mainView.btnExitGame)) {
            System.exit(0);
        }

        if (me.getSource().equals(this.winnerView.btnRestartGame)) {
            clearGameBoard();
            winnerView.setVisible(false);
        }

        if (me.getSource().equals(this.drawView.btnRestartGame)) {
            clearGameBoard();
            drawView.setVisible(false);
        }

        if (me.getSource().equals(this.mainGame.btnReturnMainMenu)) {
            clearGameBoard();
            gameType = null;
            gameLevel = null;
            optionSelected = null;
            clearGameBoard();
            gameLevel = "";
            player.setPcScore(0);
            player.setScorePlayer1(0);
        }

        if (me.getSource().equals(mainView.btnSettings)) {
            mainView.setVisible(false);
            updPlayerNameView.setVisible(true);
            updPlayerNameView.updPlayerName.setText(player.getPlayer());
        }

        if (me.getSource().equals(updPlayerNameView.btnContinue)) {
            player.setPlayer(updPlayerNameView.updPlayerName.getText());
            updPlayerNameView.setVisible(false);
            mainView.setVisible(true);
            mainView.playerName.setText("Welcome " + player.getPlayer());
        }

        if (me.getSource().equals(this.updPlayerNameView.btnExitGame)) {
            updPlayerNameView.setVisible(false);
            mainView.setVisible(true);
        }

        try {
            selectedGame(me);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (me.getSource().equals(this.mainView.btnScore)) {
            scoreView.setVisible(true);
            mainView.setVisible(false);
            try {
                showProductTableByProdKey();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setToolTip() { //Método que va a mostrar el tooltip de cada botón 
        this.mainView.playBtn.setToolTipText("Start new Game");
        this.mainView.btnInfo.setToolTipText("About game");
        this.mainView.btnScore.setToolTipText("Show score game");
        this.mainView.btnSettings.setToolTipText("Game settings");
        this.selectGame.btnPlayAgainstPC.setToolTipText("Play against pc");
        this.selectGame.btnReturnMainMenu.setToolTipText("Return main menu");
        this.aboutView.btnReturnMainMenu.setToolTipText("Return main menu");
    }

    public void selectedGame(MouseEvent evt) throws InterruptedException { //Método que va a mostrar que tipo de juego fue seleccionado
        if (gameType != null && gameLevel != null && optionSelected != null) {
            this.selectGame.setVisible(false);
            this.mainGame.setVisible(true);

            if (gameType.equals("player")) {
                mainGame.playerName.setText("Jugador 1");
                mainGame.playerName2.setText("Jugador 2");
            }

            if (gameType.equals("PC")) {
                mainGame.playerName.setText("Player 1 " + player.getPlayer());
                mainGame.playerName2.setText("PC");
            }

            if (optionSelected.equals("O")) {
                playerShift = "Turno jugador O";
                player2Shift = "Turno jugador 2 X";
                if (evt.getSource().equals(mainGame.a1)) {
                    mainGame.a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[0][0] = "O";
                    pos = 0;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a1.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a2)) {
                    mainGame.a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[0][1] = "O";
                    pos = 1;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a2.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a3)) {
                    mainGame.a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[0][2] = "O";
                    pos = 2;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a3.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a4)) {
                    mainGame.a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[1][0] = "O";
                    pos = 3;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a4.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a5)) {
                    mainGame.a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[1][1] = "O";
                    pos = 4;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a5.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a6)) {
                    mainGame.a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[1][2] = "O";
                    pos = 5;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a6.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a7)) {
                    mainGame.a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[2][0] = "O";
                    pos = 6;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a7.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a8)) {
                    mainGame.a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[2][1] = "O";
                    pos = 7;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a8.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a9)) {
                    mainGame.a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
                    matrix[2][2] = "O";
                    pos = 8;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a9.setEnabled(false);
                }
            } else if (optionSelected.equals("X")) {
                playerShift = "Turno jugador X";
                if (evt.getSource().equals(mainGame.a1)) {
                    mainGame.a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[0][0] = "X";
                    pos = 0;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a1.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a2)) {
                    mainGame.a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[0][1] = "X";
                    pos = 1;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a2.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a3)) {
                    mainGame.a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[0][2] = "X";
                    pos = 2;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a3.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a4)) {
                    mainGame.a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[1][0] = "X";
                    pos = 3;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a4.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a5)) {
                    mainGame.a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[1][1] = "X";
                    pos = 4;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a5.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a6)) {
                    mainGame.a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[1][2] = "X";
                    pos = 5;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a6.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a7)) {
                    mainGame.a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[2][0] = "X";
                    pos = 6;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a7.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a8)) {
                    mainGame.a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[2][1] = "X";
                    pos = 7;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a8.setEnabled(false);
                }

                if (evt.getSource().equals(mainGame.a9)) {
                    mainGame.a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                    matrix[2][2] = "X";
                    pos = 8;
                    usedElements.add(pos);
                    availableElements.remove(String.valueOf(pos));
                    mainGame.a9.setEnabled(false);
                }
            }

            if (gameType.equals("PC")) {
                if (playerShift.equals("Turno jugador O")) {
                    pcShift = "Turno PC X";
                } else if (playerShift.equals("Turno jugador X")) {
                    pcShift = "Turno PC O";
                }
                mainGame.shift1.setText(playerShift);
                mainGame.shift2.setText(pcShift);
            } else {
                if (this.gameType.equals("player")) {
                    if (playerShift.equals("Turno jugador O")) {
                        player2Shift = "Turno jugador 2 X";
                    } else if (playerShift.equals("Turno jugador X")) {
                        player2Shift = "Turno jugador 2 O";
                    }
                    mainGame.shift1.setText(playerShift);
                    mainGame.shift2.setText(player2Shift);
                }
            }

            if (gameLevel.equals("1") && evt.getSource().equals(this.winnerView.btnContinue)) {
                winnerView.setVisible(false);
                selectGame.setVisible(true);
                mainGame.setVisible(false);
                clearGameBoard();
                player.setPcScore(0);
                player.setScorePlayer1(0);
                gameLevel = "";
                matchQuery.scoreRegistration(player.getPlayer(), player.getScorePlayer1(), getCurrentDate(), getCurrentTime());
            } else if (!gameLevel.equals("1") && evt.getSource().equals(this.winnerView.btnContinue)) {
                winnerView.setVisible(false);
                mainGame.setVisible(true);
                clearGameBoard();
                assignAvlElements();
                levelAssigned++;
            }

            if (gameLevel.equals("1") && evt.getSource().equals(this.drawView.btnContinue)) {
                drawView.setVisible(false);
                selectGame.setVisible(true);
                mainGame.setVisible(false);
                clearGameBoard();
                player.setPcScore(0);
                player.setScorePlayer1(0);
                gameLevel = "";
            } else if (!gameLevel.equals("1") && evt.getSource().equals(this.drawView.btnContinue)) {
                drawView.setVisible(false);
                mainGame.setVisible(true);
                clearGameBoard();
                assignAvlElements();
                levelAssigned++;
            }

            if (gameLevel.equals("2") && evt.getSource().equals(this.winnerView.btnContinue) && this.levelAssigned == 3 || gameLevel.equals("3") && evt.getSource().equals(this.winnerView.btnContinue) && this.levelAssigned == 4 || gameLevel.equals("4") && evt.getSource().equals(this.winnerView.btnContinue) && this.levelAssigned == 5) {
                selectGame.setVisible(true);
                mainGame.setVisible(false);
                winnerView.setVisible(false);
                clearGameBoard();
                gameLevel = "";
                player.setPcScore(0);
                player.setScorePlayer1(0);
                matchQuery.scoreRegistration(player.getPlayer(), player.getScorePlayer1(), getCurrentDate(), getCurrentTime());
            } else {
                if (gameLevel.equals("")) {
                    mainGame.setVisible(false); //Se va a forzar a ocultar la respectiva pantalla
                }
            }
        }

        System.out.println(gameLevel);
        mainGame.levelNumber.setText(String.valueOf("Level " + levelAssigned));

        if (mainGame.isActive() && gameType.equals("PC")) {
            time = new Timer(1200, null);
            time.start();
            time.addActionListener((ActionEvent ae) -> { //Temporizador para simular cuando la maquina va a tirar
                try {
                    getRandomArrayElement(pos, playerShift, pcShift);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                time.stop();
                showWinner(playerShift, pcShift);
            });
        }
    }

    public void showWinner(String playerShift, String pcShift) { //Método que va a mostrar al jugador gaandor de la partida
        //System.out.println(vod);
        if (matrix[0][0].equals("O") && matrix[1][1].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[0][0].equals("O") && matrix[1][1].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][0].equals("O") && matrix[1][0].equals("O") && matrix[2][0].equals("O") && playerShift.equals("Turno jugador O") || matrix[0][0].equals("O") && matrix[1][0].equals("O") && matrix[2][0].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[0][0].equals("O") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][1].equals("O") && matrix[1][1].equals("O") && matrix[2][1].equals("O") && playerShift.equals("Turno jugador O") || matrix[0][1].equals("O") && matrix[1][1].equals("O") && matrix[2][1].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][2].equals("O") && matrix[1][2].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[0][2].equals("O") && matrix[1][2].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[2][0].equals("O") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[2][0].equals("O") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[2][0].equals("O") && matrix[1][1].equals("O") && matrix[0][2].equals("O") && playerShift.equals("Turno jugador O") || matrix[2][0].equals("O") && matrix[1][1].equals("O") && matrix[0][2].equals("O") && playerShift.equals("Turno jugador O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        //X
        if (matrix[0][0].equals("X") && matrix[1][1].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[0][0].equals("X") && matrix[1][1].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][0].equals("X") && matrix[1][0].equals("X") && matrix[2][0].equals("X") && playerShift.equals("Turno jugador X") || matrix[0][0].equals("X") && matrix[1][0].equals("X") && matrix[2][0].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][1].equals("X") && matrix[1][1].equals("X") && matrix[2][1].equals("X") && playerShift.equals("Turno jugador X") || matrix[0][1].equals("X") && matrix[1][1].equals("X") && matrix[2][1].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[0][2].equals("X") && matrix[1][2].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[0][2].equals("X") && matrix[1][2].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            score++;
            player.setScorePlayer1(score);
        }

        if (matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            player.setScorePlayer1(score++);
        }

        if (matrix[2][0].equals("X") && matrix[1][1].equals("X") && matrix[0][2].equals("X") && playerShift.equals("Turno jugador X") || matrix[2][0].equals("X") && matrix[1][1].equals("X") && matrix[0][2].equals("X") && playerShift.equals("Turno jugador X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            player.setScorePlayer1(score++);
        }

        //PC
        if (matrix[0][0].equals("O") && matrix[1][1].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") || matrix[0][0].equals("O") && matrix[1][1].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][0].equals("O") && matrix[1][0].equals("O") && matrix[2][0].equals("O") && pcShift.equals("Turno PC O") || matrix[0][0].equals("O") && matrix[1][0].equals("O") && matrix[2][0].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && pcShift.equals("Turno PC O") || matrix[0][0].equals("O") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][1].equals("O") && matrix[1][1].equals("O") && matrix[2][1].equals("O") && pcShift.equals("Turno PC O") || matrix[0][1].equals("O") && matrix[1][1].equals("O") && matrix[2][1].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][2].equals("O") && matrix[1][2].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") || matrix[0][2].equals("O") && matrix[1][2].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && pcShift.equals("Turno PC O") || matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[2][0].equals("O") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") || matrix[2][0].equals("O") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[2][0].equals("O") && matrix[1][1].equals("O") && matrix[0][2].equals("O") && pcShift.equals("Turno PC O") || matrix[2][0].equals("O") && matrix[1][1].equals("O") && matrix[0][2].equals("O") && pcShift.equals("Turno PC O") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        //PC X
        if (matrix[0][0].equals("X") && matrix[1][1].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") || matrix[0][0].equals("X") && matrix[1][1].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][0].equals("X") && matrix[1][0].equals("X") && matrix[2][0].equals("X") && pcShift.equals("Turno PC X") || matrix[0][0].equals("X") && matrix[1][0].equals("X") && matrix[2][0].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("X") && pcShift.equals("Turno PC X") || matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][1].equals("X") && matrix[1][1].equals("X") && matrix[2][1].equals("X") && pcShift.equals("Turno PC X") || matrix[0][1].equals("X") && matrix[1][1].equals("X") && matrix[2][1].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[0][2].equals("X") && matrix[1][2].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") || matrix[0][2].equals("X") && matrix[1][2].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && pcShift.equals("Turno PC X") || matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") || matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        if (matrix[2][0].equals("X") && matrix[1][1].equals("X") && matrix[0][2].equals("X") && pcShift.equals("Turno PC X") || matrix[2][0].equals("X") && matrix[1][1].equals("X") && matrix[0][2].equals("X") && pcShift.equals("Turno PC X") && vod.equals("Sí")) {
            mainGame.setVisible(false);
            winnerView.setVisible(true);
            winnerView.winnerSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross_winner.png")));
            pcScore++;
            player.setPcScore(pcScore);
        }

        //Empates
        if (matrix[0][0].equals("X") && matrix[0][1].equals("O") && matrix[0][2].equals("X") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("O") && matrix[0][2].equals("X") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("O") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("X") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("X") && matrix[1][2].equals("O") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("O") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("O") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("X") && matrix[0][2].equals("X") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("X") && matrix[1][1].equals("O") && matrix[1][2].equals("O") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("X") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("O") && matrix[0][2].equals("X") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("X") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("O") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("O") && matrix[2][1].equals("X") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("O") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("X") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("X") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        if (matrix[0][0].equals("X") && matrix[0][1].equals("X") && matrix[0][2].equals("O") && /*2do*/ matrix[1][0].equals("O") && matrix[1][1].equals("O") && matrix[1][2].equals("X") && /*3ro */ matrix[2][0].equals("X") && matrix[2][1].equals("O") && matrix[2][2].equals("O") && vod.equals("Sí")) {
            player.setPcScore(0);
            pcScore++;
            player.setPcScore(pcScore);
            score++;
            player.setScorePlayer1(score);
            this.mainGame.setVisible(false);
            this.drawView.setVisible(true);
        }

        this.mainGame.playerScore.setText(String.valueOf(player.getScorePlayer1())); //Se va a mostrar la puntuación actual tanto de jugadores como de la pc con la que se está jugando en contra
        this.mainGame.pcScore.setText(String.valueOf(player.getPcScore()));
    }

    public void assignAvlElements() { //Método que va a asignar los elementos que van corresponder a los elementos disponibles del array correspondiente
        for (int i = 0; i <= 8; i++) {
            availableElements.add(String.valueOf(i));
        }
    }

    public void getRandomArrayElement(int element, String shiftValue1, String shiftValue2) throws InterruptedException { //Método que va a obtener la posición del elemento a buscar el cuál no coincida con el elemento obtenido del array
        String x = "", numeroAleatorioString = "";
        int pos = 0, p = 0, r = 0;
        int numeroAleatorio = 0;
        int[] numeros = new Random().ints(0, 8).distinct().limit(1).toArray();
        numeroAleatorio = Integer.parseInt(Arrays.toString(numeros).replaceAll("[\\[\\]]", ""));
        pos = element;

        try {
            if (!usedElements.contains(numeroAleatorio) && numeroAleatorio != pos) {
                x = numPos[numeroAleatorio];
                usedElements.add(numeroAleatorio);
                availableElements.remove(String.valueOf(numeroAleatorio));
            } else {
                if (availableElements.contains(String.valueOf(numeroAleatorio)) || numeroAleatorio == pos || usedElements.contains(numeroAleatorio)) {//r va a ser la posición de ese elemento en available elements indexOf(sElementoaBuscar);
                    r = availableElements.indexOf(String.valueOf(numeroAleatorio));
                    if (r != -1) {
                        x = numPos[r];
                        usedElements.add(r);
                        usedElements.add(pos);
                        availableElements.remove(String.valueOf(pos));
                        availableElements.remove(String.valueOf(r));
                    } else {
                        r = Integer.parseInt(availableElements.get(0));
                        x = numPos[r];
                        usedElements.add(r);
                        usedElements.add(pos);
                        availableElements.remove(String.valueOf(pos));
                        availableElements.remove(String.valueOf(r));
                    }
                }
            }
        } catch (IndexOutOfBoundsException IOUOBE) {
            vod = "Sí";
        }

        if (shiftValue2.equals("Turno PC X")) {
            /*Asignación de posiciones de cada elemento del tablero */
            if (x.equals("0,0")) {
                mainGame.a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
                matrix[0][0] = "X";
            }

            if (x.equals("0,1")) {
                matrix[0][1] = "X";
                mainGame.a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("0,2")) {
                matrix[0][2] = "X";
                mainGame.a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("1,0")) {
                matrix[1][0] = "X";
                mainGame.a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("1,1")) {
                matrix[1][1] = "X";
                mainGame.a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("1,2")) {
                matrix[1][2] = "X";
                mainGame.a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("2,0")) {
                matrix[2][0] = "X";
                mainGame.a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("2,1")) {
                matrix[2][1] = "X";
                mainGame.a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }

            if (x.equals("2,2")) {
                matrix[2][2] = "X";
                mainGame.a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            }
        } else if (shiftValue2.equals("Turno PC O")) {
            if (x.equals("0,0")) {
                matrix[0][0] = "O";
                mainGame.a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("0,1")) {
                matrix[0][1] = "O";
                mainGame.a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("0,2")) {
                matrix[0][2] = "O";
                mainGame.a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("1,0")) {
                matrix[1][0] = "O";
                mainGame.a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("1,1")) {
                matrix[1][1] = "O";
                mainGame.a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("1,2")) {
                matrix[1][2] = "O";
                mainGame.a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("2,0")) {
                matrix[2][0] = "O";
                mainGame.a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("2,1")) {
                matrix[2][1] = "O";
                mainGame.a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }

            if (x.equals("2,2")) {
                matrix[2][2] = "O";
                mainGame.a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/circle.png")));
            }
        }
    }

    public void startGame() { //Método que va a inicializar la partida en el apartado en donde se debe de ingresar el nombre del jugador
        if (playerNameView.playerName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes ingresar tu nombre");
        } else {
            player.setPlayer(playerNameView.playerName.getText());
            mainView.setVisible(true);
            playerNameView.setVisible(false);
            mainView.playerName.setText("Welcome " + player.getPlayer());
        }
    }

    public void clearGameBoard() { //Método que va a limpiar los datos del tablero de juego
        mainGame.a1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[0][0] = "0,0";
        mainGame.a2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[0][1] = "0,1";
        mainGame.a3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[0][2] = "0,2";
        mainGame.a4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[1][0] = "1,0";
        mainGame.a5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[1][1] = "1,1";
        mainGame.a6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[1][2] = "1,2";
        mainGame.a7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[2][0] = "2,0";
        mainGame.a8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[2][1] = "2,1";
        mainGame.a9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset_icon.png")));
        matrix[2][2] = "2,2";
        availableElements.removeAll(availableElements);
        usedElements.removeAll(usedElements);
    }

    public void showPlayerName() {//Método que va a mostrar el nombre del jugador asignado
        if (this.updPlayerNameView.isActive()) {
            updPlayerNameView.updPlayerName.setText(player.getPlayer());
        }
    }

    public String getCurrentDate() { //Método que va a mostrar la fecha actual
        LocalDate myObj = LocalDate.now(ZoneId.of("America/Costa_Rica")); // Create a date object
        return myObj.toString();
    }

    public String getCurrentTime() { //Método que va a mostrar la fecha actual
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
        return df.format(new Date());
    }

    public void showProductTableByProdKey() throws SQLException { //Método que va a mostrar los datos de los productos registrados al buscar mediante la clave del producto
        scoreModel.setNumRows(0);
        Object rowData[] = new Object[4];

        for (int i = 0; i < matchQuery.getMatchScore().size(); i++) {
            rowData[0] = this.matchQuery.getPlayerName().get(i);
            rowData[1] = this.matchQuery.getMatchScore().get(i);
            rowData[2] = this.matchQuery.getMatchScoreDate().get(i);
            rowData[3] = this.matchQuery.getMatchScoreTime().get(i);

            scoreModel.addRow(rowData);
        }

        this.scoreView.scoreTable.setModel(scoreModel);
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
