package General;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Main {
    private static BufferedImage icon;

    static Container con = new Container();

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setTitle("Food Catcher");

        icon = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/icon.png")));
        window.setIconImage(icon);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        gamePanel.StartGameThread();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
