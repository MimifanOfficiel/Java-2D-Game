package fr.mimifan.j2d.entities;

import fr.mimifan.j2d.listeners.KeyHandler;
import fr.mimifan.j2d.panels.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = y = 100;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if(keyHandler.upPressed && keyHandler.leftPressed) {
            direction = "up";
            x -= speed;
            y -= speed;
        } else if(keyHandler.upPressed && keyHandler.rightPressed) {
            direction = "up";
            x += speed;
            y -= speed;
        } else if(keyHandler.downPressed && keyHandler.leftPressed) {
            direction = "down";
            x -= speed;
            y += speed;
        } else if(keyHandler.downPressed && keyHandler.rightPressed) {
            direction = "down";
            x += speed;
            y += speed;
        } else if(keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if(keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if(keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        } else if(keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }

        spriteCounter ++;
        if(spriteCounter > 10) {
            if(spriteNumber == 1) spriteNumber = 2;
            else if(spriteNumber == 2) spriteNumber = 1;
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage playerImage = null;
        playerImage = switch (direction) {
            case "up" -> spriteNumber == 1 ? up1 : up2;
            case "down" -> spriteNumber == 1 ? down1 : down2;
            case "left" -> spriteNumber == 1 ? left1 : left2;
            case "right" -> spriteNumber == 1 ? right1 : right2;
            default -> down1;
        };

        g2d.drawImage(playerImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.dispose();
    }

}
