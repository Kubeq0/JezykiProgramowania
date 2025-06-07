package glowa;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new Game();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}