package by.zaharov.bsuir.ppvis.fishing.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameController {
    Fish[] fishes = new Fish[7];
    Hook hook;
    JLabel labelScore = new JLabel("0");
    int amountFishes;
    JFrame gameFrame;
    Bonus bonus;
    Scoop scoop = new Scoop();
    private int counterScore = 0;
    private boolean gameOver = false;
    private JLabel hidedLabelScore;
    private Record record;
    private int caughtFish;
    private int levelDifficulty;

    public GameController(JFrame gameFrame, Record record,
                          JLabel hidedLabelScore, int levelDifficulty) {
        this.gameFrame = gameFrame;
        this.record = record;
        this.hidedLabelScore = hidedLabelScore;
        this.levelDifficulty = levelDifficulty;
        hook = new Hook();
        bonus = new Bonus();
        Random randomAmount = new Random();
        amountFishes = randomAmount.nextInt(5) + 2;
        for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
            if (counterFishes % 2 == 1) {
                fishes[counterFishes] = new Fish(true);
                fishes[counterFishes].kindness = true;
            } else {

                fishes[counterFishes] = new Fish(false);
                fishes[counterFishes].kindness = false;
            }
        }
        caughtFish = amountFishes;
        for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++)
            fishes[counterFishes].start();
        bonus.movement(levelDifficulty);
    }

    KeyAdapter getKeyAdapter() {
        return new KeyAdapter();
    }

    private void checkCatching() {

        for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {

            if (hook.x <= fishes[counterFishes].x + 30
                    && hook.x >= fishes[counterFishes].x - 30)
                if (hook.y <= fishes[counterFishes].y + 30
                        && hook.y >= fishes[counterFishes].y - 30) {

                    if (fishes[counterFishes].kindness) {
                        if (caughtFish == amountFishes) {
                            scoop.visible = true;
                            caughtFish = counterFishes;
                            fishes[caughtFish].bonusState = true;
                            fishes[caughtFish].bonusProtection = true;

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Game over",
                                "Attention", JOptionPane.INFORMATION_MESSAGE);

                        gameOver = true;
                    }

                }
        }

        if (gameOver) {
            gameFrame.dispose();
            if (counterScore > record.score) {
                record.score = counterScore;
                hidedLabelScore.setText(record.getName() + " "
                        + Integer.toString(counterScore));
            }
        }
    }

    private void checkFindingBonus() {
        if (caughtFish != amountFishes)
            fishes[caughtFish].bonusState = true;
        if (hook.x <= bonus.x + 30 && hook.x >= bonus.x - 30)
            if (hook.y <= bonus.y + 30 && hook.y >= bonus.y - 30) {
                for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
                    fishes[counterFishes].bonusState = true;
                    fishes[counterFishes].stop();

                }

            }
    }

    private void checkGettingScoop() {
        if (hook.x <= scoop.x + 70 && hook.x >= scoop.x - 70)
            if (hook.y <= scoop.y + 45 && hook.y >= scoop.y - 45) {
                counterScore++;
                hook.x = 100;
                hook.y = 100;
                labelScore.setText(Integer.toString(counterScore));
                scoop.visible = false;
                fishes[caughtFish].bonusState = false;
                fishes[caughtFish].bonusProtection = false;
                fishes[caughtFish].x = 700;
                fishes[caughtFish].y = 300;
                fishes[caughtFish].start();
                caughtFish = amountFishes;
            }
    }

    private void checkBounds() {
        if (hook.x <= 0 || hook.y <= 0 || hook.x >= 800 || hook.y >= 600) {
            JOptionPane.showMessageDialog(null, "Game over", "Attention",
                    JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
        }
    }

    class KeyAdapter implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {

            if (event.getKeyCode() == KeyEvent.VK_D) {
                hook.x += 10;
                if (caughtFish != amountFishes)
                    fishes[caughtFish].x += 10;
                for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
                    if (counterFishes != caughtFish)
                        fishes[counterFishes].secretMovement(hook.x, hook.y,
                                levelDifficulty);
                }
            }

            if (event.getKeyCode() == KeyEvent.VK_A) {
                hook.x -= 10;
                if (caughtFish != amountFishes)
                    fishes[caughtFish].x -= 10;
                for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
                    if (counterFishes != caughtFish)
                        fishes[counterFishes].secretMovement(hook.x, hook.y,
                                levelDifficulty);
                }
            }

            if (event.getKeyCode() == KeyEvent.VK_W) {
                hook.y -= 10;
                if (caughtFish != amountFishes)
                    fishes[caughtFish].y -= 10;
                for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
                    if (counterFishes != caughtFish)
                        fishes[counterFishes].secretMovement(hook.x, hook.y,
                                levelDifficulty);
                }
            }

            if (event.getKeyCode() == KeyEvent.VK_S) {
                hook.y += 10;
                if (caughtFish != amountFishes)
                    fishes[caughtFish].y += 10;
                for (int counterFishes = 0; counterFishes < amountFishes; counterFishes++) {
                    if (counterFishes != caughtFish)
                        fishes[counterFishes].secretMovement(hook.x, hook.y,
                                levelDifficulty);
                }
            }
            checkBounds();
            checkCatching();
            checkFindingBonus();
            if (scoop.visible)
                checkGettingScoop();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }
}
