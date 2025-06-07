package GUI;

import GUI.App;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    App frame=new App("Kolokwium");
                    frame.setVisible(true);
                }catch(Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }
}