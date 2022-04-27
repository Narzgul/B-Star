package src.com.github.narzgul;

import java.util.ArrayList;
import java.util.Collections;

public class Pathfinder {
    Node[][] nodes;
    ArrayList<Node> openNodes = new ArrayList<>();
    ArrayList<Node> closedNodes = new ArrayList<>();
    int[] start, end;

    public Pathfinder(Node[][] nodes, int[] start, int[] end) {
        this.nodes = nodes;
        this.start = start;
        this.end = end;
    }

    public void start() {
        System.out.println("Started Pathfinder");

        openNodes.addAll(getNeighbors(new int[] {start[0], start[1]}));
        for (Node openNode : openNodes){
            openNode.setGCost(getDistance(openNode, start));
            openNode.setHCost(getDistance(openNode, end));
            System.out.println(openNode.getFCost());
        }
        System.out.println("------------------");
        Collections.sort(openNodes);
        for (Node openNode : openNodes) {
            System.out.println(openNode.getFCost());
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
                            if (nodes[i][j].getSpecial() == ' ') neighbors.add(nodes[i][j]); // Add empty Node
                        }
                    }
                }
            }
        }

        return neighbors;
    }
}