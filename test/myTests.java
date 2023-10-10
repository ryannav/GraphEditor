import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class myTests {


    @Test
    public void test1(){        //this test will cover feature 1 in the project
        main s = new main();
        s.parseGraph("/test1.dot");
        assertEquals(true, s.tostring());
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
}
