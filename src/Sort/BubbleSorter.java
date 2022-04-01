package Sort;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of a sorter object that sorts based on bubble sort algorithm.
 * O(N^2) time complexity.
 * Slightly optimized using a boolean flag such that the algorithm terminates if
 * it does a pass without swapping anything.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
public class BubbleSorter<K extends Comparable<K>> implements Sorter<K> {

    @Override
    public void sort(List<K> elements) {
        boolean done = false;
        for(int i = 0; i < elements.size() && !done; i++) {
            done = true;
            for(int j = 0; j < elements.size() - 1 - i; j++) {
                if(elements.get(j).compareTo(elements.get(j+1)) > 0) {
                    Collections.swap(elements, j, j+1);
                    done = false;
                }
            }
        }
    }

    @Override
    public void sort(K[] elements) {
        boolean done = false;
        for(int i = 0; i < elements.length && !done; i++) {
            done = true;
            for(int j = 0; j < elements.length - 1 - i; j++) {
                if(elements[j].compareTo(elements[j+1]) > 0) {
                    K temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                    done = false;
                }
            }
        }
    }

}
