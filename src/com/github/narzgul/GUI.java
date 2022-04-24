package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {
    private JFrame frame;
    private JPanel grid;
    private JButton[][] buttons;
    private Object notifier;

    private ArrayList<int[]> obstacle = new ArrayList<>();
    public GUI(int length, int height) {
        notifier = new Object();

        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null); // Middle of the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Stop process on closing

        JPanel borderLayout = new JPanel();
        borderLayout.setLayout(new BorderLayout());
        grid = new JPanel();
        grid.setLayout(new GridLayout(length,height));
        borderLayout.add(grid, BorderLayout.CENTER); // Middle of the panel
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout());
        borderLayout.add(statusBar, BorderLayout.SOUTH); // Bottom of the panel
        frame.add(borderLayout);

        JTextField status = new JTextField("Erik ist gay");
        status.setBorder(null); // Remove border and background
        status.setBackground(null);
        statusBar.add(status);

        buttons = new JButton[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                int finalI = i; // Final vars for the ActionListener
                int finalJ = j;
                buttons[i][j].addActionListener(clickEvent -> {
                    synchronized (notifier) {
                        if (clickEvent.getSource() instanceof JButton button) {
                            button.setBackground(Color.GRAY);
                            System.out.println("Cords: " + finalI + ',' + finalJ);
                            obstacle.add(new int[]{finalI, finalJ}); // Add cords to list
                            notifier.notify();
                        }
                    }
                });
                grid.add(buttons[i][j]);
            }
        }
    }

    public ArrayList<int[]> getObstacle() {
        return obstacle;
    }

    public void setNotifier(Object notifier) {
        this.notifier = notifier;
    }
}
