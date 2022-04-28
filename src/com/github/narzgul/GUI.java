package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {
    private char nodeType; // Type of Node to be created
    private int[] start, end;
    private final JButton[][] buttons;


    private final ArrayList<int[]> obstacle = new ArrayList<>();
    public GUI(int length, int height) {
        nodeType = 's';

        JFrame frame = new JFrame();
        frame.setSize(500, 550);
        frame.setTitle("A*");
        frame.setLocationRelativeTo(null); // Middle of the screen
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Stop process on closing
        frame.doLayout();

        JPanel borderLayout = new JPanel();
        borderLayout.setLayout(new BorderLayout()); // A layout with borders that are own panels
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(length,height));
        borderLayout.add(grid, BorderLayout.CENTER); // Middle of the panel
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout());
        borderLayout.add(statusBar, BorderLayout.SOUTH); // Bottom of the panel
        frame.add(borderLayout);

        JTextField status = new JTextField("Set the Start");
        status.setBorder(null); // Remove border and background
        status.setBackground(null);
        statusBar.add(status);

        JButton next = new JButton("Next");
        next.addActionListener(e -> {
            switch (nodeType) {
                case 's' -> {
                    nodeType = 'e';
                    status.setText("Set the End");
                    statusBar.doLayout();
                }
                case 'e' -> {
                    nodeType = 'o';
                    status.setText("Set the Obstacles");
                    statusBar.doLayout();
                }
                case 'o' -> {
                    Main.getInstance().startPathfinder();
                    status.setText("Start again?");
                    statusBar.doLayout();
                }
            }
        });
        statusBar.add(next);

        buttons = new JButton[length][height];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                int finalI = i; // Final vars for the ActionListener
                int finalJ = j;
                buttons[i][j].addActionListener(clickEvent -> {
                    if (clickEvent.getSource() instanceof JButton button) {
                        switch (nodeType) {
                            case 's' -> { // Start
                                button.setBackground(Color.GREEN);
                                System.out.println("Start: " + finalI + ',' + finalJ);
                                if (start != null) buttons[start[0]][start[1]].setBackground(Color.WHITE);
                                start = new int[]{finalI, finalJ};
                            }
                            case 'e' -> { // End
                                button.setBackground(Color.MAGENTA);
                                System.out.println("End: " + finalI + ',' + finalJ);
                                if (end != null) buttons[end[0]][end[1]].setBackground(Color.WHITE);
                                end = new int[]{finalI, finalJ};
                            }
                            case 'o' -> { // Obstacle
                                button.setBackground(Color.GRAY);
                                System.out.println("Obstacle: " + finalI + ',' + finalJ);
                                obstacle.add(new int[]{finalI, finalJ}); // Add cords to list
                            }
                        }
                    }
                });
                grid.add(buttons[i][j]);
            }
        }
        frame.setVisible(true);
    }

    public void setBackground(int[] pos, Color color) {
        buttons[pos[0]][pos[1]].setBackground(color);
    }
    public void setText(int[] pos, String text) {
        buttons[pos[0]][pos[1]].setText(text);
    }

    public ArrayList<int[]> getObstacle() {
        return obstacle;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }
}
