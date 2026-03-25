import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public void test_hasNext_base() {
        assertTrue(itr.hasNext());
    }

    // hasNext() [C1, C5] = [F, T]
    @Test
    public void test_hasNext_C1() {
        itr.next();
        itr.next();

        assertFalse("Iterator should report no next element after all items are consumed", itr.hasNext());
    }

    // hasNext() [C1, C5] = [T, F]
    @Test
    public void test_hasNext_C5() {
        list.add("bird");

        assertTrue(itr.hasNext());
    }


    // next() base case: [C1, C2, C5] = [T, T, T]
    @Test
    public void test_next_base() {
        assertEquals("cat", itr.next());
        assertTrue("Second element should still remain after one successful next()", itr.hasNext());
    }

    // next() case (revised from [F, T, T]): [C1, C2, C5] = [F, F, T]
    @Test(expected = NoSuchElementException.class)
    public void test_next_C1() {
        itr.next();
        itr.next();
        itr.next();
    }

    // next() case: [C1, C2, C5] = [T, F, T]
    @Test
    public void test_next_C2() {
        list = new ArrayList<String>();
        list.add(null);
        list.add("dog");
        itr = list.iterator();
        assertNull(itr.next());
        assertTrue(itr.hasNext());
    }

    // next() case (revised from [T, T, F]): [C1, C2, C5] = [T, F, F]
    @Test(expected = ConcurrentModificationException.class)
    public void test_next_C5() {
        list.add("bird");
        itr.next();
    }

    // remove() base case: [C1, C2, C3, C4, C5] = [T, T, T, T, T]
    @Test
    public void test_remove_base() {
        assertEquals("cat", itr.next());

        itr.remove();

        assertEquals(1, list.size());
        assertFalse(list.contains("cat"));
        assertTrue(list.contains("dog"));
    }

    // remove() case (revised from [F, T, T, T, T]): [C1, C2, C3, C4, C5] = [F, F, T, T, T]
    @Test
    public void test_remove_C1() {
        list = new ArrayList<String>();
        list.add(null);
        itr = list.iterator();
        itr.next();

        itr.remove();

        assertTrue(list.isEmpty());
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, F, T, T, T]
    @Test
    public void test_remove_C2() {
        list = new ArrayList<String>();
        list.add(null);
        list.add("dog");
        itr = list.iterator();
        assertNull(itr.next());

        itr.remove();

        assertEquals(1, list.size());
        assertTrue(list.contains("dog"));
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, F, T, T]
    @Test(expected = UnsupportedOperationException.class)
    public void test_remove_C3() {
        Collection<String> unmodifiable = Collections.unmodifiableCollection(list);
        Iterator<String> unmodItr = unmodifiable.iterator();
        unmodItr.next();

        unmodItr.remove();
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, T, F, T]
    @Test(expected = IllegalStateException.class)
    public void test_remove_C4() {
        itr.remove();
    }

    // remove() case: [C1, C2, C3, C4, C5] = [T, T, T, T, F]
    @Test(expected = ConcurrentModificationException.class)
    public void test_remove_C5() {
        itr.next();
        list.add("bird");

        itr.remove();
    }

}
