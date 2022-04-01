package Pair;

import java.util.Objects;

/**
 * Implementation of a pair data structure, used to store two related values together.
 * Pairs with same values but different order of assignment are NOT equal.
 *
 * @param <T> type parameter of first value.
 * @param <V> type parameter of second value.
 */
public class Pair<T, V> {
    T first;
    V second;

    /**
     * Constructor, initializes a pair storing two values.
     *
     * @param first first value
     * @param second second value
     */
    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" +
                first +
                ", " + second +
                ')';
    }
}