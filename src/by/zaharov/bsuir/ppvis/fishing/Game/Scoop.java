package by.zaharov.bsuir.ppvis.fishing.Game;

import by.zaharov.bsuir.ppvis.fishing.resourses.ResourseLoader;

import javax.swing.*;
import java.awt.*;

class Scoop {
    Image scoopImage = null;
    int x = 400;
    int y = 450;
    boolean visible = false;

    Scoop() {
        scoopImage = new ImageIcon(ResourseLoader.loaderResourseUrl("scoop.png")).getImage();
    }

}
