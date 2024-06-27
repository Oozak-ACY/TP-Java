package models;

import application.Fenetre;
import java.awt.*;

public class Barre extends Rectangle {

    public Barre(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur, couleur);
    }

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 60, (Fenetre.HAUTEUR) - 100, 120, 20, Color.RED);
    }

    public void moveLeft(int speed) {
        if (x > 0) {
            x -= speed;
        }
    }
    public void moveRight(int speed) {
        if (x + largeur < Fenetre.LARGEUR) {
            x +=speed;
        }
    }
}
