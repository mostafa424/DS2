package Heap;

import Pair.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of a binary Max Heap data structure with a key and value <code>Pair</code>, uses a <code>List</code> to store the complete binary tree.
 * Utilizes a sentinel node for the first index in the list so that each element's index matches
 * its complete binary tree node order.
 *
 * @param <K> key type parameter, defines type of keys stored and operated on in heap.
 *            Must implement <code>Comparable</code> interface.
 * @param <V> value type parameter, defines type of values stored in heap.
 */
public class MaxKeyHeap<K extends Comparable<K>, V> implements KeyHeap<K, V>{
    List<Pair<K, V>> values;

    /**
     * Default constructor, initializes an <code>ArrayList</code> of default size.
     */
    public MaxKeyHeap() {
        this.values = new ArrayList<>();
        this.values.add(new Pair<K, V>(null, null));
    }

    /**
     * Constructor that takes an integer parameter, initializes an <code>ArrayList</code> with provided size.
     * @param size size of <code>ArrayList</code> to initialize.
     */
    public MaxKeyHeap(int size) {
        this.values = new ArrayList<>(size+1);
        this.values.add(new Pair<K, V>(null, null));
    }

    /**
     * Constructor that takes a <code>List</code> implementation to store heap data.
     * NOTE: provided list MUST be empty, otherwise an exception is thrown.
     * @param list <code>List</code> of <code>Pair</code> implementation to use for heap.
     * @throws <code>ExceptionInInitializerError</code> if provided list is not empty.
     */
    public MaxKeyHeap(List<Pair<K, V>> list) throws ExceptionInInitializerError {
        if(!list.isEmpty()) {
            throw new ExceptionInInitializerError("Non-empty list passed to MaxKeyHeap<K, V> constructor.");
        }
        this.values = list;
        this.values.add(new Pair<K, V>(null, null));
    }

    /**
     * Method to move a node up the heap tree based on pair key until the tree fulfills max heap condition.
     * Used on inserting new nodes.
     *
     * @param i <code>int</code> value: index of node to move up tree.
     */
    private void percolateUp(int i) {
        while(i >= 2 && this.values.get(i/2).getFirst().compareTo(this.values.get(i).getFirst()) < 0) {
            Pair<K, V> tempVal = this.values.get(i/2);
            this.values.set(i/2, this.values.get(i));
            this.values.set(i, tempVal);
            i /= 2;
        }
    }

    /**
     * Method to move a node down the heap tree based on pair key until the tree fulfills max heap condition.
     * Used with toHeap() to create heaps from provided arrays/<code>List</code>s.
     * Equivalent to MAX-HEAPIFY procedure.
     *
     * @param i <code>int</code> value: index of node to move down the tree.
     */
    private void percolateDown(int i) {
        //check if node has children
        while(this.values.size() > 2*i) {
            //check if node has two children
            if(this.values.size() > 2*i + 1) {
                //check if left child is greater than right
                if(this.values.get(2*i).getFirst().compareTo(this.values.get(2*i + 1).getFirst()) > 0) {
                    //check if left child is greater than parent
                    if(this.values.get(i).getFirst().compareTo(this.values.get(2*i).getFirst()) < 0) {
                        //swap
                        Pair<K, V> tempVal = this.values.get(2*i);
                        this.values.set(2*i, this.values.get(i));
                        this.values.set(i, tempVal);
                        //re align index variable.
                        i *= 2;
                    } else {
                        break;
                    }
                } else {
                    //check if right child is greater than parent
                    if(this.values.get(i).getFirst().compareTo(this.values.get(2*i + 1).getFirst()) < 0) {
                        //swap
                        Pair<K, V> tempVal = this.values.get(2*i + 1);
                        this.values.set(2*i + 1, this.values.get(i));
                        this.values.set(i, tempVal);
                        //re align index variable
                        i = 2*i + 1;
                    } else {
                        break;
                    }
                }
            } else {
                //check if child is greater than parent
                if(this.values.get(i).getFirst().compareTo(this.values.get(2*i).getFirst()) < 0) {
                    //swap
                    Pair<K, V> tempVal = this.values.get(2*i);
                    this.values.set(2*i, this.values.get(i));
                    this.values.set(i, tempVal);
                    //re align index variable
                    i *= 2;
                }  else {
                    break;
                }
            }
        }
    }

    @Override
    public void toHeap(Pair<K, V>[] values) {
        this.values.clear();
        this.values.add(new Pair<K, V>(null, null));
        this.values.addAll(Arrays.asList(values));
        for(int i = this.values.size()/2; i > 0; i--) {
            this.percolateDown(i);
        }
    }

    @Override
    public void toHeap(List<Pair<K, V>> values) {
        this.values.clear();
        this.values.add(new Pair<K, V>(null, null));
        this.values.addAll(values);
        for(int i = this.values.size()/2; i > 0; i--) {
            this.percolateDown(i);
        }
    }

    @Override
    public void insert(Pair<K, V> val) {
        this.values.add(val);
        this.percolateUp(this.values.size() - 1);
    }

    @Override
    public Pair<K, V> getMax() {
        return this.values.get(1);
    }

    @Override
    public Pair<K, V> deleteMax() {
        Pair<K, V> successorVal = this.values.get(this.values.size() - 1);
        Pair<K, V> res = this.values.get(1);
        this.values.remove(this.values.size() - 1);
        if(this.values.size() > 1) {
            this.values.set(1, successorVal);
            this.percolateDown(1);
        }
        return res;
    }

    @Override
    public int size() {
        return this.values.size() - 1;
    }
}

