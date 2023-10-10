import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class myTests {


    @Test
    public void testfeatur1pt1(){        //this test will cover feature 1 in the project
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals(true, s.tostring());
    }

    @Test
    public void testfeatur1pt2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals(true, s.outputGraph("outputs/testf1out.txt"));
    }
    @Test
    public void testfeature2pt1(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals(true, s.addNode("luck"));
        s.tostring();
    }
    @Test
    public void testfeature2pt2(){                        //tests the single tostring for graphs
        main s = new main();
        s.parseGraph("/test1.dot");
        String[] j = {"luck","mutt","baby"};
        assertEquals(true, s.addNodes(j));
        s.tostring();
    }

    @Test
    public void testfeature3(){
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals(true, s.addEdge("B","C"));
        s.tostring();
    }
    @Test
    public void testfeature4part1(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        assertEquals(true, s.outputDOTGraph("outputs/test1Output.dot"));
    }

    @Test
    public void testfeature4part2(){
        main s = new main();
        s.parseGraph("/test1.dot");
        s.addEdge("B","C");
        assertEquals(true, s.outputGraphics("outputs/test2Output.png", "png"));
    }
}
