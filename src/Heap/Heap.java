package Heap;

import java.util.List;

public interface Heap<K extends Comparable<K>> {
    void toHeap(K[] values);
    void toHeap(List<K> values);
    void insert(K val);
    K getMax();
    K deleteMax();
    int size();
}
