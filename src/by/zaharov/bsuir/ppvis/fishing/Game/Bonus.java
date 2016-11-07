package by.zaharov.bsuir.ppvis.fishing.Game;

import by.zaharov.bsuir.ppvis.fishing.resourses.ResourseLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Bonus {
    Image bonusImage = null;
    int x = 300;
    int y = 300;

    Bonus() {
        bonusImage = new ImageIcon(ResourseLoader.loaderResourseUrl("bonus.gif")).getImage();
    }

    void movement(int levelDifficulty) {
        Timer myTimer;
        ActionListener TimerWork = new ActionListener() {
            Random randomMovement = new Random();

            @Override
            public void actionPerformed(ActionEvent arg0) {
                x += (randomMovement.nextInt(30) - 15)
                        * (1 + levelDifficulty / 2.0);
                randomMovement = new Random();
                y += (randomMovement.nextInt(30) - 15)
                        * (1 + levelDifficulty / 2.0);
                if (x < 64)
                    x = 64;
                if (y < 64)
                    y = 64;
                if (x > 800 - 64)
                    x = 800 - 64;
                if (y > 500)
                    y = 500;
            }
        };
        myTimer = new Timer(100, TimerWork);
        myTimer.start();
    }
}
