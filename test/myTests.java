import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class myTests {
    @Test
    public void testPush(){
        main s = new main();
        s.push(1);
        assertEquals(1, s.size());
    }


    @Test
    public void test1(){        //this test will cover funciton 1 in the project
        main s = new main();
        assertEquals(true, s.parseGraph("/test1.dot"));
    }
}
