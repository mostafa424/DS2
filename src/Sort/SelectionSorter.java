package Sort;

import java.util.Collections;
import java.util.List;

public class SelectionSorter<K extends Comparable<K>> implements Sorter<K> {
    @Override
    public void sort(List<K> elements) {
        int size = elements.size();
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++)
                if (elements.get(j).compareTo(elements.get(minIndex)) < 0) {
                    minIndex = j;
                }
            Collections.swap(elements, minIndex, i);
        }
    }

    @Override
    public void sort(K[] elements) {
        int size = elements.length;
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++)
                if (elements[j].compareTo(elements[minIndex]) < 0) {
                    minIndex = j;
                }
            K temp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = temp;
        }
    }
}
