package src.com.github.narzgul;

import java.util.ArrayList;

public class Pathfinder {
    Node[][] nodes;

    public Pathfinder(Node[][] nodes) {
        this.nodes = nodes;
    }

    public void start() {
        System.out.println("Started Pathfinder");
    }

    private ArrayList<Node> getNeighbors(int[] cords) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = cords[0] -1; i <= cords[0] +1; i++) {
            if (i >= 0 && i < nodes.length) { // X-Cord is in grid
                for (int j = cords[1]; j <= cords[1] + 1; j++) {
                    if (j >= 0 && j < nodes[0].length) { // Y-Cord is in grid
                        if (i != cords[0] && j != cords[1]) { // Is not the start
                            System.out.println("[" + i + "," + j + "]");
                            neighbors.add(nodes[i][j] = new Node(' ')); // Add empty Node
                        }
                    }
                }
            }
        }

        return neighbors;
    }
}
