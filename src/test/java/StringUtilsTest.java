import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testReverseNormalWord() {
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    public void testReverseSingleCharacter() {
        assertEquals("a", StringUtils.reverse("a"));
    }

    @Test
    public void testReverseEmptyString() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    public void testReverseMultipleWords() {
        assertEquals("dlrow olleh", StringUtils.reverse("hello world"));
    }

    @Test
    public void testReversePalindrome() {
        assertEquals("madam", StringUtils.reverse("madam"));
    }
}
