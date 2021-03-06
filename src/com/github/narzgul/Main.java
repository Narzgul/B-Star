package src.com.github.narzgul;

public class Main {
    private static Main instance = null;
    private final Node[][] nodes;
    private final GUI gui;

    private Main() {
        nodes = new Node[20][20];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                nodes[i][j] = new Node(' ', new int[]{i, j}); // Fills array with empty nodes
            }
        }

        gui = new GUI(nodes.length, nodes[0].length, nodes);
    }

    public void startPathfinder() {
        int[] start = gui.getStart(); // Get Start + End position
        int[] end = gui.getEnd();
        nodes[start[0]][start[1]].setSpecial('s'); // Map Start

        new Thread(new Pathfinder(nodes, start, end)).start(); // Pathfinder in its own Thread
    }

    public GUI getGui() {
        return gui;
    }

    public static Main getInstance(){
        if (instance == null) instance = new Main(); // Ensures only 1 Main (Singleton)

        return instance;
    }

    public static void resetInstance() {
        instance = new Main();
    }
    public static void main(String[] args){
        Main.getInstance(); // Start Main
    }
}
