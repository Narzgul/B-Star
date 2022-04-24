package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI {
    JFrame frame;
    JPanel panel;
    JButton[][] buttons;
    ArrayList<int[]> obstacle = new ArrayList<>();
    public GUI(int length, int height) {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null); // Middle of the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Stop process on closing

        panel = new JPanel();
        panel.setLayout(new GridLayout(length,height));
        frame.add(panel);

        buttons = new JButton[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                int finalI = i; // Final vars for the ActionListener
                int finalJ = j;
                buttons[i][j].addActionListener(clickEvent -> {
                    if (clickEvent.getSource() instanceof JButton button) {
                        button.setBackground(Color.GRAY);
                        System.out.println("Cords: " + finalI + ',' + finalJ);
                        obstacle.add(new int[]{finalI, finalJ}); // Add cords to list
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
    }
}
