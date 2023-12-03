import java.util.LinkedList;

public abstract class SearchTemplate {      //abstracts the necessary mehtods
    public void addEdge(int v, int w, LinkedList<Integer> adj[]) {
        adj[v].add(w);
    }
    public abstract String Search(int src, int dst, int node, LinkedList<Integer> adj[]);       //function walks the graph
    public abstract Boolean visit(LinkedList<Integer> myList, int neighbor);            //visits the specific node, used in all search functions
    public abstract int removeHead(LinkedList<Integer> myList);             //removes the head in order to get to the next node



}
