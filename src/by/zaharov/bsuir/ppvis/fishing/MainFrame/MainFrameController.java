package by.zaharov.bsuir.ppvis.fishing.MainFrame;

import by.zaharov.bsuir.ppvis.fishing.Game.GameController;
import by.zaharov.bsuir.ppvis.fishing.Game.GameFrame;
import by.zaharov.bsuir.ppvis.fishing.Game.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainFrameController {
    private Record record = new Record();
    private JLabel labelScore;
    private JTextField userName = new JTextField("UserName");
    private int levelDifficulty = 0;
    private MainFrame mainFrame;

    MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        mainFrame.buttonStart.addActionListener(new GameStart());
        mainFrame.buttonExit.addActionListener(arg0 -> mainFrame.gameFrame.dispose());
        labelScore = new JLabel("UserName 0");
        mainFrame.panelSouth.add(userName);

        mainFrame.panelSouth.add(labelScore);

        mainFrame.gameFrame.pack();

    }

    private void checkRadioButton() {
        int LOW = 0;
        int MEDIUM = 1;
        int HIGH = 2;
        if (mainFrame.low.isSelected()) {
            levelDifficulty = LOW;
            mainFrame.medium.setSelected(false);
            mainFrame.high.setSelected(false);
        } else if (mainFrame.medium.isSelected()) {

            levelDifficulty = MEDIUM;

            mainFrame.high.setSelected(false);
        } else if (mainFrame.high.isSelected()) {

            levelDifficulty = HIGH;
        }
        if (levelDifficulty == LOW) {
            mainFrame.low.setSelected(true);
        }

    }

    public class GameStartThread extends Thread {
        public void run() {
            int WIDTH = 800;
            int HEIGHT = 600;
            JFrame gameFrame = new JFrame();
            record.setName(userName.getText());

            gameFrame.add(new GameFrame(new GameController(gameFrame, record,
                    labelScore, levelDifficulty)), BorderLayout.CENTER);

            gameFrame.setSize(WIDTH, HEIGHT);
            gameFrame.setVisible(true);
        }
    }

    public class GameStart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventSearch) {
            checkRadioButton();
            new GameStartThread().start();
        }
    }
}
