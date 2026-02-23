import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NamesTest {
    private Names names;

    @Before
    public void setUp() {
        names = new Names();
    }

    // Original flawed test from exercise
    @Test
    public void testSortFlawed() {
        names.add("Laura");
        names.add("Han");
        names.add("Alex");
        names.add("Ashley");
        names.sort();
        // This test is flawed because it only checks the first element after sorting,
        // which does not guarantee the entire list is sorted correctly.
        // A proper test should verify the order of all elements in the list.
        assertTrue("Sort method", names.getFirst().equals("Alex"));
    }

    // Improved test that properly verifies sorting
    @Test
    public void testSortImproved() {
        names.add("Laura");
        names.add("Han");
        names.add("Alex");
        names.add("Ashley");
        names.sort();

        List<String> sorted = names.getAll();
        assertEquals(Arrays.asList("Alex", "Ashley", "Han", "Laura"), sorted);
    }
}
