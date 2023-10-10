

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

    public boolean tostring(){
        System.out.println("\nYour vertexes: ");
        List<String> list = new ArrayList<String> ();
        g.nodes().forEach(node -> list.add(node.name().toString()));
        Set<String> verSet = new HashSet<>(list);
        list.clear();
        list.addAll(verSet);
        System.out.print(verSet.toString());
        System.out.println("Number of vertexes in your graph: " + verSet.size());
        System.out.println("Number of edges in your graph: " + g.edges().size());
        System.out.println(g.toString());
        return true;
    }

    public boolean outputGraph(String filepath){
        try{
            FileWriter file = new FileWriter(filepath);
            PrintWriter output = new PrintWriter(file);
            output.print("Your vertexes: ");
            List<String> list = new ArrayList<String> ();
            g.nodes().forEach(node -> list.add(node.name().toString()));
            Set<String> verSet = new HashSet<>(list);
            list.clear();
            list.addAll(verSet);
            output.print(verSet.toString() + "\n");
            output.println("Number of vertexes in your graph: " + verSet.size());
            output.println("Number of edges in your graph: " + g.edges().size());
            output.println(g.toString());
            output.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        return true;
    }
    public boolean outputDOTGraph(String path){
        try{
            Graphviz.fromGraph(g).render(Format.DOT).toFile(new File(path));
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
