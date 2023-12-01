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

    String DFS(int src, int dst) {

        DFSearch df = new DFSearch();
        return df.Search(src,dst,node,adj);
    }
    String BFS(int src, int dst)
    {

        BFSearch bf = new BFSearch();
        return bf.Search(src,dst,node,adj);

    }

}