import java.util.List;
/*Main class for running the algorithm*/
public class Main{

    public Main(){

    }

    public static void main(String[] args) {

        int[][] maze = {
                {  0,  0,  0,  0,  0,  0,  0,  0},
                {  0,  0,  0,  0,  0,  0,  0,  0},
                {  0,  0,  0,100,100,100,  0,  0},
                {  0,  0,  0,  0,  0,100,  0,  0},
                {  0,  0,100,  0,  0,100,  0,  0},
                {  0,  0,100,  0,  0,100,  0,  0},
                {  0,  0,100,100,100,100,  0,  0},
                {  0,  0,  0,  0,  0,  0,  0,  0},
        };

        Pathfinding as = new Pathfinding(maze, 0, 0, true);
        List<Node> path = as.findPathTo(7, 7);
        if (path != null) {
            path.forEach((n) -> {
                System.out.print("[" + n.getX() + ", " + n.getY() + "] ");
                maze[n.getY()][n.getX()] = -1;
            });
            System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).getF());

            for (int[] maze_row : maze) {
                for (int maze_entry : maze_row) {
                    switch (maze_entry) {
                        case 0:
                            System.out.print("[_]");//Indicates the open areas
                            break;
                        case -1:
                            System.out.print("[*]");//Indicates the path
                            break;
                        default:
                            System.out.print("[#]");//Indicates the walls that cannot be traversed

                    }
                }
                System.out.println();
            }
        }
    }
}
