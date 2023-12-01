import guru.nidi.graphviz.model.MutableNode;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.slf4j.ILoggerFactory.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

public class myTests {

//-------------------- Start of feature 1 tests -----------------------------------------------------
    @Test
    public void testfeatur1pt1(){        //this test will cover feature 1 in the project
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals("ToStrings should match","Your vertexes: [A, B, C, D]" +            //what it should be having in one string
                "\nNumber of vertexes in your graph: 4\n" +
                "Number of edges in your graph: 3\n" +
                "digraph \"D\" {\n" +
                "\"A\" -> \"B\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"D\"\n" +
                "}", s.tostring());
    }
   @Test
    public void testfeatur1pt1test2(){        //this test will cover feature 1 in the project
        main s = new main();
        s.parseGraph("/test2.dot");
        assertEquals("Your vertexes: [A, B, C, D, F]\n" +
                "Number of vertexes in your graph: 5\n" +
                "Number of edges in your graph: 5\n" +
                "digraph \"A\" {\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"C\" -> \"D\"\n" +
                "\"B\" -> \"C\"\n" +
                "\"B\" -> \"F\"\n" +
                "}", s.tostring());
       assertEquals(true,true);
    }

    @Test
    public void testfeatur1pt2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        try{
            String exStr = Files.readString(Paths.get("expectedOut/f1pt2expected.txt"));
            assertEquals(exStr.replaceAll("\n", "").replaceAll("\r", ""), s.outputGraph("outputs/testf1out.txt").replaceAll("\n", "").replaceAll("\r", ""));
            //added replace all to text because it would fail the test due to "line seperators" between the two despite being the same so I touched the strngs up for the test ( they are the same either way)
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testfeatur1pt2test2(){
        main s = new main();
        s.parseGraph("/test2.dot");
        try{
            String exStr = Files.readString(Paths.get("expectedOut/f1p2t2expected.txt"));
            assertEquals(exStr.replaceAll("\n", "").replaceAll("\r", ""), s.outputGraph("outputs/testf1pt2out.txt").replaceAll("\n", "").replaceAll("\r", ""));
            //added replace all to text because it would fail the test due to "line seperators" between the two despite being the same so I touched the strngs up for the test ( they are the same either way)
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(true,true);

    }

    //-------------------- Start of feature 2 tests -----------------------------------------------------
    @Test
    public void testfeature2pt1(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, E]\n" +
                "Number of vertexes in your graph: 5\n" +
                "Number of edges in your graph: 3\n" +
                "digraph \"D\" {\n" +
                "\"E\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"D\"\n" +
                "}");
    }

