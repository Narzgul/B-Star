package src.com.github.narzgul;

import java.util.ArrayList;

public class Main {
    private static Main instance = null;
    Node[][] nodes;
    int[] start, end;
    GUI gui;

    private Main() {
        nodes = new Node[20][20];
        System.out.println(nodes.length);

        gui = new GUI(nodes.length, nodes[0].length);


        ArrayList<Node> openNodes = new ArrayList<>();

    }

    public void startPathfinder() {
        start = gui.getStart();
        end = gui.getEnd();
        nodes[start[0]][start[1]] = new Node('s'); // Map Start
        nodes[end[0]][end[1]] = new Node('e'); // Map End

        // Map obstacles to the Node array
        for (int[] obstacle : gui.getObstacle()) nodes[obstacle[0]][obstacle[1]] = new Node('o');

        Pathfinder pathfinder = new Pathfinder(nodes);
        pathfinder.start();
    }

    public static Main getInstance(){
        if (instance == null) instance = new Main(); // Ensures only 1 Main

        return instance;
    }

    public static void main(String[] args){
        Main main = Main.getInstance();
    }
}
