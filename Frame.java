import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*This is the code for the interactive part of this project. User can choose the position of starting and end point
as well as draw the walls where the path cannot traverse. Currently undergoing construction*/
public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    private Pathfinding pathfinding;
    private JFrame window;
    private int key;
    int size;
    int[][] map;
    Node start,end;
    public static void main(String[] args){
        new Frame();

    }

    public Frame(){
        key = 0;
        size = 25;
        map = new int[size][size];
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        pathfinding = new Pathfinding(this,map,true);

        window = new JFrame();
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(700, 700));
        map = new int[window.getWidth()][window.getHeight()];
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int height = getHeight();
        int width = getWidth();
        /*Draws the grids*/
        g.setColor(Color.lightGray);
        for (int j = 0; j < this.getHeight(); j += size) {
            for (int i = 0; i < this.getWidth(); i += size) {
                g.drawRect(i, j, size, size);
            }
        }
        /*Draws the walls/borders*/
        g.setColor(Color.black);
        for(int i = 0; i < pathfinding.getWalls().size(); i++){
            g.fillRect(pathfinding.getWalls().get(i).getX()+1,pathfinding.getWalls().get(i).getY() +1,size -1, size -1);

        }
        /*Draws all open nodes*/
        for (int i = 0; i < pathfinding.getOpen().size(); i++) {
            Node current = pathfinding.getOpen().get(i);
            g.setColor(new Color(132,255,138));
            g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);
        }
        /*Draws all closed nodes*/
        for (int i = 0; i < pathfinding.getClosed().size(); i++) {
            Node current = pathfinding.getClosed().get(i);
            g.setColor(new Color(253, 90, 90));
            g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);
        }
        /*Draws all the path nodes with the lowest F cost*/
        for(int i = 0; i < pathfinding.getPath().size(); i++){
            Node current = pathfinding.getPath().get(i);
            g.setColor(new Color(32, 233, 255));
            g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);
        }
        /*Draws the start node on map(not yet implemented*/
        if (start != null) {
            g.setColor(Color.blue);
            g.fillRect(start.getX() + 1, start.getY() + 1, size - 1, size - 1);
        }
        /*Draws the end node on the map(not yet implemented)*/
        if (end != null) {
            g.setColor(Color.red);
            g.fillRect(end.getX() + 1, end.getY() + 1, size - 1, size - 1);
        }
    }

    public void mapInteractions(MouseEvent e){
        if (SwingUtilities.isLeftMouseButton(e)) {
            /*Creating a wall by getting x and y of where the mouse is clicked*/
            int xBorder = e.getX() - (e.getX() % size);
            int yBorder = e.getY() - (e.getY() % size);

            for(int i = 0; i < map.length; i+=size){
                for(int j = 0; j <map[i].length; j+=size){
                    map[xBorder][yBorder] = 100;
                }
            }


            Node newBorder = new Node(null, xBorder, yBorder, 0, 0);
            pathfinding.addWalls(newBorder);

            repaint();
        }else if(SwingUtilities.isRightMouseButton(e)){
            System.out.println("Yaya");
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pathfinding.findPathTo(25,25);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mapInteractions(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mapInteractions(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
