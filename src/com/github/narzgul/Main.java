package src.com.github.narzgul;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Node[][] nodes = new Node[20][20];
        int[] start, end;
        System.out.println(nodes.length);
        Object notifier = new Object();

        GUI gui = new GUI(nodes.length, nodes[0].length);
        gui.setNotifier(notifier);
        System.out.println(gui.getObstacle());
        synchronized (notifier) {
            notifier.wait();
        }
        System.out.println("Done placing!");

        start = gui.getStart();
        end = gui.getEnd();
        nodes[start[0]][start[1]] = new Node('s'); // Map Start
        nodes[end[0]][end[1]] = new Node('e'); // Map End
        // Map obstacles to the Node array
        for (int[] obstacle : gui.getObstacle()) nodes[obstacle[0]][obstacle[1]] = new Node('o');

        ArrayList<Node> openNodes = new ArrayList<>();
        for (int i = start[0] -1; i <= start[0] +1; i++) {
            if (i >= 0 && i < nodes.length) { // X-Cord is in grid
                for (int j = start[1]; j <= start[1] + 1; j++) {
                    if (j >= 0 && j < nodes[0].length) { // Y-Cord is in grid
                        if (i != start[0] && j != start[1]) { // Is not the start
                            openNodes.add(nodes[i][j] = new Node(' ')); // Add empty Node
                        }
                    }
                }
            }
        }
    }
}
