package Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap<K extends Comparable<K>> implements Heap<K>{
    List<K> values;

    public MaxHeap() {
        this.values = new ArrayList<>();
        this.values.add(null);
    }

    public MaxHeap(int size) {
        this.values = new ArrayList<>(size+1);
        this.values.add(null);
    }

    private void percolateUp(int i) {
        while(i >= 2 && this.values.get(i/2).compareTo(this.values.get(i)) < 0) {
            K tempVal = this.values.get(i/2);
            this.values.set(i/2, this.values.get(i));
            this.values.set(i, tempVal);
            i /= 2;
        }
    }

    private void percolateDown(int i) {
        while(this.values.size() > 2*i) {
            if(this.values.size() > 2*i + 1) {
                if(this.values.get(2*i).compareTo(this.values.get(2*i + 1)) > 0) {
                    if(this.values.get(i).compareTo(this.values.get(2*i)) < 0) {
                        K tempVal = this.values.get(2*i);
                        this.values.set(2*i, this.values.get(i));
                        this.values.set(i, tempVal);
                        i *= 2;
                    } else {
                        break;
                    }
                } else {
                    if(this.values.get(i).compareTo(this.values.get(2*i + 1)) < 0) {
                        K tempVal = this.values.get(2*i + 1);
                        this.values.set(2*i + 1, this.values.get(i));
                        this.values.set(i, tempVal);
                        i = 2*i + 1;
                    } else {
                        break;
                    }
                }
            } else {
                if(this.values.get(i).compareTo(this.values.get(2*i)) < 0) {
                    K tempVal = this.values.get(2*i);
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
    public void toHeap(K[] values) {
        this.values.clear();
        this.values.add(null);
        this.values.addAll(Arrays.asList(values));
        for(int i = this.values.size()/2; i > 0; i--) {
            this.percolateDown(i);
        }
    }

    @Override
    public void toHeap(List<K> values) {
        this.values.clear();
        this.values.add(null);
        this.values.addAll(values);
        for(int i = this.values.size()/2; i > 0; i--) {
            this.percolateDown(i);
        }
    }

    @Override
    public void insert(K val) {
        this.values.add(val);
        this.percolateUp(this.values.size() - 1);
    }

    @Override
    public K getMax() {
        return this.values.get(1);
    }

    @Override
    public K deleteMax() {
        K successorVal = this.values.get(this.values.size() - 1);
        K res = this.values.get(1);
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

