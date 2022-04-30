package src.com.github.narzgul;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Pathfinder implements Runnable{
    private final Node[][] nodes;
    private final ArrayList<Node> openNodes, closedNodes;
    private final int[] start, end;
    private final GUI gui;
    public Pathfinder(Node[][] nodes, int[] start, int[] end) {
        this.nodes = nodes;
        this.start = start;
        this.end = end;
        openNodes = new ArrayList<>();
        closedNodes = new ArrayList<>();
        gui = Main.getInstance().getGui(); // Get GUI from Main
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
            } catch (IndexOutOfBoundsException e) { // If List is Empty
                System.out.println("Couldn't find a path!");
                gui.showErrorDialog("Path", "Could not find valid path to the end!");
                return;
            }
            openNodes.remove(currentNode); // Move currentNode to closedNodes
            closedNodes.add(currentNode);

            gui.setText(currentNode.getPos(), "" + currentNode.getFCost()); // Set Text & Color of the Button
            gui.setBackground(currentNode.getPos(), Color.RED);
            try {
                //noinspection BusyWait (For ItelliJ to stop complaining)
                Thread.sleep(50); // Wait 50ms for animation
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Node neighbor : getNeighbors(currentNode.getPos())) {
                if (!openNodes.contains(neighbor)) {
                    neighbor.setGCost(currentNode.getGCost() + getDistance(currentNode, neighbor.getPos()));
                    neighbor.setHCost(getDistance(neighbor, end));
                    gui.setBackground(neighbor.getPos(), Color.GREEN); // Indicate openNode
                    neighbor.setParent(currentNode);
                    openNodes.add(0, neighbor); // Add to openList
                } else {
                    // Check for new shortest path to neighbor
                    int newGCost = currentNode.getGCost() + getDistance(currentNode, neighbor.getPos());
                    if (newGCost < neighbor.getGCost()) { // Is new path shorter?
                        neighbor.setGCost(newGCost);
                        neighbor.setParent(currentNode);
                    }
                }
            }
        } while (currentNode.getPos() != nodes[end[0]][end[1]].getPos()); // Is end Node

        // Mark shortest path cyan
        while (currentNode != nodes[start[0]][start[1]]) { // Is start Node
            gui.setBackground(currentNode.getPos(), Color.CYAN);
            currentNode = currentNode.getParent();
        }
    }

    public int getDistance(Node node, int[] point) {
        int disX = Math.abs(point[0] - node.getPos()[0]); // Distance to point in X-Direction
        int disY = Math.abs(point[1] - node.getPos()[1]); // Distance to point in Y-Direction

        return (int) (10 * Math.sqrt(disX*disX + disY*disY));
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
