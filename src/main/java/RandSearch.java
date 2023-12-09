import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

public class RandSearch extends SearchTemplate implements SearchStrategy {

    @Override
    public Boolean visit(LinkedList<Integer> myList, int neighbor) {
        myList.add(neighbor);
        return true;
    }

    @Override
    public int removeHead(LinkedList<Integer> myList) {
        return myList.poll();
    }

    @Override
    public String Search(int src, int dst, int node, LinkedList<Integer>[] adj) {
        int newnode = node;                         //keeps the original node and adjascency list from the original call
        LinkedList<Integer>[] oldadj = adj;
        boolean visited[] = new boolean[node];
        int parent[] = new int[node];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[src] = true;
        queue.add(src);
        Random random = new Random();
        int randomIndex = random.nextInt(adj[src].size());
        int randomNeighbor = adj[src].get(randomIndex);             //gets the value for a random adjascent node

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == randomNeighbor) {
                StringBuilder path = new StringBuilder();
                node = randomNeighbor;
                while (node != src) {
                    path.insert(0, node + " "); //builds the string for the outout
                    node = parent[node];
                }
                path.insert(0, src + " ");
                String s = path.toString();
                if (adj[randomNeighbor].size() > 0 && randomNeighbor != dst) {
                    s += Search(randomNeighbor, dst, newnode, oldadj); // Recursively call randWalk with the new node
                }
                return s;
            }

            for (int neighbor : adj[current]) {         //checks neighbors and moves the current node to the next one
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
        return "Error, try a different path";
    }
}