package GameObjects;

import General.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    public BufferedImage image;
    public Rectangle collider;

    GamePanel gp;
    int posX, posY;

    private final int speedFactor = 4;
    public static boolean canMove = true;

    public Player (GamePanel gp) {
        this.gp = gp;
    }

    public void setDefaults () {
        posX = 5*speedFactor;
        posY = 9*speedFactor;

        collider = new Rectangle(posX, posY, 6, 2);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Player : " + "[" + posX + ", " + posY + "]");
    }

    public void update () {
        checkBounds();

        if (canMove) {
            if (KeyboardInput.xAxis == -1) {
                posX -= 1;
                KeyboardInput.xAxis = 0;
            }

            else if (KeyboardInput.xAxis == 1) {
                posX += 1;
                KeyboardInput.xAxis = 0;
            }
        }
    }

    void checkBounds () {
        if (collider.x < 0*speedFactor)
            posX = (int)(10.8*speedFactor);
        if (collider.x > 10.8*speedFactor)
            posX = 0*speedFactor;
    }

    public void repaint (Graphics2D g2) {
        g2.drawImage(image, (posX * gp.tileSize)/speedFactor, (posY * gp.tileSize)/speedFactor, gp.tileSize*4, gp.tileSize*4, null);
        collider.x = posX;
        collider.y = posY;
    }
}
