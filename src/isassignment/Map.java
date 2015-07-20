package isassignment;

import java.util.ArrayList;

public class Map {
    private int[][] grid;
    private int maxVal;
    private int size;
    //private Node[] graph;
    private ArrayList<Node> graph= new ArrayList<>();
     
    //Map constructor
    public Map(int size){
        this.size=size;
        this.grid= new int[size][size];
        this.maxVal=size*size;
        //this.graph=new Node[maxVal];
        
        /*  instantiation the Map --- starts --- (including the grid and the graph) */
        
        //generating the grid
        for(int i=0; i<size;i++){
            for(int j=size-1;j>=0;j--){
                grid[i][j]=maxVal;
                maxVal--;
            }
        }
        
        this.maxVal=size*size; //restore the maxVal to its initial value.
        
        //generating the graph
        Node tempNode;
        for(int i=0; i<size;i++){
            for(int j=0;j<size;j++){
                //grid[i][j];
                tempNode=new Node(i,j, grid[i][j]);
                
                //adding child nodes in the four directions
                    //to the right direction
                    if( j < (size-1) ){
                        Node tempChildNode= new Node(i,j+1,grid[i][j+1]);
                        tempNode.addChild(tempChildNode);
                    }
                    
                    //to the left direction
                    if( j > 0 ){
                        Node tempChildNode= new Node(i,j-1,grid[i][j-1]);
                        tempNode.addChild(tempChildNode);
                    }
                    
                    //to the top direction
                    if( i > 0 ){
                        Node tempChildNode= new Node(i-1,j,grid[i-1][j]);
                        tempNode.addChild(tempChildNode);
                    }
                    
                    //to the down direction
                    if( i < (size-1) ){
                        Node tempChildNode= new Node(i+1,j,grid[i+1][j]);
                        tempNode.addChild(tempChildNode);
                    }
                    
                //adding the tempNode to the graph
                graph.add(tempNode);
                
            }
        }
        
        /*  instantiation the Map --- ends   */
    }

    //print the map
    public void printMap(){
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                //System.out.print(grid[i][j] + " ");
                System.out.format("%2d ",grid[i][j]);
            }
            System.out.println("");
        }
    }
    
    public ArrayList<Node> getGraph(){
        return this.graph;
    }
    
    //print the graph
    public void printGraph(){
        int index=0;
        for(int i=0; i<5;i++){
            for(int j=0;j<5;j++){
                //System.out.print(grid[i][j] + " ");
                System.out.format("%2d ",graph.get(index).getValue());
                index++;
            }
            System.out.println("");
        }
    }
    
    public int getSize() {
        return size;
    }
}
