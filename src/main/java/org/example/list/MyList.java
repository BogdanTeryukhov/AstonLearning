package org.example.list;

public interface MyList<T> {
    void add(T t);
    void add(int index, T t);
    T get(int index);
    void remove(int index);
    void clean();
    void sort();
}
