package org.example.vectorHeapV;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class VectorHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public VectorHeap() {
        data = new ArrayList<>();
    }

    public void add(E item) {
        data.add(item);
        int current = data.size() - 1;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (data.get(current).compareTo(data.get(parent)) >= 0) break;
            E temp = data.get(current);
            data.set(current, data.get(parent));
            data.set(parent, temp);
            current = parent;
        }
    }

    public E remove() {
        if (data.isEmpty()) throw new NoSuchElementException("Heap vacío");
        E min = data.get(0);
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            pushDownRoot(0);
        }
        return min;
    }

    private void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int child = 2 * root + 1;
            if (child >= heapSize) break;
            if (child + 1 < heapSize && data.get(child + 1).compareTo(data.get(child)) < 0)
                child++;
            if (data.get(child).compareTo(value) >= 0) break;
            data.set(root, data.get(child));
            root = child;
        }
        data.set(root, value);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
