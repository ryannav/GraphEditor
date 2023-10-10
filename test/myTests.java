import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.slf4j.ILoggerFactory.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

public class myTests {


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
    public void testfeatur1pt2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        try{
            String exStr = Files.readString(Paths.get("expectedOut/f1pt2expected.txt"));
            assertEquals(exStr.replaceAll("\n", "").replaceAll("\r", ""), s.outputGraph("outputs/testf1out.txt").replaceAll("\n", "").replaceAll("\r", ""));
            //added replaceall to text because it would fail the test due to "line seperators" between the two despite being the same so I touched the strngs up for the test ( they are the same either way)
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
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
    public void testfeature4part1(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        s.outputDOTGraph("outputs/test1Output.dot");
        String str = s.tostring();
        main p = new main();
        p.parseGraph("expectedDot.dot");
        assertEquals(p.tostring(),str);
    }

    @Test
    public void testfeature4part2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        assertEquals(true, s.outputGraphics("outputs/test2Output.png", "png"));
    }
}
