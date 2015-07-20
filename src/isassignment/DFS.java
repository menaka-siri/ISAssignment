package isassignment;

import java.util.ArrayList;

public class DFS {
    private static int sourceVal, destVal;
    private static Map tempMap;
    private static ArrayList<Node> DFSGraph=new ArrayList();
    private static ArrayList<Node> outputSeq = new ArrayList();;
    
    private static long startTime;
    private static long estimatedTime;
    
    public static void DFS(Map map, int start, int dest){
        tempMap=map;
        sourceVal=start;
        destVal=dest;
        ArrayList outputSeq= new ArrayList();
        DFSGraph.addAll(tempMap.getGraph()); 
    }
    
    public static void run(){
        startTime = System.currentTimeMillis();
        for(Node tempNode : DFSGraph){
            if (tempNode.getValue() == sourceVal){ //finding the Source Node
                tempNode.setColour((byte)0);
                tempNode.setDistance((byte)0);
                System.out.print("--DFS--\nDiscovered Sequence:\n");
                DFSVisit(tempNode);
                break;
            }
        }       
    }
    
    private static void DFSVisit(Node u){
            Node tempNode =u;
            System.out.print("|");
            System.out.print(" "+tempNode.getValue()+" ");
            
            outputSeq.add(tempNode);
            
            //set the colour of the discovered node to Gray
            for(Node x : DFSGraph){
                if(tempNode.getValue()== x.getValue()){
                    x.setColour((byte)0); //set the colour to Gray
                }
            }
            
            ArrayList<Node> tempChildSet = new ArrayList<>();
            tempChildSet.addAll(tempNode.getChildSet());
            
            //System.out.print("\nNode "+tempNode.getValue()+"'s childset :"); //for debugging purposes
            
            for(Node child : tempChildSet){
                for(Node v : DFSGraph){
                    if(child.getValue()==v.getValue())
                        //System.out.print(child.getValue()+"**");  //for debugging purposes
                    
                    if(child.getValue()==v.getValue() && v.getColour()==-1){
                        v.setDistance(tempNode.getDistance() + 1);
                        v.setParent(tempNode.getValue());
                        DFSVisit(v);
                    }     
                }
            }
            
            //set the colour of the finished to Black
            for(Node x : DFSGraph){
                if(tempNode.getValue()== x.getValue()){
                    x.setColour((byte)1); //set the colour to Black
                }
            }

            if(tempNode.getValue()== destVal ){
                //System.out.println("\nOS size: when tempNode == destNode : "+outputSeq.size());  //for debugging purposes
                backTrack(); //calling the backtrack method to print the path
            }
 
    }
    
    private static void backTrack(){
        estimatedTime = System.currentTimeMillis() - startTime;
        
        //assigning the first node in output sequence to tempX
        //to initialize the tempX variable
        Node tempX= outputSeq.get(1);
        for(Node x : outputSeq){
                if(x.getValue()==destVal){  //selecting the Dest. node in output sequence.
                    tempX=x;
                    }
            }
        
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
        //System.out.println("Back tracking ended.\n");
        System.out.println("\nTime ELapsed - DFS (in milliseconds): ");
        System.out.println(estimatedTime);
        System.exit(0);
    }
}
