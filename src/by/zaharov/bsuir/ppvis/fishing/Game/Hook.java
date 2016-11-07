package by.zaharov.bsuir.ppvis.fishing.Game;

import by.zaharov.bsuir.ppvis.fishing.resourses.ResourseLoader;

import javax.swing.*;
import java.awt.*;

class Hook {
    Image hookImage = null;
    int x = 100;
    int y = 100;

    Hook() {
        hookImage = new ImageIcon(ResourseLoader.loaderResourseUrl("hook.png")).getImage();
    }

}
