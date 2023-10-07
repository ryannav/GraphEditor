import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class myTests {
    @Test
    public void testPush(){
        main s = new main();
        s.push(1);
        assertEquals(1, s.size());
    }
}
