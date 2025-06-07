package inputs;

import entities.Player;
import glowa.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboardinputs implements KeyListener {
    private GamePanel panel;
    public Keyboardinputs(GamePanel panel){
        this.panel=panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_W:
                panel.getPlayer().setUp(true);
                break;
            case KeyEvent.VK_S:
                panel.getPlayer().setDown(true);
                break;
            case KeyEvent.VK_A:
                panel.getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_D:
                panel.getPlayer().setRight(true);
                break;
            case KeyEvent.VK_C:
                panel.getPlayer().setAttacing(true);
                Player.fat+=10;
                break;
            case KeyEvent.VK_H:
                panel.getPlayer().setHurt(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                panel.getPlayer().setUp(false);
                panel.getPlayer().setSpeedY(-2f);
                break;
            case KeyEvent.VK_S:
                panel.getPlayer().setDown(false);
                panel.getPlayer().setSpeedY(2f);
                break;
            case KeyEvent.VK_A:
                panel.getPlayer().setLeft(false);
                panel.getPlayer().setSpeedX(-2f);
                break;
            case KeyEvent.VK_D:
                panel.getPlayer().setRight(false);
                panel.getPlayer().setSpeedX(2f);
                break;
        }
    }
}
