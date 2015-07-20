package isassignment;

import java.util.ArrayList;
import static javafx.application.Platform.exit;

public class Node {
    private int x=-1; // x position in the map
    private int y=-1; // y position in the map
    // (-)ve values of the coordinates shows that the node is not in the map.
    private int value=0;
    private ArrayList<Node> child=new ArrayList<>(); //array of child nodes
    //max. size of child array = 4

    
    /* attributes needed for search algorithms*/
    public void setParent(int parent) {
        this.parent = parent;
    }

    private byte colour= -1; // -1=white, 0=gray , 1=black
    private int distance=-1; //-1 = infinity
    private int parent=-1000; //stores the parent node's value
    
    //Node Constructor
    //array of child nodes is not instantiated in the constructor method.
    public Node(int x, int y,int value){
        this.x=x;
        this.y=y;
        this.value=value;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Node> getChildSet() {
        return child;
    }

    public void addChild(Node newChild) {
        if(child.size()<=5){
            child.add(newChild);
        }
        else{
            System.out.println("Maximum supported no. of child nodes = 4 !");
            System.out.println("Node(" + this.getX()+ "," + this.getY() + ") contains >4 children!");
            exit();
            
        }
    }
    
    public byte getColour() {
        return colour;
    }

    public void setColour(byte colour) {
        this.colour = colour;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getParent() {
        return parent;
    }

    
}
