package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        String[][] nodes = new String[20][10];
        System.out.println(nodes.length);
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j] = "";
            }
        }

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20,20));
        frame.add(panel);

        JButton[][] buttons = new JButton[nodes.length][nodes[0].length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                buttons[i][j] = new JButton(nodes[i][j]);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(clickEvent -> {
                    if (clickEvent.getSource() instanceof JButton button) {
                        button.setBackground(Color.GRAY);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
    }
}
