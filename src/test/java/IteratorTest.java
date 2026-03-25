import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTest {
    private Collection<String> list;
    private Iterator<String> itr;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
        list.add("cat");
        list.add("dog");
        itr = list.iterator();
    }

    // hasNext() base case: [C1, C5] = [T, T]
    @Test
    public void testHasNextBaseCase() {
        assertTrue(itr.hasNext());
    }

    // hasNext() [C1, C5] = [F, T]
    @Test
    public void testHasNextWhenIteratorIsExhausted() {
        itr.next();
        itr.next();

        assertFalse("Iterator should report no next element after all items are consumed", itr.hasNext());
    }

    // hasNext() [C1, C5] = [T, F]
    @Test
    public void testHasNextAfterConcurrentModification() {
        list.add("bird");

        assertTrue(itr.hasNext());
    }


    // next() base case: [C1, C2, C5] = [T, T, T]
    @Test
    public void testNextBaseCase() {
        assertEquals("cat", itr.next());
        assertTrue("Second element should still remain after one successful next()", itr.hasNext());
    }

    // next() revised case: [C1, C2, C5] = [F, F, T]
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyCollection() {
        itr.next();
        itr.next();
        itr.next();
    }

    // next() case: [C1, C2, C5] = [T, F, T]
    @Test(expected = NoSuchElementException.class)
    public void testNextAfterIteratorIsExhausted() {
        list = new ArrayList<String>();
        list.add(null);
        itr = list.iterator();
        assertNull(itr.next());
    }

    // next() revised case: [C1, C2, C5] = [T, F, F]
    @Test(expected = ConcurrentModificationException.class)
    public void testNextAfterConcurrentModification() {
        list.add("bird");
        itr.next();
    }

    // remove() base case: [C1, C2, C3, C4, C5] = [T, T, T, T, T]
    @Test
    public void testRemoveBaseCase() {
        assertEquals("cat", itr.next());

        itr.remove();

        assertEquals(1, list.size());
        assertFalse(list.contains("cat"));
        assertTrue(list.contains("dog"));
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, F, -, -]
    @Test(expected = IllegalStateException.class)
    public void testRemoveBeforeNext() {
        itr.remove();
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, T, F, T]
    @Test(expected = IllegalStateException.class)
    public void testRemoveTwiceWithoutAdvancingIterator() {
        itr.next();
        itr.remove();

        itr.remove();
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, T, T, T]
    @Test
    public void testRemoveAfterAdvancingAgainIsLegal() {
        assertEquals("cat", itr.next());
        itr.remove();

        assertEquals("dog", itr.next());
        itr.remove();

        assertTrue(list.isEmpty());
    }

    // remove() revised case: [C1, C2, C3, C4, C5] = [T, F, T, T, F]
    @Test(expected = ConcurrentModificationException.class)
    public void testRemoveAfterConcurrentModification() {
        itr.next();
        list.add("bird");

        itr.remove();
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, T, T, T]
    @Test
    public void testRemoveKeepsRemainingIterationConsistent() {
        itr.next();
        itr.remove();

        assertTrue(itr.hasNext());
        assertEquals("dog", itr.next());
        assertFalse(itr.hasNext());
    }
}
