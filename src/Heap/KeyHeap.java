package Heap;

import Pair.Pair;

import java.util.List;

public interface KeyHeap<K extends Comparable<K>, V> {
    void toHeap(Pair<K, V>[] values);
    void toHeap(List<Pair<K, V>> values);
    void insert(Pair<K, V> val);
    Pair<K, V> getMax();
    Pair<K, V> deleteMax();
    int size();
}
