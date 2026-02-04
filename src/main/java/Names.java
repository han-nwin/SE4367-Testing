import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {
    private List<String> names = new ArrayList<>();

    public void add(String name) {
        names.add(name);
    }

    public void sort() {
        Collections.sort(names);
    }

    public String getFirst() {
        return names.isEmpty() ? null : names.get(0);
    }

    public List<String> getAll() {
        return new ArrayList<>(names);
    }
}
