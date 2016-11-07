package by.zaharov.bsuir.ppvis.fishing.Game;

import by.zaharov.bsuir.ppvis.fishing.resourses.ResourseLoader;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameFrame extends JPanel {

    private Image imageBackground = null;

    private GameController controller;

    public GameFrame(GameController controller) {
        this.controller = controller;
        JLabel labelText = new JLabel("Your score is ");
        JPanel panelScore = new JPanel();
        panelScore.add(labelText);
        panelScore.add(controller.labelScore);
        controller.gameFrame.add(panelScore, BorderLayout.SOUTH);
        imageBackground = new ImageIcon(ResourseLoader.loaderResourseUrl("back.jpg")).getImage();

        controller.gameFrame.addKeyListener(controller.getKeyAdapter());

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(imageBackground, 0, 0, this);
        graphics.drawImage(controller.hook.hookImage, controller.hook.x,
                controller.hook.y, this);
        graphics.drawImage(controller.bonus.bonusImage, controller.bonus.x,
                controller.bonus.y, this);
        if (controller.scoop.visible)
            graphics.drawImage(controller.scoop.scoopImage, controller.scoop.x,
                    controller.scoop.y, this);
        for (int counterFishes = 0; counterFishes < controller.amountFishes; counterFishes++)
            graphics.drawImage(controller.fishes[counterFishes].fishImage,
                    controller.fishes[counterFishes].x,
                    controller.fishes[counterFishes].y, this);

        repaint();
    }

}
