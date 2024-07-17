package General;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import GameObjects.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int originalTileSize = 16; // (16 x 16) pixel tile set
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tiles

    public final int maxScreenCol = 12;
    public final int maxScreenRow = 10;

    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    public int score = 0;
    public static boolean gameOver = false;

    int FPS = 60;
    public Thread gameThread;

    KeyboardInput keyboardInput = new KeyboardInput();

    // GameObjects ...
    public Player player = new Player(this);
    private LinkedList<Food> food = new LinkedList<Food>();

    // The UI Class ...
    public  UI_Class ui = new UI_Class(this);

    public static boolean flag = false;
    public static Food flagFood;

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(32, 32, 32));

        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardInput);

        this.setFocusable(true);
        System.out.println("Score : " + score);

        // Set Game-Objects.
        player.setDefaults();
        flagFood = null;

        food.add(0, new Food(this));
        food.get(0).setDefaults();
    }

    public void StartGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // SETUP THE GAME LOOP ...
    @Override
    public void run() {
        double drawInterval = (double) (10^9) / FPS; // Interval in nano-seconds.
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint(); // Re-draws the screen ...

                delta --;
            }
        }
    }

    private void ResetGame () {
        gameOver = false;
        flag = false;

        player.setDefaults();
        score = 0;

        flagFood = null;

        for (Food value : food) {
            if (value != null)
                value.setDefaults();
        }

        Player.canMove = true;
    }

    int reqScore = 5;
    void IncFood ()
    {
        if (food.size() >= 3)
            return;

        if (score == reqScore)
        {
            food.add(food.indexOf(food.getLast())+1, new Food(this));
            food.get(food.indexOf(food.getLast())).setDefaults();

            reqScore = score+5;
        }
    }

    int prevScore = 0;
    public void update () {
        if (gameOver) {
            Player.canMove = false;
            if (KeyboardInput.spacePressed)
                ResetGame();
        }

        if (flag)
        {
            if (food.size() > 1)
            {
                food.remove(flagFood);

                flagFood = null;
                flag = false;
                return;
            }
            if (food.size() == 1)
            {
                gameOver = true;
                return;
            }
        }

        if (score != prevScore)
        {
            IncFood();
            prevScore = score;
        }

        player.update();

        for (Food value : food) {
            if (value != null)
                value.update();
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.repaint(g2);
        for (Food value : food) {
            if (value != null)
                value.repaint(g2);
        }

        // Set the UI.
        ui.draw(g2);

        g2.dispose();
    }
}
