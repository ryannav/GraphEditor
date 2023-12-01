import java.util.LinkedList;

public class DFSearch extends SearchTemplate {
    public Boolean visit(LinkedList<Integer> myList, int neighbor){
        myList.push(neighbor);
        return true;
    }
    public int removeHead(LinkedList<Integer> myList){
        return myList.pop();
    }
    public String Search(int src, int dst, int node, LinkedList<Integer> adj[]){
        boolean visited[] = new boolean[node];          //if the node has been visited or not
        int parent[] = new int[node];
        LinkedList<Integer> stack = new LinkedList<Integer>();
        visited[src] = true;
        visit(stack,src);

        while (!stack.isEmpty()) {
            int current = removeHead(stack);

            if (current == dst) {                               //if we have reached the destination node
                StringBuilder path = new StringBuilder();
                node = dst;
                while (node != src) {                               //gets the path straight from destination to the source
                    path.insert(0, node + " ");
                    node = parent[node];
                }
                path.insert(0, src + " ");
                return path.toString();
            }

            for (int neighbor : adj[current]) {
                if (!visited[neighbor]) {       //checks neighbors for if they have been visited
                    visited[neighbor] = true;      //if not it visits them
                    visit(stack,neighbor);
                    parent[neighbor] = current;
                }
            }
        }

        return "Error, try a different path";
    }
}
