package application;

import models.Balle;
import models.Barre;
import models.Brique;
import models.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Fenetre extends Canvas implements KeyListener {

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    protected boolean leftArrow = false;
    protected boolean rightArrow = false;
    protected boolean spaceBar = false;
    protected int speedBar = 6;

    ArrayList<Balle> listeBalles = new ArrayList<>();
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    Barre playBar;

    Fenetre()  throws InterruptedException {

        JFrame fenetre = new JFrame();

        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);

        this.setIgnoreRepaint(true);
        this.setFocusable(false);

        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR );
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);


        Container panneau = fenetre.getContentPane();
        panneau.add(this);

        fenetre.setVisible(true);
        this.createBufferStrategy(2);

        this.demarrer();
        fenetre.dispose();

    }

    public void demarrer() throws InterruptedException {

        int nbColumns = 10;
        int nbRows = 5;

        int espaceBrique = 10;
        int briqueWidth = 45;
        int briqueHeight = 20;

        playBar = new Barre();
        listeSprites.add(playBar);

        Balle balle = new Balle(100, 200 , Color.WHITE, 30);

        listeBalles.add(balle);
        listeSprites.add(balle);

        for (int row = 0; row < nbRows; row++) {
            for (int col = 0; col < nbColumns; col++) {
                int x = col * (briqueWidth + espaceBrique);
                int y = row * (briqueHeight + espaceBrique);
                Color couleur = Color.CYAN;
                Brique brique = new Brique(x, y, briqueWidth, briqueHeight, couleur);
                listeSprites.add(brique);
            }
        }

        while(true) {

            if (allBricksBroken()) {
                System.out.println("Vous avez gagné !");
                break;
            }

            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.BLACK);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);


            for(Balle b : listeBalles) {

                for (int i = 0; i < listeSprites.size(); i++) {
                    Sprite sprite = listeSprites.get(i);
                    if (sprite instanceof Brique) {
                        Brique brique = (Brique) sprite;
                        if (balle.briqueTouched(brique)) {
                            listeSprites.remove(i);
                            balle.rebondY();
                            break;
                        }
                    }
                }

                b.deplacement();
                b.collisionManager(playBar);
            }

            for(Sprite s : listeSprites) {
                s.dessiner(dessin);
            }
            if(spaceBar) {
                listeBalles.add( new Balle(200, 400 , Color.BLUE, 50));
            }
            if(rightArrow)
            {
                playBar.moveRight(speedBar);
            }
            if(leftArrow)
            {
                playBar.moveLeft(speedBar);
            }

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }


    private boolean allBricksBroken() {
        for (Sprite sprite : listeSprites) {
            if (sprite instanceof Brique) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceBar = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftArrow = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightArrow = true;
        }
    }


    @Override
    public void  keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceBar = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftArrow = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightArrow = false;
        }
    }
}
