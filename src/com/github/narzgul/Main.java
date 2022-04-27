package src.com.github.narzgul;

import java.util.ArrayList;

public class Main {
    private static Main instance = null;
    Node[][] nodes;
    int[] start, end;
    GUI gui;

    private Main() {
        nodes = new Node[20][20];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                nodes[i][j] = new Node(' ', new int[]{i, j});
            }
        }

        gui = new GUI(nodes.length, nodes[0].length);


        ArrayList<Node> openNodes = new ArrayList<>();

    }

    public void startPathfinder() {
        start = gui.getStart();
        end = gui.getEnd();
        nodes[start[0]][start[1]].setSpecial('s');// Map Start
        nodes[end[0]][end[1]].setSpecial(' '); // Map End

        // Map obstacles to the Node array
        for (int[] obstacle : gui.getObstacle()) nodes[obstacle[0]][obstacle[1]].setSpecial('o');

        Pathfinder pathfinder = new Pathfinder(nodes, start, end);
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
