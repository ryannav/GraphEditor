

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

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
        List<String> list = new ArrayList<String> ();
        g.nodes().forEach(node -> list.add(node.name().toString()));
        Set<String> verSet = new HashSet<>(list);
        list.clear();
        list.addAll(verSet);
        System.out.println(verSet.toString());
        System.out.println("Number of vertexes in your graph: " + verSet.size());
        System.out.println("Number of edges in your graph: " + g.edges().size());
        System.out.println(g.toString());
        return true;
    }

    public boolean alreadyVertex(String label){      //this is a helper method that checks if its already a vertex
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

    public boolean addEdge(String srcLabel, String dstLabel){
        if(alreadyVertex(srcLabel) && alreadyVertex(dstLabel)){
           g.add(mutNode(srcLabel).addLink(mutNode(dstLabel)));

        }
       /* try {
            // Render the graph as an image (e.g., PNG)
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("yourgraph.png"));

            System.out.println("Graph visualization saved to " + "yourgraph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return true;
    }


}
