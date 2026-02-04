import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class NamesTest {
    private Names names;

    @BeforeEach
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
        assertTrue(names.getFirst().equals("Alex"), "Sort method");
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
        assertEquals(List.of("Alex", "Ashley", "Han", "Laura"), sorted);
    }
}
