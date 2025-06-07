package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    private boolean left,right,up,down;
    private float playerSpeed = 2.5f;
    private float speedX = -1f;
    private float speedY = -1f;
    private float xOffset = 23,yOffset = 23;
    private float xWidth= 15,yHeight = 8;

    public static double scale=1;
    public static int fat = 100;
    public static int getScore(){
        return fat;
    }

    public Player(float x, float y) {
        super(x, y,48,48);
        loadAnimations();
        initHitBox(x,y,xWidth,yHeight);
    }
    public void render(Graphics g){
        g.drawImage(anim.get(action).get(aniIndex),(int)(hitBox.x-xOffset+x_flip),(int)(hitBox.y-yOffset), width*m_left, height,null);
        //drawHitBox(g);
    }

    @Override
    public void updateSprite(){
        scale=fat/100.0;
        double n_wh=Math.round(48*scale);
        width= (int) n_wh;
        height = (int) n_wh;
        updateHitBox((int) (15*scale), (int) (8*scale));
        xOffset= (float) (23*scale);
        yOffset= (float) (23*scale);
        updatePos();
        updateAnimationTick();
        setAnimation();
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
                //deltaU--;

        }
    }
    public void reset(){
        hitBox.x=450;
        hitBox.y=230;
    }
    public void setSpeedX(float x){
        speedX=x;
    }
    public void setSpeedY(float y){
        speedY=y;
    }
    @Override
    public void updatePos(){
        if(left){
            if(hitBox.x-(playerSpeed)>0) {
                hitBox.x -= (playerSpeed);
            }
            m_left=-1;
            x_flip=width;
        }
        if(right){
            if(hitBox.x+(playerSpeed)<900) {
                hitBox.x += (playerSpeed);
            }
            m_left=1;
            x_flip=0;
        }
        if(up){
            if(hitBox.y-(playerSpeed)>0) {
                hitBox.y -= (playerSpeed);
            }
        }
        if(down){
            if(hitBox.y+(playerSpeed)<520) {
                hitBox.y += (playerSpeed);
            }
        }
        if(!isMoving) {
            if (aniTick >= 3) {
                hitBox.x += speedX;
                if(hitBox.y-speedY<520) {
                    hitBox.y -= speedY;
                }
                if (speedY > -1) {
                    speedY--;
                } else if (speedY < -1) {
                    speedY++;
                }
                if (speedX > 0) {
                    speedX--;
                } else if (speedX < 0) {
                    speedX++;
                }
            }
        }
        if((!(left || right || up || down))){
            isMoving=false;
        }else{
            isMoving=true;
        }
    }
//    public void setMoving(boolean moving){
//        isMoving=moving;
//    }

    private void loadAnimations(){
        anim=new ArrayList<>();
        try {
            BufferedImage img = ImageIO.read(new File("res/Shark/Idle.png"));
            aniIndex=0;
            anim.add(new ArrayList<>());
            int index = 0;
            for (int i = 0; i < 4; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));

            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Shark/Walk.png"));
            for (int i = 0; i < 4; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Shark/Hurt.png"));
            for (int i = 0; i < 2; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Shark/Attack.png"));
            for (int i = 0; i < 6; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
            anim.add(new ArrayList<>());
            img = ImageIO.read(new File("res/Shark/Death.png"));
            for (int i = 0; i < 6; i++) {
                anim.get(index).add(img.getSubimage(i * 48, 0, 48, 48));
            }
            index++;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
