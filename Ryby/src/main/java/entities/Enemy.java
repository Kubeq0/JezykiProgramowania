package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static utilz.Constants.*;
import static utilz.Constants.PlayerConst.*;

public class Enemy extends Entity{
    private boolean l_r=false,u_d=false,x_moving=true,y_moving=false;
    private float enemySpeed = 2.0f;
    protected float xOffset = 13,yOffset = 18;
    private float xWidth= 20,yHeight = 13;

    protected float scale=1;
    public Enemy(float x, float y, int fatness) {
        super(x, y, 48, 48);
        loadAnimations();
        scale=fatness/10;
        initHitBox(x,y,(float)20*scale,(float)13*scale);
        this.fatness=fatness;
        xOffset= 13*scale;
        yOffset= 18*scale;
    }
    private void updateDir(){
        Random rand = new Random();
        if(rand.nextInt()%200>=199){
            l_r=!l_r;
        }
        if(rand.nextInt()%200>=190){
            u_d=!u_d;
        }
        if(rand.nextInt()%200>=190){
            y_moving=!y_moving;
        }
    }
    public void out(){
        Random rand = new Random();
        if(rand.nextBoolean()){
            hitBox.x=-3;
        }else{
            hitBox.x=903;
        }
    }
    @Override
    public void updatePos(){
        Random rand = new Random();
        if(END!=1) {
            if (hitBox.x < 0) {
                hitBox.x = 900;
                fatness = rand.nextInt(600) - 300;
                scale = (float) ((Player.fat + fatness) / 100.0);
                scale = Math.abs(scale);
                updateHitBox((int) (scale * 20), (int) (scale * 13));
                xOffset = 13 * scale;
                yOffset = 18 * scale;
            }
            if (hitBox.x > 900) {
                hitBox.x = 0;
                fatness = rand.nextInt(600) - 300;
                scale = (float) ((Player.fat + fatness) / 100.0);
                scale = Math.abs(scale);
                updateHitBox((int) (scale * 20), (int) (scale * 13));
                xOffset = 13 * scale;
                yOffset = 18 * scale;
            }
            if (hitBox.y < 0) {
                hitBox.y = 530;
                fatness = rand.nextInt(600) - 300;
                scale = (float) ((Player.fat + fatness) / 100.0);
                scale = Math.abs(scale);
                updateHitBox((int) (scale * 20), (int) (scale * 13));
                xOffset = 13 * scale;
                yOffset = 18 * scale;
            }
            if (hitBox.y > 530) {
                hitBox.y = 0;
                fatness = rand.nextInt(600) - 300;
                scale = (float) ((Player.fat + fatness) / 100.0);
                scale = Math.abs(scale);
                updateHitBox((int) (scale * 20), (int) (scale * 13));
                xOffset = 13 * scale;
                yOffset = 18 * scale;
            }
            if (x_moving || y_moving) {
                action = WALK;
            }
            if (x_moving) {
                if (l_r) {
                    hitBox.x += enemySpeed;
                    m_left = 1;
                    x_flip = 0;
                } else {
                    hitBox.x -= enemySpeed;
                    m_left = -1;
                    x_flip = width;
                }
            }
            if (y_moving) {
                if (u_d) {
                    hitBox.y += enemySpeed;
                } else {
                    hitBox.y -= enemySpeed;
                }
            }
        }
    }
    @Override
    public void updateSprite(){
        updateDir();
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(anim.get(action).get(aniIndex),(int)(hitBox.x-xOffset),(int)(hitBox.y-yOffset),null);
        drawHitBox(g);
    }
    private void loadAnimations(){
        anim=new ArrayList<>();
        try {
            BufferedImage img = ImageIO.read(new File("res/Fish/Idle.png"));
            aniIndex=0;
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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
//        double update = 1000000000.0/100;
//        long previousTime= System.nanoTime();
//        double deltaU =0;
        while(true) {
//            long currentTime = System.nanoTime();
//            deltaU = deltaU + (currentTime-previousTime)/update;
//            previousTime = currentTime;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            updateSprite();
//            if(deltaU>1){
//                updateSprite();
//                deltaU--;
//            }
        }
    }
}
