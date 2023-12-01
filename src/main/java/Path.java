import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

import java.util.*;

class  Path {
    public int node;
    public LinkedList<Integer> adj[];           //linked list of adjascent nodes
    SearchContext searchContext = new SearchContext();
    public Path(int size)                       //constructor for path
    {
        node = size;
        adj = new LinkedList[size];
        for (int i = 0; i < size; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    String DFS(int src, int dst) {
        searchContext.setSearchStrategy(new DFSearch());                //design strategy, called from enum
        return searchContext.performSearch(src,dst,node,adj);
    }
    String BFS(int src, int dst)
    {
        searchContext.setSearchStrategy(new BFSearch());                //design strategy, called from enum
        return searchContext.performSearch(src,dst,node,adj);
    }
    String randWalk(int src)
    {
        boolean visited[] = new boolean[node];
        int parent[] = new int[node];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[src] = true;
        queue.add(src);
        Random random = new Random();
        int randomIndex = random.nextInt(adj[src].size());
        int randomNeighbor = adj[src].get(randomIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == randomNeighbor) {
                StringBuilder path = new StringBuilder();
                int node = randomNeighbor;
                while (node != src) {
                    path.insert(0, node + " ");
                    node = parent[node];
                }
                path.insert(0, src + " ");
                String s = path.toString();
                if (adj[randomNeighbor].size() > 0) {
                    s += randWalk(randomNeighbor); // Recursively call randWalk with the new node
                }
                return s;
            }

            for (int neighbor : adj[current]) {
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