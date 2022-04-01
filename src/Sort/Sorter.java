package Sort;

import java.util.List;

public interface Sorter<K extends Comparable<K>> {
    public void sort(List<K> elements);
    public void sort(K[] elements);
}
