package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Change {
    private JButton button;
    private JTextField text;
    private JButton button2;
    private JTextField text2;
    private JButton button3;
    private JTextField text3;
    public Change(int x, int y, int width, int height,SinPanel sin, SinPanel sin2,int num){
        this.button=new JButton("A"+num);
        this.text=new JTextField();
        text.setBounds(x,y,width,height);
        text.setBorder(new LineBorder(Color.BLACK,4,true));
        button.setBounds(x+82,y,100,height);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=Integer.valueOf(text.getText());
                text.setText("");
                if (num == 1) {
                    sin.setA(i);
                    sin2.setA(i);
                } else {
                    sin.setA(i);
                    sin2.setA2(i);
                }
            }
        });
        y+=31;
        this.button2=new JButton("B"+num);
        this.text2=new JTextField();
        text2.setBounds(x,y,width,height);
        text2.setBorder(new LineBorder(Color.BLACK,4,true));
        button2.setBounds(x+82,y,100,height);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double i=Double.valueOf(text2.getText());
                text2.setText("");
                if (num == 1) {
                    sin.setB(i);
                    sin2.setB(i);
                } else {
                    sin.setB(i);
                    sin2.setB2(i);
                }
            }
        });
        y+=31;
        this.button3=new JButton("C"+num);
        this.text3=new JTextField();
        text3.setBounds(x,y,width,height);
        text3.setBorder(new LineBorder(Color.BLACK,4,true));
        button3.setBounds(x+82,y,100,height);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double i=Double.valueOf(text3.getText());
                text3.setText("");
                if (num == 1) {
                    sin.setC(i);
                    sin2.setC(i);
                } else {
                    sin.setC(i);
                    sin2.setC2(i);
                }
            }
        });
    }
    public JButton getButton(){
        return button;
    }

    public JTextField getTextField(){
        return text;
    }
    public void addTo(JPanel panel){
        panel.add(text);
        panel.add(button);
        panel.add(text2);
        panel.add(button2);
        panel.add(text3);
        panel.add(button3);
    }
}
