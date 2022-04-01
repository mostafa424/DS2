package Sort;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of a sorter object that sorts based on quick sort algorithm.
 * O(N * lg(N)) average time complexity.
 * Picks pivot based on median of start-mid-end.
 *
 * @param <K> type parameter: defines data type that the sorter operates on.
 *           Must implement <code>Comparable</code> interface.
 */
public class QuickSorter<K extends Comparable<K>> implements Sorter<K> {

    /**
     * Method that calculates median value of three elements.
     *
     * @param element1 first element
     * @param element2 second element
     * @param element3 third element
     * @return median element
     */
    private K medianOfThree(K element1, K element2, K element3) {
        if(element1.compareTo(element3) <= 0) {
            if(element1.compareTo(element2) > 0) {
                return element1;
            } else if(element2.compareTo(element3) <= 0) {
                return element2;
            } else {
                return element3;
            }
        } else {
            if(element3.compareTo(element2) > 0) {
                return element3;
            } else if(element2.compareTo(element1) <= 0) {
                return element2;
            } else {
                return element1;
            }
        }
    }

    /**
     * Method that returns quick sort pivot of <code>List</code> of elements.
     *
     * @param elements <code>List</code> of elements to get pivot from.
     * @param start starting index of element <code>List</code>.
     * @param end ending index of element <code>List</code>.
     * @return element which is to act as pivot for quick sort operation.
     */
    private K getPivot(List<K> elements, int start, int end) {
        int mid = (start + end)/2;
        K startElement = elements.get(start);
        K endElement = elements.get(end);
        K midElement = elements.get(mid);
        return medianOfThree(startElement, midElement, endElement);
    }

    /**
     * Method that returns quick sort pivot of array of elements.
     *
     * @param elements array of elements to get pivot from.
     * @param start starting index of element array.
     * @param end ending index of element array.
     * @return element which is to act as pivot for quick sort operation.
     */
    private K getPivot(K[] elements, int start, int end) {
        int mid = (start + end)/2;
        K startElement = elements[start];
        K endElement = elements[end];
        K midElement = elements[mid];
        return medianOfThree(startElement, midElement, endElement);
    }

    /**
     * Method to split <code>List</code> based on its pivot, returns index of sorted pivot.
     *
     * @param elements <code>List</code> of elements to partition.
     * @param start starting index of <code>List</code>
     * @param end ending index of <code>List</code>
     * @return index of sorted pivot.
     */
    private int partition(List<K> elements, int start, int end) {
        K pivot = this.getPivot(elements, start, end);
        int i = (start - 1);
        for(int j = start; j <= end - 1; j++){
            if (elements.get(j).compareTo(pivot) < 0){
                i++;
                Collections.swap(elements, i, j);
            }
        }
        Collections.swap(elements, i + 1, end);
        return (i + 1);
    }

    /**
     * Method to split array based on its pivot, returns index of sorted pivot.
     *
     * @param elements array of elements to partition.
     * @param start starting index of array
     * @param end ending index of array
     * @return index of sorted pivot.
     */
    private int partition(K[] elements, int start, int end) {
        K pivot = this.getPivot(elements, start, end);
        int i = (start - 1);
        for(int j = start; j <= end - 1; j++){
            if (elements[j].compareTo(pivot) < 0){
                i++;
                K temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }
        K temp = elements[i+1];
        elements[i+1] = elements[end];
        elements[end] = temp;
        return (i + 1);
    }

    /**
     * Method to recursively partition and sort a <code>List</code>
     *
     * @param elements <code>List</code> of elements to sort.
     * @param start starting index of <code>List</code>
     * @param end ending index of <code>List</code>
     */
    private void quickSort(List<K> elements, int start, int end) {
        if (start < end){
            int pivot = partition(elements, start, end);
            quickSort(elements, start, pivot - 1);
            quickSort(elements, pivot + 1, end);
        }
    }

    /**
     * Method to recursively partition and sort an array
     *
     * @param elements array of elements to sort.
     * @param start starting index of array
     * @param end ending index of array
     */
    private void quickSort(K[] elements, int start, int end) {
        if (start < end){
            int pivot = partition(elements, start, end);
            quickSort(elements, start, pivot - 1);
            quickSort(elements, pivot + 1, end);
        }
    }

    @Override
    public void sort(List<K> elements) {
        this.quickSort(elements, 0, elements.size() - 1);
    }

    @Override
    public void sort(K[] elements) {
        this.quickSort(elements, 0, elements.length - 1);
    }
}
