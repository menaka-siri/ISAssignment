
package isassignment;


public class Temp {
    
}

public List<Coordinates> A_star(Coordinates start, Coordinates goal)
        {
            Closed_set.Clear();
            Open_set.Clear();


            Open_set.Add(start);
            Coordinates[,] came_from = new Coordinates[(GameConstants.grid), (GameConstants.grid)];
            for (int i = 0; i < GameConstants.grid; i++)
            {
                for (int j = 0; j < GameConstants.grid; j++)
                {
                    came_from[i, j] = null;
                }

            }
            
            g_score[start.X, start.Y] = 0;
            f_score[start.X, start.Y] = g_score[start.X, start.Y] + heuristic_cost_estimate(start, goal);

            Coordinates current = start;

            List<Coordinates> path = new List<Coordinates>();
            while (Open_set.Count != 0)
            {

                current = min_f_cost(Open_set, goal);
                if (current.X == goal.X && current.Y == goal.Y)
                {
                    Console.WriteLine();
                    path = reconstruct_path(came_from, current);
                    return path;

                }

                Open_set.Remove(current);
                Closed_set.Add(current);

                List<Coordinates> neighbours = find_neighbours(current);
                for (int i = 0; i < neighbours.Count; i++)
                {

                    if (searchBlocks(Closed_set, neighbours.ElementAt(i)))
                        continue;
                    int tentative_g_score = g_score[current.X, current.Y] + dist_between(current, neighbours.ElementAt(i));

                    if (!searchBlocks(Open_set, neighbours.ElementAt(i)) || tentative_g_score < g_score[neighbours.ElementAt(i).X, neighbours.ElementAt(i).Y])
                    {
                        came_from[neighbours.ElementAt(i).X, neighbours.ElementAt(i).Y] = current;
                        g_score[neighbours.ElementAt(i).X, neighbours.ElementAt(i).Y] = tentative_g_score;
                        f_score[neighbours.ElementAt(i).X, neighbours.ElementAt(i).Y] = g_score[neighbours.ElementAt(i).X, neighbours.ElementAt(i).Y] + heuristic_cost_estimate(neighbours.ElementAt(i), goal);
                        if (!searchBlocks(Open_set, neighbours.ElementAt(i)))
                        {
                            Open_set.Add(neighbours.ElementAt(i));
                            
                        }
                    }
                }
            }
            path.Add(start);
            path.Add(start);
            return path;
            
        }