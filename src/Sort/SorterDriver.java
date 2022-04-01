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

        int[] cases = {5, 10, 25, 50, 100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000, 100000};
        List<Long> heapTime = new ArrayList<Long>();
        List<Long> bubbleTime = new ArrayList<Long>();
        List<Long> insertionTime = new ArrayList<Long>();
        List<Long> mergeTime = new ArrayList<Long>();
        List<Long> quickTime = new ArrayList<Long>();
        List<Long> selectionTime = new ArrayList<Long>();
        Random random = new Random();
        for (int i = 0; i < cases.length; i++) {
            List<Integer> heapArray = new ArrayList<>();
            List<Integer> bubbleArray = new ArrayList<>();
            int[] insertionArray = new int[cases[i]];
            int[] mergeArray = new int[cases[i]];
            int[] quickArray = new int[cases[i]];
            int[] selectionArray = new int[cases[i]];
            for (int j = 0; j < cases[i]; j++) {
                heapArray.add(random.nextInt(1000));
            }
            for (int j = 0; j < cases[i]; j++) {
                bubbleArray.add(heapArray.get(i));
                insertionArray[i] = heapArray.get(i);
                mergeArray[i] = heapArray.get(i);
                quickArray[i] = heapArray.get(i);
                selectionArray[i] = heapArray.get(i);
            }
            long timeBefore = System.nanoTime();
            heapSorter.sort(heapArray);
            long timeAfter = System.nanoTime();
            heapTime.add(timeAfter - timeBefore);

            bubbleSorter.sort(bubbleArray);

            bubbleTime.add(timeAfter - timeBefore);

            insertionSort.sort(insertionArray);

            insertionTime.add(timeAfter - timeBefore);

            selectionSort.sort(selectionArray);

            selectionTime.add(timeAfter - timeBefore);

            mergeSort.sort(mergeArray, 0, mergeArray.length - 1);

            mergeTime.add(timeAfter - timeBefore);

            quickSort.quickSort(quickArray, 0, quickArray.length - 1);

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
