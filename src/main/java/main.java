

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class main {

    public MutableGraph g;

    public boolean parseGraph(String filepath){         //this parses the input file graph
        try (InputStream dot = getClass().getResourceAsStream(filepath)) {
            g = new Parser().read(dot).setDirected(true);          //imports the graph from the file
                                               //prints the data from the graph
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean tostring(){
        System.out.println("\nYour vertexes: ");
        g.nodes().forEach(node -> System.out.print(node.name()+ " "));
        System.out.println("\n Number of vertexes in your graph: " + g.nodes().size());
        System.out.println("Number of edges in your graph: " + g.edges().size());
        System.out.println(g.toString());
        return true;
    }

    public boolean alreadyVertex(String label){
        List<String> list = new ArrayList<String> ();
        g.nodes().forEach(node -> list.add(node.name().toString()));
        if(list.contains(label)){
            return true;
        }else{return false;}
    }

    public boolean addNode(String Label){
        if(alreadyVertex(Label) == true){
            return false;
        }else{
            g.add(mutNode(Label));
        }
        return true;
    }

    public boolean addNodes(String[] label){
        for(int i = 0; i<label.length; i++){
            if (alreadyVertex(label[i])!=true) {
                g.add(mutNode(label[i]));
            }
        }
        return true;
    }


}
