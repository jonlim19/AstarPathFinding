import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pathfinding {
    private List<Node> open;
    private List<Node> closed;
    private List<Node> path;
    private List<Node> walls;
    private int[][] maze;
    private Frame frame;
    private int size;
    private Node current;
    private int xstart;
    private int ystart;
    private int xend, yend;
    private boolean diagonal;
    public List<Node> getPath() {
        return path;
    }

    public Pathfinding(int[][] maze, int xstart, int ystart, boolean diagonal){
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.maze = maze;
        this.current = new Node(null,xstart,ystart, 0, 0);
        this.xstart = xstart;
        this.ystart = ystart;
        this.diagonal = diagonal;
    }
    /*Constructor for the interactive part(using JFrame) under construction */
    public Pathfinding(Frame frame, int[][] maze,boolean diagonal){
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.maze = maze;
        this.walls = new ArrayList<>();
        this.diagonal = diagonal;
        this.frame = frame;
    }

    public List<Node> getOpen() {
        return open;
    }

    public List<Node> getClosed() {
        return closed;
    }

    public List<Node> getWalls() {
        return walls;
    }
    /*Adds the walls/borders to a list if there isn't a duplicate*/
    public void addWalls(Node n){
        if (walls.size() == 0) {
            walls.add(n);
        }else if(!checkWallDuplicate(n)){
            walls.add(n);
        }
    }
    /*Checks the x and y of a wall node to ensure that there is no repetition of the same cell*/
    public boolean checkWallDuplicate(Node node) {
        for (int i = 0; i < walls.size(); i++) {
            if (node.getX() == walls.get(i).getX() && node.getY() == walls.get(i).getY()) {
                return true;
            }
        }
        return false;
    }


    public List<Node> findPathTo(int xend, int yend){
        this.xend = xend;
        this.yend = yend;
        this.closed.add(this.current);
        addNeigborsToOpenList();
        while (this.current.getX() != this.xend || this.current.getY() != this.yend) {
            if (this.open.isEmpty()) {
                return null;
            }
            this.current = this.open.get(0);
            this.open.remove(0);
            this.closed.add(this.current);
            addNeigborsToOpenList();
        }
        this.path.add(0, this.current);
        while (this.current.getX() != this.xstart || this.current.getY() != this.ystart) {
            this.current = this.current.getParent();
            this.path.add(0, this.current);
        }
        return this.path;
    }

    public boolean findNeighbourInList(List<Node> array, Node node){
        return array.stream().anyMatch((n) -> (n.getX()==node.getX() && n.getY() == node.getY()));
    }

    private double distance(int dx, int dy){ // Calculates the H cost of the Starting Node
        if(this.diagonal){
            return Math.hypot(this.current.getX() + dx - this.xend,this.current.getY() + dy - this.yend);
        }else{
            return Math.abs(this.current.getX() + dx - this.xend) + Math.abs(this.current.getY() + dy -this.yend);
        }
    }

    public void addNeigborsToOpenList() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!this.diagonal && x != 0 && y != 0) {
                    continue; // skip if diagonal movement is not allowed
                }
                node = new Node(this.current, this.current.getX() + x, this.current.getY() + y, this.current.getG(), this.distance(x, y));

                if ((x != 0 || y != 0) // not this.current
                        && this.current.getX() + x >= 0 && this.current.getX() + x < this.maze[0].length // check maze boundaries
                        && this.current.getY() + y >= 0 && this.current.getY() + y < this.maze.length
                        && this.maze[this.current.getY() + y][this.current.getX() + x] != -1 // check if square is walkable
                        && !findNeighbourInList(this.open, node) && !findNeighbourInList(this.closed, node)) { // if not already done
                    node.setG(node.getParent().getG() + 1.); // Horizontal/vertical cost = 1.0
                    node.setG(node.getG() + maze[this.current.getY() + y][this.current.getX() + x]);
                    double fCost = node.getG() + node.getH();
                    node.setF(fCost);
                    this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);

    }

    public int getXstart() {
        return xstart;
    }

    public void setXstart(int xstart) {
        this.xstart = xstart;
    }

    public int getYstart() {
        return ystart;
    }

    public void setYstart(int ystart) {
        this.ystart = ystart;
    }


}
