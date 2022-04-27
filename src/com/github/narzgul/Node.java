package src.com.github.narzgul;

public class Node implements Comparable<Node>{
    private int gCost;
    private int hCost;
    private char special;
    private final int[] pos;
    private Node parent;
    public Node(char special, int[] pos) {
        this.special = special;
        this.pos = pos;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public char getSpecial() {
        return special;
    }

    public void setSpecial(char special) {
        this.special = special;
    }

    public int[] getPos() {
        return pos;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.getFCost() - otherNode.getFCost();
    }
}
