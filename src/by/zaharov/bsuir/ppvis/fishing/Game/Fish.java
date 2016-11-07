package by.zaharov.bsuir.ppvis.fishing.Game;

import by.zaharov.bsuir.ppvis.fishing.resourses.ResourseLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Fish {
    Image fishImage = null;
    int x = 700;
    int y = 300;
    boolean kindness = true;
    boolean bonusState = false;
    boolean bonusProtection = false;

    Fish(boolean kindness) {

        if (kindness)
            fishImage = new ImageIcon(ResourseLoader.loaderResourseUrl("fish.gif")).getImage();
        else
            fishImage = new ImageIcon(ResourseLoader.loaderResourseUrl("fish5.gif")).getImage();

    }

    private void movement() {

        ActionListener TimerWork = new ActionListener() {
            Random randomMovement = new Random();

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!bonusState) {
                    x += (randomMovement.nextInt(30) - 15);
                    randomMovement = new Random();
                    y += (randomMovement.nextInt(30) - 15);
                    if (x < 64)
                        x = 64;
                    if (y < 64)
                        y = 64;
                    if (x > 800 - 64)
                        x = 800 - 64;
                    if (y > 500)
                        y = 500;

                }
            }

        };
        Timer myTimer = new Timer(100, TimerWork);
        myTimer.start();
    }

    void stop() {
        bonusState = true;
        new FishSleepThread().start();
    }

    void secretMovement(int hookX, int hookY, int levelDifficulty) {
        if (hookX - x < 100 && hookX - x > -100)
            if (hookY - y < 100 && hookY - y > -100)
                if (!bonusState) {
                    if (!kindness) {
                        if (hookX > x) {
                            x += 3 + levelDifficulty;
                        } else {
                            x -= 3 + levelDifficulty;
                        }
                        if (hookY > y) {
                            y += 3 + levelDifficulty;
                        } else {
                            y -= 3 + levelDifficulty;
                        }
                    } else {
                        if (hookX > x) {
                            x -= 3 + levelDifficulty;
                        } else {
                            x += 3 + levelDifficulty;
                        }
                        if (hookY > y) {
                            y -= 3 + levelDifficulty;
                        } else {
                            y += 3 + levelDifficulty;
                        }
                    }
                }
    }

    void start() {
        new FishStartThread().start();
    }

    public class FishSleepThread extends Thread {
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
            if (!bonusProtection)
                bonusState = false;
        }
    }

    public class FishStartThread extends Thread {
        public void run() {

            try {

                for (int movementCounter = 0; movementCounter < 30; movementCounter++) {
                    x -= 10;
                    sleep(50);
                }
            } catch (InterruptedException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }

            if (x == 400) {

                movement();

            }
        }
    }
}
