import java.util.LinkedList;

public abstract class SearchTemplate {
    public void addEdge(int v, int w, LinkedList<Integer> adj[]) {
        adj[v].add(w);
    }
    public abstract String Search(int src, int dst, int node, LinkedList<Integer> adj[]);
    public abstract Boolean visit(LinkedList<Integer> myList, int neighbor);
    public abstract int removeHead(LinkedList<Integer> myList);



}
