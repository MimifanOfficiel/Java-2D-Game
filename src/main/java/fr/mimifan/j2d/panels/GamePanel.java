package fr.mimifan.j2d.panels;

import fr.mimifan.j2d.listeners.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // Each tile is 16x16 pixels
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    int maxFPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    int playerX = 100, playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
            double drawInterval = 1000000000/maxFPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();


            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        int deltaX = (keyHandler.rightPressed ? playerSpeed : 0) - (keyHandler.leftPressed ? playerSpeed : 0);
        int deltaY = (keyHandler.downPressed ? playerSpeed : 0) - (keyHandler.upPressed ? playerSpeed : 0);

        playerX += deltaX;
        playerY += deltaY;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
