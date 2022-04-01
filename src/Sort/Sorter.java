package Sort;

import java.util.List;

/**
 * Interface defining operations that can be done by a sorter object.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
public interface Sorter<K extends Comparable<K>> {
    /**
     * Method to sort input <code>List</code> ascendingly.
     * Does not change <code>List</code> implementation.
     *
     * @param elements <code>List</code> containing elements to be sorted.
     */
    public void sort(List<K> elements);

    /**
     * Method to sort input array ascendingly.
     *
     * @param elements array containing elements to be sorted.
     */
    public void sort(K[] elements);
}
