package src.com.github.narzgul;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Node[][] nodes = new Node[20][10];
        System.out.println(nodes.length);
        Object notifier = new Object();

        GUI gui = new GUI(nodes.length, nodes[0].length);
        gui.setNotifier(notifier);
        System.out.println(gui.getObstacle());
        synchronized (notifier) {
            notifier.wait();
        }
        System.out.println("Done");
    }
}
