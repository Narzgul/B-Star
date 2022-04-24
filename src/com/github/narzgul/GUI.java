package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JFrame frame;
    JPanel panel;
    JButton[][] buttons;
    boolean testflag;
    public GUI(int length, int height) {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(length,height));
        frame.add(panel);

        buttons = new JButton[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(clickEvent -> {
                    if (clickEvent.getSource() instanceof JButton button) {
                        button.setBackground(Color.GRAY);
                        System.out.println("Cords: " + finalI + ',' + finalJ);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
    }
}
