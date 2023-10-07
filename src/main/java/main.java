

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    private List<Integer> container = new ArrayList<>();

    public void push(int i){
        container.add(i);
    }

    public int size(){
        return container.size();
    }
    public boolean parseGraph(String filepath){         //this parses the input file graph
        try (InputStream dot = getClass().getResourceAsStream(filepath)) {
            MutableGraph g = new Parser().read(dot).setDirected(true);          //imports the graph from the file

            System.out.println(g.toString());                                   //prints the data from the graph
            System.out.println("Number of vertexes in your graph: " + g.nodes().size());
            System.out.println("Number of edges in your graph: " + g.edges().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}
