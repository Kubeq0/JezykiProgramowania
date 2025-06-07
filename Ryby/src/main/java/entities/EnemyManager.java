package entities;

import glowa.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static utilz.Constants.END;
import static utilz.Constants.PL;

public class EnemyManager implements Runnable {
    public static int count=0;
    private Game game;
    protected ArrayList<ArrayList<BufferedImage>> anim;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Game game){
        this.game=game;
        loadAnimations();
    }
    public void update(){
        for(Enemy e : enemies){
            e.updateSprite();
        }
    }
    public void addEnemy(){
        Random rand=new Random();
        int fatness = rand.nextInt(10)-5;
        enemies.add(new Enemy(rand.nextInt(900), rand.nextInt(520),(Player.fat)/10+fatness ));
        new Thread(enemies.get(enemies.size()-1)).start();
        count++;
    }
    public void reset(){
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).out();
        }
    }
    public void draw(Graphics g){
        drawEnemies(g);
    }
    public void checkHit(Player player){
        Rectangle2D.Float rec = player.getHitBox();
        for(Enemy e: enemies){
            if(rec.intersects(e.getHitBox())){
                if(e.scale<=player.scale){
                    player.setAttacing(true);
                    Player.fat+=10;
                    //e.setHurt(true);
                    e.out();
                }else{
                    player.setHurt(true);
                    e.setAttacing(true);
                    END=1;
                }
            }
        }
    }
    public void drawEnemies(Graphics g){
        for(Enemy e:enemies){
            g.drawImage(anim.get(e.action).get(e.aniIndex),(int)(e.getHitBox().x-e.xOffset+e.scale*e.x_flip),(int)(e.getHitBox().y- e.yOffset), (int) (e.m_left*(48* e.scale)), (int) (48*e.scale),null);
            //e.drawHitBox(g);
        }
    }
    private void loadAnimations(){
        anim=new ArrayList<>();
        try {
            BufferedImage img = ImageIO.read(new File("res/Fish/Idle.png"));
            anim.add(new ArrayList<>());
            int index = 0;
            for (int i = 0; i < 4; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));

            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Fish/Walk.png"));
            for (int i = 0; i < 4; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Fish/Hurt.png"));
            for (int i = 0; i < 2; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Fish/Attack.png"));
            for (int i = 0; i < 6; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Fish/Death.png"));
            for (int i = 0; i < 6; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            while (END == 0) {
                try {
                    Thread.sleep(15);
                    checkHit(PL);
                    //update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(END!=0){
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
