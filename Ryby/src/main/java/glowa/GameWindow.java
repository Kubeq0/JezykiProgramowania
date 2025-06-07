package glowa;

import javax.swing.*;

public class GameWindow extends JFrame{
    int delay = 10;

    public GameWindow(GamePanel panel){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setContentPane(panel);
        setResizable(false);
        pack();
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                panel.repaint();
//            }
//        });
        setLocationRelativeTo(null);
        panel.requestFocus();
    }

}
