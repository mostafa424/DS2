package Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorterDriver {
    public void run() {
        System.out.println("You are now comparing between heap sorting and other sorting techniques");
        HeapSorter<Integer> heapSorter = new HeapSorter<>();
        BubbleSorter<Integer> bubbleSorter = new BubbleSorter<>();
        InsertionSorter<Integer> insertionSorter = new InsertionSorter<>();
        MergeSorter<Integer> mergeSorter = new MergeSorter<>();
        QuickSorter<Integer> quickSort = new QuickSorter<>();
        SelectionSorter<Integer> selectionSort = new SelectionSorter<>();
        int[] cases = {5, 10, 25, 50, 75, 100, 250, 500, 750, 1000, 2500, 5000, 7500, 10000, 25000, 50000, 75000, 100000};
        List<Long> heapTime = new ArrayList<>();
        List<Long> bubbleTime = new ArrayList<>();
        List<Long> insertionTime = new ArrayList<>();
        List<Long> mergeTime = new ArrayList<>();
        List<Long> quickTime = new ArrayList<>();
        List<Long> selectionTime = new ArrayList<>();
        Random random = new Random();
        for (int testCase : cases) {
            Integer[] heapArray = new Integer[testCase];
            Integer[] bubbleArray = new Integer[testCase];
            Integer[] insertionArray = new Integer[testCase];
            Integer[] mergeArray = new Integer[testCase];
            Integer[] quickArray = new Integer[testCase];
            Integer[] selectionArray = new Integer[testCase];
            for (int j = 0; j < testCase; j++) {
                heapArray[j] = random.nextInt(testCase * 10);
            }
            for (int j = 0; j < testCase; j++) {
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
            insertionSorter.sort(insertionArray);
            timeAfter = System.nanoTime();
            insertionTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            selectionSort.sort(selectionArray);
            timeAfter = System.nanoTime();
            selectionTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            mergeSorter.sort(mergeArray);
            timeAfter = System.nanoTime();
            mergeTime.add(timeAfter - timeBefore);
            timeBefore = System.nanoTime();
            quickSort.sort(quickArray);
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
    }
}
