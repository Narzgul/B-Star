package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main() {
        JFrame frame = new JFrame();
        frame.setSize(150, 200);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20,20));
        frame.add(panel);

    }
}
