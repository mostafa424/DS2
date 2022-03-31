package Heap;

import Pair.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxKeyHeap<K extends Comparable<K>, V> implements KeyHeap<K, V>{
    List<Pair<K, V>> values;

    public MaxKeyHeap() {
        this.values = new ArrayList<>();
        this.values.add(new Pair<K, V>(null, null));
    }

    public MaxKeyHeap(int size) {
        this.values = new ArrayList<>(size+1);
        this.values.add(new Pair<K, V>(null, null));
    }

    private void percolateUp(int i) {
        while(i >= 2 && this.values.get(i/2).getFirst().compareTo(this.values.get(i).getFirst()) < 0) {
            Pair<K, V> tempVal = this.values.get(i/2);
            this.values.set(i/2, this.values.get(i));
            this.values.set(i, tempVal);
            i /= 2;
        }
    }

    private void percolateDown(int i) {
        while(this.values.size() > 2*i) {
            if(this.values.size() > 2*i + 1) {
                if(this.values.get(2*i).getFirst().compareTo(this.values.get(2*i + 1).getFirst()) > 0) {
                    if(this.values.get(i).getFirst().compareTo(this.values.get(2*i).getFirst()) < 0) {
                        Pair<K, V> tempVal = this.values.get(2*i);
                        this.values.set(2*i, this.values.get(i));
                        this.values.set(i, tempVal);
                        i *= 2;
                    } else {
                        break;
                    }
                } else {
                    if(this.values.get(i).getFirst().compareTo(this.values.get(2*i + 1).getFirst()) < 0) {
                        Pair<K, V> tempVal = this.values.get(2*i + 1);
                        this.values.set(2*i + 1, this.values.get(i));
                        this.values.set(i, tempVal);
                        i = 2*i + 1;
                    } else {
                        break;
                    }
                }
            } else {
                if(this.values.get(i).getFirst().compareTo(this.values.get(2*i).getFirst()) < 0) {
                    Pair<K, V> tempVal = this.values.get(2*i);
                    this.values.set(2*i, this.values.get(i));
                    this.values.set(i, tempVal);
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

