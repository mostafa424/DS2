package Sort;

import java.util.ArrayList;
import java.util.List;

class MergeSorter<K extends Comparable<K>> implements Sorter<K> {

    void merge(List<K> elements, int start, int mid, int end) {
        int leftEnd = mid - start + 1;
        int rightEnd = end - mid;
        List<K> left = new ArrayList<K>(leftEnd);
        List<K> right = new ArrayList<K>(rightEnd);
        for(int i = 0; i < leftEnd; i++) {
            left.add(elements.get(start + i));
        }
        for(int j = 0; j < rightEnd; j++) {
            right.add(elements.get(mid + 1 + j));
        }
        int i = 0;
        int j = 0;
        int k = start;
        while (i < leftEnd && j < rightEnd) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                elements.set(k++, left.get(i++));
            } else {
                elements.set(k++, right.get(j++));
            }
        }
        while(i < leftEnd) {
            elements.set(k++, left.get(i++));
        }
        while(j < rightEnd) {
            elements.set(k++, right.get(j++));
        }
    }

    void merge(K[] elements, int start, int mid, int end) {
        int leftEnd = mid - start + 1;
        int rightEnd = end - mid;
        List<K> left = new ArrayList<K>(leftEnd);
        List<K> right = new ArrayList<K>(rightEnd);
        for(int i = 0; i < leftEnd; i++) {
            left.add(elements[start + i]);
        }
        for(int j = 0; j < rightEnd; j++) {
            right.add(elements[mid + 1 + j]);
        }
        int i = 0;
        int j = 0;
        int k = start;
        while (i < leftEnd && j < rightEnd) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                elements[k++] = left.get(i++);
            } else {
                elements[k++] = right.get(j++);
            }
        }
        while(i < leftEnd) {
            elements[k++] = left.get(i++);
        }
        while(j < rightEnd) {
            elements[k++] = right.get(j++);
        }
    }

    private void mergeSort(List<K> elements, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(elements, start, mid);
            mergeSort(elements, mid + 1, end);
            merge(elements, start, mid, end);
        }
    }

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