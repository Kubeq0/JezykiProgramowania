package glowa;

import entities.EnemyManager;
import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utilz.Constants.END;
import static utilz.Constants.PL;

public class Game implements ActionListener {
    private int delay=15;
    Timer timer = new Timer(delay,this);
    private GamePanel panel;
    private GameWindow window;
    EnemyManager manager = new EnemyManager(this);
    private JLabel end=new JLabel();
    private JButton restart = new JButton("RESTART");
    private Player player;
    public Game(){
        initEntity();
        panel = new GamePanel(this,timer);
        window = new GameWindow(panel);
        new Thread(panel).start();
        new Thread(player).start();
        new Thread(manager).start();

        panel.add(end);
        end.setText("GAME OVER");
        end.setBounds(300,250,200,100);
        end.setVisible(false);

        panel.add(restart);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart.setVisible(false);
                end.setVisible(false);
                player.reset();
                Player.fat=100;
                manager.reset();
                END=0;
                panel.requestFocus();
            }
        });
        restart.setBounds(400,250,100,50);
        restart.setVisible(false);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(3);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    manager.checkHit(player);
//                }
//            }
//        }).start();
        timer.start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.setVisible(true);
            }
        });
    }
    private void initEntity(){
        player=new Player(450,230);
        PL=player;
    }
    public void render(Graphics g){
        player.render(g);
        manager.draw(g);
    }
    public Player getPlayer() {
        return player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //player.updateSprite();
        if(EnemyManager.count<20){
        manager.addEnemy();
        }
        panel.score.setText(String.valueOf(Player.getScore()-100));
        Dimension size =  panel.score.getPreferredSize();
        panel.score.setBounds(900-size.width,0,size.width,size.height);
        //player.updateSprite();
        //manager.update();
        if(END==1){
            end.setVisible(true);
            restart.setVisible(true);
            //timer.stop();
        }
    }
}