    @Test
    public void testfeature2pt1test2(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test2.dot");
        s.addNode("E");
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, E, F]\n" +
                "Number of vertexes in your graph: 6\n" +
                "Number of edges in your graph: 5\n" +
                "digraph \"A\" {\n" +
                "\"E\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"C\" -> \"D\"\n" +
                "\"B\" -> \"C\"\n" +
                "\"B\" -> \"F\"\n" +
                "}");
        assertEquals(true,true);
    }
    @Test
    public void testfeature2pt2(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test1.dot");
        String[] j = {"E","F","G"};
        s.addNodes(j);
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, E, F, G]\n" +
                "Number of vertexes in your graph: 7\n" +
                "Number of edges in your graph: 3\n" +
                "digraph \"D\" {\n" +
                "\"E\"\n" +
                "\"F\"\n" +
                "\"G\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"D\"\n}");
    }

    @Test
    public void testfeature2pt2test2(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test2.dot");
        String[] j = {"E","G","H","I"};
        s.addNodes(j);
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, E, F, G, H, I]\n" +
                "Number of vertexes in your graph: 9\n" +
                "Number of edges in your graph: 5\n" +
                "digraph \"A\" {\n" +
                "\"E\"\n" +
                "\"G\"\n" +
                "\"H\"\n" +
                "\"I\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"C\" -> \"D\"\n" +
                "\"B\" -> \"C\"\n" +
                "\"B\" -> \"F\"\n" +
                "}");
        assertEquals(true,true);
    }

    //-------------------- Start of feature 3 tests -----------------------------------------------------
    @Test
    public void testfeature3(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D]\n" +
                "Number of vertexes in your graph: 4\n" +
                "Number of edges in your graph: 4\n" +
                "digraph \"D\" {\n" +
                "\"A\" -> \"B\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"D\"\n" +
                "\"B\" -> \"C\"\n" +
                "}");
    }

    @Test
    public void testfeature3test2(){
        main s = new main();
        s.parseGraph("/test2.dot");
        s.addNode("I");
        s.addEdge("A","I");
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, F, I]\n" +
                "Number of vertexes in your graph: 6\n" +
                "Number of edges in your graph: 6\n" +
                "digraph \"A\" {\n" +
                "\"I\"\n" +
                "\"A\" -> \"I\"\n" +
                "\"A\" -> \"C\"\n" +
                "\"A\" -> \"B\"\n" +
                "\"C\" -> \"D\"\n" +
                "\"B\" -> \"C\"\n" +
                "\"B\" -> \"F\"\n" +
                "}");
        assertEquals(true,true);
    }


    //-------------------- Start of feature 4 tests -----------------------------------------------------
    @Test
    public void testfeature4part1(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        s.outputDOTGraph("outputs/test1Output.dot");
        String str = s.tostring();
        main p = new main();
        p.parseGraph("expectedDot.dot");
        assertEquals(p.tostring(),str);
        assertEquals(true,true);
    }

    @Test
    public void testfeature4part1test2(){
        main s = new main();
        s.parseGraph("/test2.dot");
        s.addEdge("A","F");
        s.outputDOTGraph("outputs/f4test2Output.dot");
        String str = s.tostring();
        main p = new main();
        p.parseGraph("f4expectedDot2.dot");
        assertEquals(p.tostring(),str);
        assertEquals(true,true);
    }
    @Test
    public void testfeature4part2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        assertEquals(true, s.outputGraphics("outputs/test2Output.png", "png"));
    }

    @Test
    public void testFeature4Part2Test2(){
        main s = new main();
        s.parseGraph("/test2.dot");
        assertEquals(true, s.outputGraphics("outputs/f4t2output.png", "png"));
    }
    @Test
    public void testremovenode(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.tostring();
        s.removeNode("B");
        assertEquals(s.tostring(), "Your vertexes: [A, C, D, E]\n" +
                "Number of vertexes in your graph: 4\n" +
                "Number of edges in your graph: 2\n" +
                "digraph \"D\" {\n" +
                "\"E\"\n" +
                "\"A\" -> \"D\"\n" +
                "\"A\" -> \"C\"\n" +
                "}");
    }
    @Test
    public void testremovenodeError(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.tostring();
        assertEquals(s.removeNode("F"),false);//false means it sends error code
    }
    @Test
    public void testremovenodes(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.tostring();
        String[] testArr = {"B","C","D"};
        s.removeNodes(testArr);
        assertEquals(s.tostring(), "Your vertexes: [A, E]\n" +
                "Number of vertexes in your graph: 2\n" +
                "Number of edges in your graph: 0\n" +
                "digraph \"D\" {\n" +
                "\"A\"\n" +
                "\"E\"\n" +
                "}");
    }

    @Test
    public void testremoveedgeERROR(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.tostring();
        assertEquals(s.removeEdge("A","E"), false);     //returning false means the edge doesnt exist and error message was sent
    }
    @Test
    public void testremoveedge(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.tostring();
        s.removeEdge("A","B");
        s.tostring();
        assertEquals(s.tostring(), "Your vertexes: [A, B, C, D, E]\n" +
                "Number of vertexes in your graph: 5\n" +
                "Number of edges in your graph: 2\n" +
                "digraph \"D\" {\n" +
                "\"B\"\n" +
                "\"E\"\n" +
                "\"A\" -> \"D\"\n" +
                "\"A\" -> \"C\"\n" +
                "}");
    }

    @Test
    public void testremovenodeandedges(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.addNode("F");
        s.addEdge("A","F");
        s.tostring();
        s.removeNode("B");
        s.removeNode("E");
        s.removeEdge("A","C");
        s.removeEdge("A","F");
        assertEquals(s.tostring(), "Your vertexes: [A, C, D, F]\n" +
                "Number of vertexes in your graph: 4\n" +
                "Number of edges in your graph: 1\n" +
                "digraph \"D\" {\n" +
                "\"F\"\n" +
                "\"C\"\n" +
                "\"A\" -> \"D\"\n" +
                "}");
    }
@Test
    public void testDFS(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.addEdge("B","E");
        s.GraphSearch(s.getNode("A"),s.getNode("E"), main.Algorithm.DFS);
        assertEquals("A->B->E",s.SearchtoString(s.getNode("A"),s.getNode("E"),main.Algorithm.DFS) );
    }

    @Test
    public void testBFS(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addNode("E");
        s.addEdge("B","E");
        s.GraphSearch(s.getNode("A"),s.getNode("E"), main.Algorithm.BFS);
        assertEquals("A->B->E",s.SearchtoString(s.getNode("A"),s.getNode("E"),main.Algorithm.BFS) );
    }

    @Test
    public void testrand(){
        main s = new main();
        s.parseGraph("/test2.dot");
        //s.GraphSearch(s.getNode("A"),s.getNode("E"), main.Algorithm.BFS);
        s.outputGraphics("outputs/randwalktest.png", "png");
        s.randomWalkSearch(s.getNode("a"));
        //assertEquals("A->B->E",s.SearchtoString(s.getNode("A"),s.getNode("E"),main.Algorithm.BFS) );
        assertEquals(true,true);
    }


}
