



import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.parse.Parser;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static guru.nidi.graphviz.model.Factory.*;

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

    public ArrayList<String> GetNodeArr(){                                  //makes array of all node names so it can assign an int to them
        ArrayList<String> nodes = new ArrayList<>();
        g.nodes().forEach(Node -> nodes.add(Node.name().value()));
        return nodes;
    }
    public boolean removeNode(String label){
        ArrayList<String> mynodes = GetNodeArr();
        if(!mynodes.contains(label)){
            System.out.println("Error, node does not exist");
            return false;
        }
        MutableGraph temp = mutGraph("D").setDirected(true);            //new graph to replace the old one
        g.edges().forEach(Link -> {                                           //gets all edge details so we can move them to new graph
            String fromex = Link.from().toString().split("\\{",2)[0];
            String toex = Link.to().toString().split("\\:",2)[0];

            if(!fromex.equals(label)  && !toex.equals(label) ){
                temp.add(mutNode(fromex).addLink(mutNode(toex)));     //adds your edge
            }
        });
        g.nodes().forEach(node -> {
            if (!node.name().value().equals(label)) {
                temp.add(mutNode(node.name().value()));                 //adds any new nodes back

            }

        });

        g=temp;
        return true;
    }

    public boolean removeNodes(String[] label){             //calls removenode function to remove multiple nodes
        for(int i=0;i<label.length;i++){
            removeNode(label[i]);
        }
        return true;
    }
    public boolean removeEdge(String srcLabel, String dstLabel){
        MutableGraph temp = mutGraph("D").setDirected(true);
        g.edges().forEach(Link -> {                                           //gets all edge details so we can move them to new graph
            String fromex = Link.from().toString().split("\\{",2)[0];
            String toex = Link.to().toString().split("\\:",2)[0];

            if(!fromex.equals(srcLabel)  || !toex.equals(dstLabel) ){       //makes sure src and dst label match for the node
                temp.add(mutNode(fromex).addLink(mutNode(toex)));     //adds your edge
            }
        });
        g.nodes().forEach(node -> {
                temp.add(mutNode(node.name().value()));                 //adds any new nodes back
        });
        if(g.edges().size() == temp.edges().size()){
            System.out.println("EXCEPTION, Your edge does not exist");
            return false;
        }
        g=temp;//bro why doesnt this stuff work
        return true;
    }
    public Node getNode(String label){                                  //returns node you select
        return node(label);
    }

    public Multimap getLinks(){
        Multimap<String, String> linkMap = ArrayListMultimap.create();          //gets all the links and adds them to a linkMap
        g.edges().forEach(Link -> {
            String fromex = Link.from().toString().split("\\{",2)[0];
            String toex = Link.to().toString().split("\\:",2)[0];
            linkMap.put(fromex, toex);
        });
        return linkMap;
    }
    public enum Algorithm{
        DFS,BFS
    }
    public Path GraphSearch(Node src, Node dst, Algorithm Algo){
        System.out.println("Running search through "+ Algo);
        String pathtxt ="";
        String str = "";
        ArrayList<String> joe = GetNodeArr();
        Path path = new Path(joe.size());
        Multimap<String, String> linkMap = getLinks();
        for (Map.Entry<String, String> entry : linkMap.entries()) {                 //uses multimap library so it can hold all the links easier(couldnt use a hashmap)
            path.addEdge(joe.indexOf(entry.getKey()), joe.indexOf(entry.getValue()));//adds all edges to the path
        }
        if(Algo == Algorithm.DFS){
            str = path.DFS(joe.indexOf(src.name().value()), joe.indexOf(dst.name().value()));//runs the BFS from the path class
        }else{
            str = path.BFS(joe.indexOf(src.name().value()), joe.indexOf(dst.name().value()));//runs the DFS from the path class
        }
        String[] array = str.split(" +");
        for(int i=0;i<array.length;i++){                                                        //makes the BFS able to be printed
            pathtxt += joe.get(Integer.parseInt(array[i]));
            if(i+1 < array.length){
                pathtxt += "->";
            }
        }
        System.out.println(pathtxt);
        if(pathtxt.isEmpty()){
            return null;
        }
        return path;
    }

    public String SearchtoString(Node src, Node dst, Algorithm Algo){          //made this function for testing purposes
        String pathtxt ="";
        String str = "";
        ArrayList<String> joe = GetNodeArr();
        Path path = new Path(joe.size());
        Multimap<String, String> linkMap = getLinks();
        for (Map.Entry<String, String> entry : linkMap.entries()) {                 //uses multimap library so it can hold all the links easier(couldnt use a hashmap)
            path.addEdge(joe.indexOf(entry.getKey()), joe.indexOf(entry.getValue()));//adds all edges to the path
        }
        if(Algo == Algorithm.DFS){
            str = path.DFS(joe.indexOf(src.name().value()), joe.indexOf(dst.name().value()));//runs the BFS from the path class
        }else{
            str = path.BFS(joe.indexOf(src.name().value()), joe.indexOf(dst.name().value()));//runs the DFS from the path class
        }
        String[] array = str.split(" +");
        for(int i=0;i<array.length;i++){                                                        //makes the BFS able to be printed
            pathtxt += joe.get(Integer.parseInt(array[i]));
            if(i+1 < array.length){
                pathtxt += "->";
            }
        }

        if(!pathtxt.isEmpty()){
            return(pathtxt);
        }else{return null;}
    }


}
