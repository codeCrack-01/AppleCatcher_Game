package General;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public static int xAxis = 0;
    public static int yAxis = 0;

    public static boolean spacePressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            xAxis = -1;
            //System.out.println("LEFT");
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xAxis = 1;
            //System.out.println("RIGHT");
        }

        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            yAxis = -1;
            //System.out.println("DOWN");
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            yAxis = 1;
            //System.out.println("UP");
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = true;
    }

    // !ALERT! : ! DO RESET THE VALUES FROM THE SCRIPT WHERE IT IS USED !
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            xAxis = 0;

        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            yAxis = 0;

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = false;
    }
}
