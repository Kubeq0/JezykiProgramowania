package entities;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.PlayerConst.*;
import static utilz.Constants.PlayerConst.HURT;

public abstract class Entity implements Runnable, ActionListener {

    protected int action = IDLE;
    protected int fatness = 0;
    protected ArrayList<ArrayList<BufferedImage>> anim;
    protected boolean isMoving=false, isAttacing=false, isHurt=false;
    protected int aniIndex, aniTick , aniSpeed=10;
    protected float x,y;
    protected int width,height;
    protected Rectangle2D.Float hitBox;

    protected int m_left=1;
    protected int x_flip=0;
    public Entity(float x,float y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public abstract void updatePos();
    public abstract void updateSprite();
    public void initHitBox(Float x,Float y,Float width,Float height) {
        hitBox = new Rectangle2D.Float(x,y,width,height);
    }
    public void updateAnimationTick(){
        aniTick++;
        if(aniTick>=aniSpeed) {
            aniIndex++;
            aniTick=0;
            if (aniIndex >= anim.get(action).size()) {
                aniIndex = 0;
                setAttacing(false);
                setHurt(false);
            }
        }
    }
    public void setAttacing(boolean b) {
        isAttacing = b;
        resetAnimation();
    }
    public void setHurt(boolean b){
        resetAnimation();
        isHurt = b;
        resetAnimation();
    }
    protected void resetAnimation(){
        aniIndex=0;
        aniTick=0;
    }
    public void updateHitBox(int width,int height){
        hitBox.width= width;
        hitBox.height= height;
    }
    protected void drawHitBox(Graphics g){
        g.setColor(Color.RED);
        g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
    }
    public void setAnimation(){
        if(isMoving){
            action = WALK;
        }else{
            action = IDLE;
        }

        if(isAttacing){
            action = ATTACK;
        }
        if(isHurt){
            action = HURT;
        }
    }
    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }

}
