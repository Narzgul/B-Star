package src.com.github.narzgul;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pathfinder {
    Node[][] nodes;
    ArrayList<Node> openNodes = new ArrayList<>();
    ArrayList<Node> closedNodes = new ArrayList<>();
    int[] start, end;
    GUI gui = Main.getInstance().gui;
    public Pathfinder(Node[][] nodes, int[] start, int[] end) {
        this.nodes = nodes;
        this.start = start;
        this.end = end;
    }

    public void start() {
        System.out.println("Started Pathfinder");

        openNodes.add(nodes[start[0]][start[1]]);
        Node currentNode = openNodes.get(0);
        while (currentNode.getPos() != nodes[end[0]][end[1]].getPos()) {
            Collections.sort(openNodes);
            currentNode = openNodes.get(0);
            gui.setText(currentNode.getPos(), "" + currentNode.getFCost());
            openNodes.remove(currentNode);
            closedNodes.add(currentNode);
            gui.setBackground(currentNode.getPos(), Color.RED);
            for (Node neighbor : getNeighbors(currentNode.getPos())) {
                if (!openNodes.contains(neighbor)) {
                    neighbor.setGCost(currentNode.getGCost() + getDistance(currentNode, neighbor.getPos()));
                    neighbor.setHCost(getDistance(neighbor, end));
                    gui.setBackground(neighbor.getPos(), Color.GREEN);
                    neighbor.setParent(currentNode);
                    openNodes.add(neighbor);
                } else {
                    int newGCost = currentNode.getGCost() + getDistance(currentNode, neighbor.getPos());
                    if (newGCost < neighbor.getGCost()) {
                        neighbor.setGCost(newGCost);
                        neighbor.setParent(currentNode);
                    }
                }
            }
        }
        while (currentNode != nodes[start[0]][start[1]]) {
            gui.setBackground(currentNode.getPos(), Color.CYAN);
            currentNode = currentNode.getParent();
        }
    }

    public int getDistance(Node node, int[] point) {
        int disX = Math.abs(point[0] - node.getPos()[0]);
        int disY = Math.abs(point[1] - node.getPos()[1]);

        return disX < disY ? (14 * disX) + 10 * (disY - disX) : (14 * disY) + 10 * (disX - disY);
    }

    private ArrayList<Node> getNeighbors(int[] cords) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = cords[0] -1; i <= cords[0] +1; i++) {
            if (i >= 0 && i < nodes.length) { // X-Cord is in grid
                for (int j = cords[1] -1; j <= cords[1] + 1; j++) {
                    if (j >= 0 && j < nodes[0].length) { // Y-Cord is in grid
                        if (!(i == cords[0] && j == cords[1])) { // Is not the start
                            if (nodes[i][j].getSpecial() == ' ') {
                                if (!closedNodes.contains(nodes[i][j])) neighbors.add(nodes[i][j]); // Add empty Node
                            }
                        }
                    }
                }
            }
        }

        return neighbors;
    }
}
