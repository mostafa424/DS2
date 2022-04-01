package Heap;

import java.util.List;

/**
 * Interface for defining heap operations for implementing heap data structures.
 *
 * @param <K> type parameter, defines type of objects stored and operated on in heap.
 *          Must implement <code>Comparable</code> interface.
 */
public interface Heap<K extends Comparable<K>> {
    /**
     * Method to create a heap from an array. Equivalent to BUILD-HEAP procedure.
     *
     * @see #toHeap(List) Method override for building from lists.
     * @param values array of type parameter that will be used to build heap.
     */
    void toHeap(K[] values);

    /**
     * Method to create a heap from a list. Equivalent to BUILD-HEAP procedure.
     *
     * @see #toHeap(Comparable[]) Method override for building from arrays.
     * @param values <code>List</code> of type parameter that will be used to build heap.
     */
    void toHeap(List<K> values);

    /**
     * Method to insert an element into the heap.
     * New element is inserted as a leaf and then is percolated up until it reaches proper position.
     * Equivalent to HEAP-INSERT procedure.
     *
     * @param val value to insert into heap.
     */
    void insert(K val);

    /**
     * Method to get top of heap without deleting it.
     *
     * @return element of type parameter at top of heap.
     */
    K getTop();

    /**
     * Method to delete top of heap, returning value deleted.
     * Equivalent to HEAP-EXTRACT procedure.
     *
     * @return element of type parameter that was at top of heap.
     */
    K deleteTop();

    /**
     * Method that returns number of elements currently in heap.
     *
     * @return <code>int</code> value representing number of elements currently in heap.
     */
    int size();
}
