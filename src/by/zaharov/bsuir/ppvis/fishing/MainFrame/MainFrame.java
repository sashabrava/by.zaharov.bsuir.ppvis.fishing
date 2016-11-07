package by.zaharov.bsuir.ppvis.fishing.MainFrame;

import javax.swing.*;
import java.awt.*;

class MainFrame {
    JFrame gameFrame = new JFrame();
    JButton buttonStart = new JButton("Start");
    JButton buttonExit = new JButton("Exit");
    JPanel panelSouth = new JPanel();
    JRadioButton low = new JRadioButton("Low");
    JRadioButton medium = new JRadioButton("Medium");
    JRadioButton high = new JRadioButton("High");
    private JPanel panelWest = new JPanel();
    private JLabel level = new JLabel("Choose level");
    private ButtonGroup bg = new ButtonGroup();

    MainFrame() {

        JPanel panelNorth = new JPanel();
        gameFrame.add(panelNorth, BorderLayout.NORTH);
        gameFrame.add(panelWest, BorderLayout.WEST);
        gameFrame.add(panelSouth, BorderLayout.SOUTH);
        panelWest.add(buttonStart);
        panelWest.add(buttonExit);
        panelWest.add(level);
        bg.add(low);
        bg.add(medium);
        bg.add(high);
        panelWest.add(low);
        panelWest.add(medium);
        panelWest.add(high);
        gameFrame.pack();
        gameFrame.setVisible(true);

    }

}
