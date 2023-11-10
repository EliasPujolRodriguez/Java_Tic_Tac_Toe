package tic_tac_toe;

import com.formdev.flatlaf.FlatIntelliJLaf;
import controller.MainController;
import javax.swing.UIManager;
import view.AboutView;
import view.ChangePlayerNameView;
import view.DrawView;
import view.MainGame;
import view.MainView;
import view.PlayerNameView;
import view.ScoreView;
import view.SelectGameTypeView;
import view.WinnerView;

public class Tic_Tac_Toe {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            MainView mainView = new MainView();
            SelectGameTypeView selectGame = new SelectGameTypeView();
            MainGame mainGame = new MainGame();
            AboutView aboutView = new AboutView();
            PlayerNameView playerNameView = new PlayerNameView();
            WinnerView winnerView = new WinnerView();
            ChangePlayerNameView updPlayerNameView = new ChangePlayerNameView();
            ScoreView scoreView = new ScoreView();
            DrawView drawView = new DrawView();
            playerNameView.setVisible(true);
            MainController controller = new MainController(mainView, selectGame, mainGame, aboutView, playerNameView, winnerView, updPlayerNameView, scoreView, drawView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
