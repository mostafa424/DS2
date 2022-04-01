package Heap;

import Pair.Pair;

import java.util.List;

/**
 * Interface for defining heap operations for implementing heap data structures.
 * Utilizes both a key and a value, with the key being used for heap operations.
 *
 * @param <K> type parameter of key, defines how each node is operated on in heap.
 *          Must implement <code>Comparable</code> interface.
 * @param <V> type parameter of value, stored in node placed with respect to key.
 */
public interface KeyHeap<K extends Comparable<K>, V> {
     /**
     * Method to create a heap from an array. Equivalent to BUILD-HEAP procedure.
     *
     * @see #toHeap(List)  Method override for building from lists.
     * @param values array of <code>Pair</code> of key and value to be used to build heap.
     */
    void toHeap(Pair<K, V>[] values);

    /**
     * Method to create a heap from a list. Equivalent to BUILD-HEAP procedure.
     *
     * @see #toHeap(Pair[])  Method override for building from arrays.
     * @param values list of <code>Pair</code> of key and value to be used to build heap.
     */
    void toHeap(List<Pair<K, V>> values);

    /**
     * Method to insert a <code>Pair</code> of key and value into the heap.
     * New <code>Pair</code> is inserted as a leaf and then is percolated up until it reaches proper position.
     *
     * @param val <code>Pair</code> of key and value to insert into heap.
     */
    void insert(Pair<K, V> val);

    /**
     * Method to get top <code>Pair</code> of heap without deleting it.
     *
     * @return <code>Pair</code> of key and value at top of heap.
     */
    Pair<K, V> getMax();

    /**
     * Method to delete top <code>Pair</code> of heap, returning value deleted.
     *
     * @return <code>Pair</code> of key and value that was at top of heap.
     */
    Pair<K, V> deleteMax();

    /**
     * Method that returns number of <code>Pair</code>s currently in heap.
     *
     * @return <code>int</code> value representing number of <code>Pair</code>s currently in heap.
     */
    int size();
}
