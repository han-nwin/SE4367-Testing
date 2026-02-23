import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class MinTest {

    private List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test
    public void testForNullList() {
        list = null;
        try {
            Min.min(list);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @After
    public void tearDown() {
        list = null;
    }
}
