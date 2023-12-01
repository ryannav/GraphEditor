import java.util.LinkedList;

public class BFSearch extends SearchTemplate implements SearchStrategy{
    public Boolean visit(LinkedList<Integer> stack, int neighbor){
        stack.add(neighbor);
        return true;
    }
    public int removeHead(LinkedList<Integer> myList){
        return myList.poll();
    }

        public String Search(int src, int dst, int node, LinkedList<Integer> adj[])
    {
        boolean visited[] = new boolean[node];             //if the node has been visited or note
        int parent[] = new int[node];                      //parent of the current node
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[src] = true;                            //adds our src node to parse from there
        visit(queue,src);
        while (!queue.isEmpty()) {
            int current = removeHead(queue);
            if (current == dst) {                               //once we reach our destination node
                StringBuilder path = new StringBuilder();
                node = dst;
                while (node != src) {
                    path.insert(0, node + " ");
                    node = parent[node];
                }
                path.insert(0, src + " ");
                return path.toString();
            }
            for (int neighbor : adj[current]) {     //checks if the neighbors have also been visited yet
                if (visited[neighbor]==false) {
                    visited[neighbor] = true;       //if they havent it visits them
                    visit(queue,neighbor);
                    parent[neighbor] = current;
                }
            }
        }
        return "Error, try a different path";

    }
}
