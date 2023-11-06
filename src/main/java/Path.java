import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class  Path {
    public int node;
    public LinkedList<Integer> adj[];           //linked list of adjascent nodes
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
    String BFS(int src, int dst)
    {
        boolean visited[] = new boolean[node];             //if the node has been visited or note
        int parent[] = new int[node];                      //parent of the current node
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[src] = true;                            //adds our src node to parse from there
        queue.add(src);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == dst) {
                StringBuilder path = new StringBuilder();
                int node = dst;
                while (node != src) {
                    path.insert(0, node + " ");
                    node = parent[node];
                }
                path.insert(0, src + " ");
                return path.toString();
            }
            for (int neighbor : adj[current]) {
                if (visited[neighbor]==false) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
        return "Error, try a different path";

    }


}
