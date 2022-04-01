package Sort;

import Heap.Heap;
import Heap.MaxHeap;

import java.util.ArrayList;
import java.util.List;

public class HeapSorter<K extends Comparable<K>> implements Sorter<K> {

    @Override
    public void sort(List<K> elements) {
        Heap<K> heap = new MaxHeap<K>(elements.size());
        heap.toHeap(elements);
        List<K> buffer = new ArrayList<>(elements.size());
        int size = elements.size();
        for(int i = 0; i < size; i++) {
            buffer.add(heap.deleteMax());
        }
        elements.clear();
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
            elements[size - i - 1] = heap.deleteMax();
        }
    }
}
