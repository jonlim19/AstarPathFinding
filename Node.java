public class Node implements Comparable{
    private Node parent;
    private int x,y;
    private double g;
    private double h;
    private double f;
    public Node(Node parent, int xpos, int ypos, double g, double h){
        this.parent = parent;
        this.x = xpos;
        this.y = ypos;
        this.g = g;
        this.h = h;
    }

    public Node(int xpos, int ypos){
        this.x = xpos;
        this.y = ypos;
    }
    @Override
    public int compareTo(Object o) {
        Node that = (Node) o;
        return (int)((this.g + this.h) -(that.g + that.h));
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public Node getParent() {

        return parent;
    }

    public double getH() {
        return h;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }
}
