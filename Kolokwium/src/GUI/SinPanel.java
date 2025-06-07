package GUI;

import Figure.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class SinPanel extends JPanel implements Runnable {
    private int A;
    private double B;
    private double C;
    private int A2;
    private double B2;
    private double C2;
    private int x=0;
    private int height;
    private Color color;
    private ArrayList<Square> signal=new ArrayList<>();
    public SinPanel(int A,double B,double C, int height,Color color){
        this(A,B,C,0,0,0,height,color);
        // y=A*sin(bx+c)
    }
    public SinPanel(int A1,double B1,double C1,int A2,double B2,double C2,int height,Color color){
        setBorder(new LineBorder(Color.BLACK,4,true));
        setBackground(Color.white);
        setLayout(null);
        this.A=A1;
        this.B=B1;
        this.C=C1;
        this.A2=A2;
        this.B2=B2;
        this.C2=C2;
        this.color=color;
        this.height=height;
        for(float i=0;i<608; i= (float) (i+0.1)){
            signal.add(new Square(i,height/2,color));
        }
    }

    public void setA(int a) {
        A = a;
    }

    public void setB(double b) {
        B = b;
    }

    public void setC(double c){
        C=c;
    }

    public void setA2(int a2) {
        A2 = a2;
    }

    public void setB2(double b2) {
        B2 = b2;
    }
    public void setC2(double c2){
        C2=c2;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try{
            for(int i=0;i<signal.size();i++){
                signal.get(i).draw(g2d);
            }
        }finally{
            g2d.dispose();
        }
    }
    @Override
    public void run() {
        while(true){
            if(A2==0 && B2==0 && C2==0) {
                for (int i = 0; i < signal.size(); i++) {
                    signal.get(i).setY(height / 2 + (int) (A * Math.sin(B * (i*0.1 + x) + C)));
                }
            }else{
                for(int i = 0; i < signal.size(); i++) {
                    int y1= (int) ((A*Math.sin(B*(i*0.1+x)+C)));
                    int y2= (int) ((A2*Math.sin(B2*(i*0.1+x)+C2)));
                    signal.get(i).setY(height/2+y1+y2);
                }
            }
            x+=1;
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
