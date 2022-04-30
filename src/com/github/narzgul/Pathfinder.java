package src.com.github.narzgul;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pathfinder implements Runnable{
    private final Node[][] nodes;
    private final ArrayList<Node> openNodes = new ArrayList<>();
    private final ArrayList<Node> closedNodes = new ArrayList<>();
    private final int[] start, end;
    private final GUI gui = Main.getInstance().getGui(); // Get GUI from Main
    public Pathfinder(Node[][] nodes, int[] start, int[] end) {
        this.nodes = nodes;
        this.start = start;
        this.end = end;
    }

    public void run() {
        System.out.println("Started Pathfinder");

        nodes[start[0]][start[1]].setHCost(getDistance(nodes[start[0]][start[1]], end));
        openNodes.add(0, nodes[start[0]][start[1]]); // Makes start first openNode
        Node currentNode;
        do {
            Collections.sort(openNodes); // Sort by FCost
            try {
                currentNode = openNodes.get(0); // Get node with lowest FCost
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Couldn't find a path!");
                gui.showErrorDialog("Path", "Could not find valid path to the end!");
                return;
            }
            openNodes.remove(currentNode); // Move currentNode to closedNodes
            closedNodes.add(currentNode);

            gui.setText(currentNode.getPos(), "" + currentNode.getFCost());
            gui.setBackground(currentNode.getPos(), Color.RED);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Node neighbor : getNeighbors(currentNode.getPos())) {
                if (!openNodes.contains(neighbor)) {
                    neighbor.setGCost(currentNode.getGCost() + getDistance(currentNode, neighbor.getPos()));
                    neighbor.setHCost(getDistance(neighbor, end));
                    gui.setBackground(neighbor.getPos(), Color.GREEN);
                    neighbor.setParent(currentNode);
                    openNodes.add(0, neighbor);
                } else {
                    // Check for new shortest path to neighbor
                    int newGCost = currentNode.getGCost() + getDistance(currentNode, neighbor.getPos());
                    if (newGCost < neighbor.getGCost()) {
                        neighbor.setGCost(newGCost);
                        neighbor.setParent(currentNode);
                    }
                }
            }
        } while (currentNode.getPos() != nodes[end[0]][end[1]].getPos());

        // Mark shortest path cyan
        while (currentNode != nodes[start[0]][start[1]]) {
            gui.setBackground(currentNode.getPos(), Color.CYAN);
            currentNode = currentNode.getParent();
        }
    }

    public int getDistance(Node node, int[] point) {
        int disX = Math.abs(point[0] - node.getPos()[0]); // Distance to point in X-Direction
        int disY = Math.abs(point[1] - node.getPos()[1]); // Distance to point in Y-Direction

        // Add diagonal (14) by looking for shortest distance (X || Y) + other direction (10)
        int a = disX < disY ? (14 * disX) + 10 * (disY - disX) : (14 * disY) + 10 * (disX - disY);
        int b = 10 * (Math.abs(point[0] - node.getPos()[0]) + Math.abs(point[1] - node.getPos()[1])); // Manhattan (?)
        int c = (int) (10 * Math.sqrt(disX*disX + disY*disY)); // Pythagoras
        System.out.println("A: " + a + "; B:" + b + "; C: " + c);
        System.out.println("Node: " + Arrays.toString(node.getPos()) + "; point: " + Arrays.toString(point));
        return c;
    }

    private ArrayList<Node> getNeighbors(int[] cords) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = cords[0] -1; i <= cords[0] +1; i++) {
            if (i >= 0 && i < nodes.length) { // X-Cord is in grid
                for (int j = cords[1] -1; j <= cords[1] + 1; j++) {
                    if (j >= 0 && j < nodes[0].length) { // Y-Cord is in grid
                        if (!(i == cords[0] && j == cords[1])) { // Is not the start
                            if (nodes[i][j].getSpecial() == ' ') { // Empty Node
                                if (!closedNodes.contains(nodes[i][j])) neighbors.add(nodes[i][j]);
                            }
                        }
                    }
                }
            }
        }

        return neighbors;
    }
}
