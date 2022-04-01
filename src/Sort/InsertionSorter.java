package Sort;

import java.util.List;

/**
 * Implementation of a sorter object that sorts based on insertion sort algorithm.
 * O(N^2) time complexity.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
public class InsertionSorter<K extends Comparable<K>> implements Sorter<K> {

    @Override
    public void sort(List<K> elements) {
        int size = elements.size();
        for(int i = 1; i < size; ++i) {
            //take unsorted element
            K val = elements.get(i);
            int j;
            //compare element with sorted elements
            for(j = i - 1; j >= 0 && elements.get(j).compareTo(val) > 0; j--) {
                elements.set(j + 1, elements.get(j));
            }
            //place it in correct position
            elements.set(j + 1, val);
        }
    }

    @Override
    public void sort(K[] elements) {
        int size = elements.length;
        for(int i = 1; i < size; ++i) {
            //take unsorted element
            K val = elements[i];
            int j;
            //compare element with sorted elements
            for(j = i - 1; j >= 0 && elements[j].compareTo(val) > 0; j--) {
                elements[j + 1] = elements[j];
            }
            //place it in correct position
            elements[j + 1] = val;
        }
    }
}
