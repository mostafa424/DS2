package Sort;

import Heap.Heap;
import Heap.MaxHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a sorter object that sorts based on heap sort algorithm.
 * O(N * lg(N)) time complexity.
 * Uses a Max Heap for implementation.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
public class HeapSorter<K extends Comparable<K>> implements Sorter<K> {

    @Override
    public void sort(List<K> elements) {
        Heap<K> heap = new MaxHeap<K>(elements.size());
        heap.toHeap(elements);
        List<K> buffer = new ArrayList<>(elements.size());
        int size = elements.size();
        for(int i = 0; i < size; i++) {
            buffer.add(heap.deleteTop());
        }
        //place results in same list.
        elements.clear();
        //add back elements to list in reverse.
        for(int i = 0; i < size; i++) {
            elements.add(buffer.remove(0));
        }
    }

    @Override
    public void sort(K[] elements) {
        Heap<K> heap = new MaxHeap<K>(elements.length);
        heap.toHeap(elements);
        int size = elements.length;
        for(int i = 0; i < size; i++) {
            elements[size - i - 1] = heap.deleteTop();
        }
    }
}
