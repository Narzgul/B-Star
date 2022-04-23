package src.com.github.narzgul;

public class Node {
    private int gCost;
    private int hCost;
    private int fCost;
    public Node() {

    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
        fCost = gCost + hCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
        fCost = gCost + hCost;
    }

    public int getFCost() {
        return fCost;
    }
}
