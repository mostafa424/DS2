package Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorterDriver {
    public void run() {
        System.out.println("You are now comparing between heap sorting and other sorting techniques");
        HeapSorter<Integer> heapSorter = new HeapSorter<Integer>();
        BubbleSorter<Integer> bubbleSorter = new BubbleSorter<Integer>();
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        SelectionSort selectionSort = new SelectionSort();
        int[] cases = {5, 10, 25, 50, 75, 100, 250, 500, 750, 1000, 2500, 5000, 7500, 10000, 25000, 50000, 75000, 100000};
        List<Long> heapTime = new ArrayList<Long>();
        List<Long> bubbleTime = new ArrayList<Long>();
        List<Long> insertionTime = new ArrayList<Long>();
        List<Long> mergeTime = new ArrayList<Long>();
        List<Long> quickTime = new ArrayList<Long>();
        List<Long> selectionTime = new ArrayList<Long>();
        Random random = new Random();
        for (int i = 0; i < cases.length; i++) {
            Integer[] heapArray = new Integer[cases[i]];
            Integer[] bubbleArray = new Integer[cases[i]];
            int[] insertionArray = new int[cases[i]];
            int[] mergeArray = new int[cases[i]];
            int[] quickArray = new int[cases[i]];
            int[] selectionArray = new int[cases[i]];
            for (int j = 0; j < cases[i]; j++) {
                heapArray[j] = random.nextInt(cases[i] * 10);
            }
            for (int j = 0; j < cases[i]; j++) {
                bubbleArray[j] = heapArray[j];
                insertionArray[j] = heapArray[j];
                mergeArray[j] = heapArray[j];
                quickArray[j] = heapArray[j];
                selectionArray[j] = heapArray[j];
            }
            long timeBefore;
            long timeAfter;
            timeBefore = System.nanoTime();
            heapSorter.sort(heapArray);
            timeAfter = System.nanoTime();
            heapTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            bubbleSorter.sort(bubbleArray);
            timeAfter = System.nanoTime();
            bubbleTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            insertionSort.sort(insertionArray);
            timeAfter = System.nanoTime();
            insertionTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            selectionSort.sort(selectionArray);
            timeAfter = System.nanoTime();
            selectionTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            mergeSort.sort(mergeArray, 0, mergeArray.length - 1);
            timeAfter = System.nanoTime();
            mergeTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            quickSort.quickSort(quickArray, 0, quickArray.length - 1);
            timeAfter = System.nanoTime();
            quickTime.add(timeAfter - timeBefore);
        }
            System.out.println("Data For HeapSort:");
            System.out.println(heapTime);
            System.out.println("Data For BubbleSort:");
            System.out.println(bubbleTime);
            System.out.println("Data For insertion sort:");
            System.out.println(insertionTime);
            System.out.println("Data For selection sort:");
            System.out.println(selectionTime);
            System.out.println("Data For merge sort:");
            System.out.println(mergeTime);
            System.out.println("Data For quick sort:");
            System.out.println(quickTime);
            return;

    }
}
