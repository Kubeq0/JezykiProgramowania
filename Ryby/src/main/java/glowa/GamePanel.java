package glowa;

import entities.Player;
import inputs.Keyboardinputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    private BufferedImage background;
    private Timer timer;
    private int delay=15;
    private Game game;
    public JLabel score=new JLabel();
    public Player getPlayer(){
        return game.getPlayer();
    }
    public GamePanel(Game game, Timer timer) {
        super();
        setLayout(null);

        Dimension size = new Dimension(900,540);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        this.game=game;
        try {
            background = ImageIO.read(new File("res/4_game_background/4_game_background.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        addKeyListener(new Keyboardinputs(this));
        setBackground(new Color(50,100,23));
        add(score);
        score.setBounds(800,0,100,30);
        this.timer = timer;
        //timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,900,540,null);
        game.render(g);
    }

    public void run(){
//        double update = 1000000000.0/100;
//        long previousTime= System.nanoTime();
//        double deltaU =0;
//        while(true) {
//            long currentTime = System.nanoTime();
//            deltaU = deltaU + (currentTime-previousTime)/update;
//            previousTime = currentTime;
//            if(deltaU>1){
//                repaint();
//                deltaU--;
//            }
//        }
        while (true) {
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
