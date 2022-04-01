package Sort;

import java.util.Collections;
import java.util.List;

public class QuickSorter<K extends Comparable<K>> implements Sorter<K> {

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

    private K getPivot(List<K> elements, int start, int end) {
        int mid = (start + end)/2;
        K startElement = elements.get(start);
        K endElement = elements.get(end);
        K midElement = elements.get(mid);
        return medianOfThree(startElement, midElement, endElement);
    }

    private K getPivot(K[] elements, int start, int end) {
        int mid = (start + end)/2;
        K startElement = elements[start];
        K endElement = elements[end];
        K midElement = elements[mid];
        return medianOfThree(startElement, midElement, endElement);
    }

    private int partition(List<K> elements, int start, int end) {
        K pivot = elements.get(end);
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

    private int partition(K[] elements, int start, int end) {
        K pivot = elements[end];
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

     private void quickSort(List<K> elements, int start, int end) {
        if (start < end){
            int pivot = partition(elements, start, end);
            quickSort(elements, start, pivot - 1);
            quickSort(elements, pivot + 1, end);
        }
    }

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
