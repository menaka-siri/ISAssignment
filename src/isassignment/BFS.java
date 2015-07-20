package isassignment;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException; //used when the program tries to remove an element from an empty Queue

//Takes a Map as input along with the source node and destination node

public class BFS {
    private static Queue<Node> queue= new LinkedList();
    private static int sourceVal, destVal;
    private static Map tempMap;
    private static ArrayList<Node> BFSGraph=new ArrayList();
    private static ArrayList<Node> outputSeq = new ArrayList();;
    
    public static void BFS(Map map, int start, int dest){
        tempMap=map;
        sourceVal=start;
        destVal=dest;
        
        
        ArrayList outputSeq= new ArrayList();
        
        BFSGraph.addAll(tempMap.getGraph());
        
//        for(Node b : BFSGraph){
//            System.out.println(b.getValue());
//        }
    }
    
    public static void run(){
        for(Node tempNode : BFSGraph){
            if (tempNode.getValue() == sourceVal){ //finding the Source Node
                tempNode.setColour((byte)0);
                tempNode.setDistance((byte)0);
                //System.out.println("in Run first for loop ");
                queue.add(tempNode);
                break;
            }
        }
                
        System.out.print("--BFS--\nDiscovered Sequence:\n");
        
        while(queue.peek() != null ){
            Node tempNode =queue.poll();
            System.out.print("|");
            System.out.print(" "+tempNode.getValue()+" ");
            
            
            
            ArrayList<Node> tempChildSet = new ArrayList<>();
            tempChildSet.addAll(tempNode.getChildSet());
            for(Node child : tempChildSet){
                for(Node v : BFSGraph){
                    if(child.getValue()==v.getValue() && v.getColour()==-1){
                        v.setColour((byte)0);
                        v.setDistance(tempNode.getDistance() + 1);
                        v.setParent(tempNode.getValue());
                        queue.add(v);
                    }     
                }
            }
            
            for(Node x : BFSGraph){
                if(tempNode.getValue()== x.getValue()){
                    x.setColour((byte)1); //set the colour to Black
                }
            }
            
            outputSeq.add(tempNode);
            
            if(tempNode.getValue()==destVal){
                //should call back tracking method.
                backTrack();
                break;
            }
        }
           
    }
    
    private static void backTrack(){
        Node tempX= outputSeq.get(outputSeq.size()-1);
        String path = "";
        while(tempX.getParent() != -1000){
            path = path.concat( Integer.toString( tempX.getValue())).concat(" ");
            for(Node x : outputSeq){
                if(x.getValue()==tempX.getParent()){
                    tempX=x;
                    }
            }
        }
        path = path.concat( Integer.toString( tempX.getValue())).concat(" ");
        System.out.println("\n\nBack tracked path from Source to Destination:");
        System.out.println(path);
    }
}
