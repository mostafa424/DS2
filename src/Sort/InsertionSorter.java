package Sort;

import java.util.List;

public class InsertionSorter<K extends Comparable<K>> implements Sorter<K> {

    @Override
    public void sort(List<K> elements) {
        int size = elements.size();
        for(int i = 1; i < size; ++i) {
            K val = elements.get(i);
            int j;
            for(j = i - 1; j >= 0 && elements.get(j).compareTo(val) > 0; j--) {
                elements.set(j + 1, elements.get(j));
            }
            elements.set(j + 1, val);
        }
    }

    @Override
    public void sort(K[] elements) {
        int size = elements.length;
        for(int i = 1; i < size; ++i) {
            K val = elements[i];
            int j;
            for(j = i - 1; j >= 0 && elements[j].compareTo(val) > 0; j--) {
                elements[j + 1] = elements[j];
            }
            elements[j + 1] = val;
        }
    }
}
