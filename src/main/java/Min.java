import java.util.List;

public class Min {
    public static <T extends Comparable<? super T>> T min(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }
        T result = list.get(0);
        for (T element : list) {
            if (element.compareTo(result) < 0) {
                result = element;
            }
        }
        return result;
    }
}
