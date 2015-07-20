
package isassignment;

import java.util.ArrayList;
import java.lang.Math;

public class A_Star {
    private static int sourceVal, destVal;
    private static Map tempMap;
    private static ArrayList<Node> Graph=new ArrayList();
    //private static ArrayList<Node> outputSeq = new ArrayList();
    
    private static Node startNode= new Node(0, 0, 0);
    private static Node endNode = new Node(-1, -1, 0);
    
    private static ArrayList<Node> closed_set=new ArrayList();
    private static ArrayList<Node> open_set= new ArrayList();
    
    private static  int[][] g_score;
    private  static int[][] f_score;
    
    private static long startTime;
    private static long estimatedTime;
    
    
    
    public static void A_Star(Map map, int start, int dest){
        tempMap=map;
        sourceVal=start;
        destVal=dest;
        ArrayList outputSeq= new ArrayList();
        Graph.addAll(tempMap.getGraph());
        f_score= new int[tempMap.getSize()][tempMap.getSize()];
        g_score= new int[tempMap.getSize()][tempMap.getSize()];
        
        for(Node x : Graph){
            if(x.getValue()== sourceVal){
                startNode=x;
            }
            if(x.getValue()== destVal){
                endNode=x;
            }
        }
        
//        for(int i=0; i<tempMap.getSize() ;i++){
//            for(int j=0; j< tempMap.getSize();j++){
//                g_score[i][j]=0;
//                f_score[i][j]=0;
//            }
//        }
    }
    
    public static ArrayList<Node> run(){
        open_set.add(startNode);
        
        Node[][] came_from = new Node[tempMap.getSize()][tempMap.getSize()];
        
        //initializing the nodes in 'came_from' 2-d Node Array
        for (int i = 0; i < tempMap.getSize() ; i++) {
                for (int j = 0; j <tempMap.getSize() ; j++){
                    came_from[i][j] = new Node(-1, -1, 0);
                }
        }
        
        g_score[startNode.getX()][startNode.getY()] = 0;
        f_score[startNode.getX()][startNode.getY()] = g_score[startNode.getX()][startNode.getY()] + heuristicEstimate(startNode, endNode);
    
        Node current= new Node(-1, -1, 0);
        current = startNode;
        
        ArrayList<Node> path = new ArrayList<>();
        
        while(open_set.size() != 0){
            current = min_f_cost(open_set, endNode);
            if (current.getX() == endNode.getX() && current.getY() == endNode.getY())
                {
                    //write the code for console output
                    path = reconstructPath(came_from, current);
                    return path;
                }
            
            open_set.remove(current);
            closed_set.add(current);

            ArrayList<Node> neighbours = findNeighbours(current);
            for (int i = 0; i < neighbours.size(); i++){

                if (searchBlocks(closed_set, neighbours.get(i)))
                    continue;
                int tentative_g_score = g_score[current.getX()][current.getY()] + distanceBetween(current, neighbours.get(i));

                if (!searchBlocks( open_set, neighbours.get(i)) || tentative_g_score < g_score[neighbours.get(i).getX()][neighbours.get(i).getY()])
                {
                    came_from[neighbours.get(i).getX()][ neighbours.get(i).getY()] = current;
                    g_score[neighbours.get(i).getX()][ neighbours.get(i).getY()] = tentative_g_score;
                    f_score[neighbours.get(i).getX()][ neighbours.get(i).getY()] = g_score[neighbours.get(i).getX()][ neighbours.get(i).getX()] + heuristicEstimate(neighbours.get(i), endNode);
                    if (!searchBlocks(open_set, neighbours.get(i)))
                    {
                        open_set.add(neighbours.get(i));

                    }
                }
            }
        }
        
        path.add(startNode);
        return path;
        //write the code to return the path 
    }
    
    private static int heuristicEstimate(Node start, Node dest)
        {
            return Math.abs(start.getX() - dest.getX()) + Math.abs(start.getY() - dest.getY());
        }
    
    public static Node min_f_cost(ArrayList<Node> list, Node goal){
            int min = g_score[list.get(0).getX()][list.get(0).getY()] + heuristicEstimate(list.get(0), goal);
            Node index = new Node(-1, -1, 0);
            index = list.get(0);
                    
            for (int i = 0; i < list.size() ; i++)
            {
                int f_cost = f_score[list.get(i).getX()][list.get(i).getY()] = g_score[list.get(i).getX()][ list.get(i).getY()] + heuristicEstimate(list.get(i), goal);
                if (f_cost < min)
                {
                    min = f_cost;
                    index = list.get(i);
                }
            }
            return index;
        }
    
    public static ArrayList<Node> reconstructPath(Node[][] came_from, Node current){
        ArrayList<Node> total_path = new ArrayList<Node>();
        total_path.add(current);

        Node temp= new Node(-1, -1, 0);
        //temp = current;

        while (came_from[current.getX()][ current.getY()] != temp){
            current = came_from[current.getX()][ current.getY()];
            total_path.add(current);  
        }

        return total_path;
    }

    public static boolean searchBlocks(Node[] blocks, Node point)
        {
            for (int i = 0; i < blocks.length; i++)
            {
                if (blocks[i].getX() == point.getX() && blocks[i].getY() == point.getY())
                    return true;
            }
            return false;
        }

    public static boolean searchBlocks(ArrayList<Node> blocks, Node point)
        {
            for (int i = 0; i < blocks.size(); i++)
            {
                if (blocks.get(i).getX() == point.getX() && blocks.get(i).getY() == point.getY())
                {
                    return true;
                }
            }

            return false;
        }
    
    public static int distanceBetween(Node current, Node neighbour)
        {
            int current_x = current.getX();
            int current_y = current.getY();
            int neighbour_x = neighbour.getX();
            int neighbour_y = neighbour.getY();
            if ((current_x == neighbour_x) && (current_y != neighbour_y))
                return 1;
            if ((current_x != neighbour_x) && (current_y == neighbour_y))
                return 1;
            else return 1000;
        }

    public static ArrayList<Node> findNeighbours(Node current){
        int i = current.getY();
        int j = current.getX();

        ArrayList<Node> neighbours = new ArrayList<Node>();
        for (int y = i - 1; y <= i + 1; y++)
        {
            for (int x = j - 1; x <= j + 1; x++)
            {
                if (x >= 0 && x <= tempMap.getSize() - 1 && y >= 0 && y <= tempMap.getSize() - 1)
                {
                    Node point = new Node(x, y,-1);
                    if (!(y == i && x == j))
                        neighbours.add(point);
                }
            }
        }

        return neighbours;
    }
    
    public static void printPath(){
        ArrayList<Node> finalPath = run();
        System.out.println("Final Path:");
        for (Node node : finalPath){
            System.out.println(node.getValue()+" ");
        }
    }
}
