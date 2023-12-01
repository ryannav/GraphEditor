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

}