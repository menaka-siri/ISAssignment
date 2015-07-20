package isassignment;

import static isassignment.A_Star.A_Star;
import static isassignment.BFS.BFS;
import static isassignment.DFS.DFS;

/**
 *
 * @author Menaka Lahiru Sirisena
 * index: 120628C
 */

public class ISAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map map= new Map(5);
        //map.printMap();
        //System.out.println();
        //map.printGraph();
        
        /* --- executing BFS ---*/
//        BFS(map,2,25);
//        DFS(map,2,25);
//        long startTime = System.currentTimeMillis();
//        BFS.run();
//        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println("\nTime ELapsed - BFS (in milliseconds): ");
//        System.out.println(estimatedTime);
//        System.out.println("");
        
        /* --- executing DFS ---*/
//        map= new Map(5);
//        DFS(map,2,25);
//        DFS.run();
        
        
        map= new Map(5);
        A_Star(map,2,25);
        A_Star.printPath();
    }
    
}
