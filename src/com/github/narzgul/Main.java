package src.com.github.narzgul;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Node[][] nodes = new Node[20][10];
        System.out.println(nodes.length);

        GUI gui = new GUI(nodes.length, nodes[0].length);
        System.out.println("Fertig");
    }
}
