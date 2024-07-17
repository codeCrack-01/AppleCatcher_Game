package General;

import java.awt.*;

public class UI_Class {
    private final GamePanel gp;
    private final Font SansSerif_32;
    private final Font Serif_32;

    public UI_Class (GamePanel gp) {
        this.gp = gp;
        SansSerif_32 = new Font("SansSerif", Font.BOLD, 32);
        Serif_32 = new Font("SansSerif", Font.BOLD, 32);
    }

    public void draw (Graphics2D g2) {
        g2.setFont(SansSerif_32);
        g2.setColor(Color.YELLOW);

        g2.drawString("Score : " + gp.score, 16f, 36f); // DrawString : Draws from "base-line" , not "top-left".

        if (GamePanel.gameOver)
        {
            g2.setColor(Color.RED);
            g2.setFont(Serif_32);

            g2.drawString("GAME OVER !", 3.75f * gp.tileSize, 4.75f * gp.tileSize);
        }
    }
}
