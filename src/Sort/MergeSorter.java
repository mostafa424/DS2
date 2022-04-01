package Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a sorter object that sorts based on merge sort algorithm.
 * O(N * lg(N)) time complexity.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
class MergeSorter<K extends Comparable<K>> implements Sorter<K> {

    /**
     * Method that recombines <code>List</code> into sorted order.
     *
     * @param elements <code>List</code> of elements
     * @param start starting index of <code>List</code>
     * @param mid middle index of <code>List</code>
     * @param end end index of <code>List</code>
     */
    private void merge(List<K> elements, int start, int mid, int end) {
        int leftEnd = mid - start + 1;
        int rightEnd = end - mid;
        List<K> left = new ArrayList<K>(leftEnd);
        List<K> right = new ArrayList<K>(rightEnd);
        //fill left part of list
        for(int i = 0; i < leftEnd; i++) {
            left.add(elements.get(start + i));
        }
        //fill right part of list
        for(int j = 0; j < rightEnd; j++) {
            right.add(elements.get(mid + 1 + j));
        }
        int i = 0;
        int j = 0;
        int k = start;
        //compare elements of left list and right list and place them in sorted order.
        while (i < leftEnd && j < rightEnd) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                elements.set(k++, left.get(i++));
            } else {
                elements.set(k++, right.get(j++));
            }
        }
        //add remaining elements
        while(i < leftEnd) {
            elements.set(k++, left.get(i++));
        }
        //add remaining elements
        while(j < rightEnd) {
            elements.set(k++, right.get(j++));
        }
    }

    /**
     * Method that recombines array into sorted order.
     *
     * @param elements array of elements
     * @param start starting index of array
     * @param mid middle index of array
     * @param end end index of array
     */
    private void merge(K[] elements, int start, int mid, int end) {
        int leftEnd = mid - start + 1;
        int rightEnd = end - mid;
        List<K> left = new ArrayList<K>(leftEnd);
        List<K> right = new ArrayList<K>(rightEnd);
        //fill left list
        for(int i = 0; i < leftEnd; i++) {
            left.add(elements[start + i]);
        }
        //fill right list
        for(int j = 0; j < rightEnd; j++) {
            right.add(elements[mid + 1 + j]);
        }
        int i = 0;
        int j = 0;
        int k = start;
        //compare elements of left list and right list and place them in sorted order.
        while (i < leftEnd && j < rightEnd) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                elements[k++] = left.get(i++);
            } else {
                elements[k++] = right.get(j++);
            }
        }
        //insert remaining elements
        while(i < leftEnd) {
            elements[k++] = left.get(i++);
        }
        //insert remaining elements
        while(j < rightEnd) {
            elements[k++] = right.get(j++);
        }
    }

    /**
     * Method to recursively split and merge a <code>List</code> into a sorted <code>List</code>.
     *
     * @param elements <code>List</code> of elements to sort
     * @param start starting index of <code>List</code>
     * @param end end index of <code>List</code>
     */
    private void mergeSort(List<K> elements, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(elements, start, mid);
            mergeSort(elements, mid + 1, end);
            merge(elements, start, mid, end);
        }
    }

    /**
     * Method to recursively split and merge an array into a sorted array.
     *
     * @param elements array of elements to sort
     * @param start starting index of array
     * @param end end index of array
     */
    private void mergeSort(K[] elements, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(elements, start, mid);
            mergeSort(elements, mid + 1, end);
            merge(elements, start, mid, end);
        }
    }

    @Override
    public void sort(List<K> elements) {
        this.mergeSort(elements, 0, elements.size() - 1);
    }

    @Override
    public void sort(K[] elements) {
        this.mergeSort(elements, 0, elements.length - 1);
    }
}