package GameObjects;

import General.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Food {
    GamePanel gp;
    private Rectangle collider;

    private final int speedFactor = 4;

    BufferedImage image;
    int posX, posY;

    Random rand = new Random();

    public Food(GamePanel gp) {
        this.gp = gp;
    }

    public void setDefaults () {
        ResetPos();

        timer = 1000f; // Reset the Timer...
        collider = new Rectangle(posX, posY, 4, 4);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Food/apple.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ball : " + "[" + posX + ", " + posY + "]");
    }


    public void update () {
        if (!GamePanel.gameOver)
            gravityUpdate();

        if (collider.intersects(gp.player.collider))
        {
            ResetPos();
            temp = rand.nextInt(550, 951);

            gp.score++;
            System.out.println("Score : " + gp.score);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return;
        }

        if (posY >= 9.25*4)
        {
            //ResetPos();
            GamePanel.flag = true;
            GamePanel.flagFood = this;
        }
    }

    void ResetPos ()
    {
        float xFactor = rand.nextFloat(0, 11.3f);

        posX = (int)(xFactor * speedFactor);
        posY = 1 * speedFactor;
    }

    float timer = 1000f;
    float temp = timer;

    void gravityUpdate () {
        timer -= 0.001f;

        if (timer <= 0) {
            posY += 1;
            timer = temp;
            //System.out.println("Ball : " + "[" + posX + ", " + posY + "]");
        }
    }

    public void repaint (Graphics2D g2) {
        g2.drawImage(image, (posX * gp.tileSize)/speedFactor, (posY * gp.tileSize)/speedFactor, gp.tileSize*4, gp.tileSize*4, null);
        collider.x = posX;
        collider.y = posY;
    }
}
