import java.util.LinkedList;

public class SearchContext {
    private SearchStrategy SearchStrategy;


    public void setSearchStrategy(SearchStrategy SearchStrategy) {
        this.SearchStrategy = SearchStrategy;
    }

    public String performSearch(int src, int dst, int node, LinkedList<Integer> adj[]) {
        return SearchStrategy.Search(src,dst,node,adj);
    }
}
