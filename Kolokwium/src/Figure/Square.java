package Figure;

import java.awt.*;

public class Square {
    private float x;
    private float y;
    private int width =2;
    private int height =2;
    private Color color;
    public Square(float x,float y,Color color){
        this.x=x;
        this.y=y;
        this.color = color;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillRect((int) (x-1), (int) (y-1),this.width,this.height);
    }
     public void setY(int y){
        this.y=y;
     }
}
