package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App extends JFrame {
    public App(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(828,478);

        JPanel contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int A1=10;
        int A2=20;

        double B1=0.02;
        double B2=0.06;

        double C1=0;
        double C2=Math.PI;


        SinPanel sin1=new SinPanel(A1,B1,C1,100, Color.BLUE);
        sin1.setBounds(10,11,608,100);

        contentPane.add(sin1);

        SinPanel sin2 = new SinPanel(A2,B2,C2,100,Color.RED);
        sin2.setBounds(10,122,608,100);

        contentPane.add(sin2);

        SinPanel sin3 = new SinPanel(A1,B1,C1,A2,B2,C2,200,Color.magenta);
        sin3.setBounds(10,233,608,200);

        contentPane.add(sin3);

        Change ch1 = new Change(628,11,80,20,sin1,sin3,1);
        ch1.addTo(contentPane);

        Change ch2 = new Change(628,122,80,20,sin2,sin3,2);
        ch2.addTo(contentPane);

        new Thread(sin1).start();
        new Thread(sin2).start();
        new Thread(sin3).start();
    }
}
