

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
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

    public String tostring(){
        String verts = "";
        List<String> list = new ArrayList<String> ();    //puts vertex names into a list then a hashset
        g.nodes().forEach(node -> list.add(node.name().toString()));
        Set<String> verSet = new HashSet<>(list);
        list.clear();
        list.addAll(verSet);
        String f = "Your vertexes: " + verSet.toString() + "\nNumber of vertexes in your graph: " + verSet.size()
                + "\nNumber of edges in your graph: " + g.edges().size() + "\n" + g.toString();
        System.out.println(f);
        return f;
    }

    public String outputGraph(String filepath){        //both functions are almost the same except one prints to file
        try{
            FileWriter file = new FileWriter(filepath);
            PrintWriter output = new PrintWriter(file);     //same code as the toString except it writes it into a txt file
            String verts = "";
            List<String> list = new ArrayList<String> ();    //puts vertex names into a list then a hashset
            g.nodes().forEach(node -> list.add(node.name().toString()));
            Set<String> verSet = new HashSet<>(list);
            list.clear();
            list.addAll(verSet);
            String f = "Your vertexes: " + verSet.toString() + "\nNumber of vertexes in your graph: " + verSet.size()
                    + "\nNumber of edges in your graph: " + g.edges().size() + "\n" + g.toString();
            output.print(f);
            output.close();
            return f;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean alreadyVertex(String label){      //this is a helper method that checks if its already a vertex
        List<String> list = new ArrayList<String> ();
        g.nodes().forEach(node -> list.add(node.name().toString()));
        if(list.contains(label)){
            return true;
        }else{return false;}
    }

    public boolean addNode(String Label){
        if(alreadyVertex(Label) == true){           //checks if node is in the list already
            return false;
        }else{
            g.add(mutNode(Label));                  //adds node to the graph
        }
        return true;
    }

    public boolean addNodes(String[] label){
        for(int i = 0; i<label.length; i++){            //checks if nodes are in list already
            if (alreadyVertex(label[i])!=true) {
                g.add(mutNode(label[i]));               //adds all nodes into the graph
            }
        }
        return true;
    }

    public boolean addEdge(String srcLabel, String dstLabel){
        if(alreadyVertex(srcLabel) && alreadyVertex(dstLabel)){     //makes sure both vertexes exist
           g.add(mutNode(srcLabel).addLink(mutNode(dstLabel)));     //adds your edge

        }
        return true;
    }
    public boolean outputDOTGraph(String path){
        try{
            Graphviz.fromGraph(g).render(Format.DOT).toFile(new File(path));   //prints graph in dot file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean outputGraphics(String path, String format){
        if(format == "png" || format == "PNG" || format ==".png" || format == ".PNG"){
            try{
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(path));   //this saves the file to the png
                } catch (IOException e) {
            e.printStackTrace();
        }}else{
            return false;
        }
        return true;
    }


}
