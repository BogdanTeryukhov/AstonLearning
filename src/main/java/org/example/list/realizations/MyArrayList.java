package org.example.list.realizations;

import org.example.list.MyList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;


/**
 * Массив с динамическим размером и индексацией
 * По умолчанию список содержит 10 элементов, при превышении размера - увеличивается в 2 раза.
 * @param <T> - тип элементов массива
 */
public class MyArrayList<T> implements MyList<T>, Comparator<T> {

    /**
     * Массив элементов
     */
    private T[] array;
    /**
     * Размерность массива
     */
    private int size;

    /**
     * Создаёт пустой массив с размером по умолчанию
     */
    public MyArrayList() {
        this.size = 0;
        this.array = (T[]) new Object[10];
    }

    /**
     * Добавление элемента в конец массива
     * @param t - добавляемый элемент
     */
    @Override
    public void add(T t) {
        if (array.length == size) {
            changeSize();
        }
        array[size++] = t;
    }

    /**
     * Добавление элемента в массив по индексу
     * @param t - добавляемый элемент
     * @param index - индекс(место) добавляемого элемента
     */
    @Override
    public void add(int index, T t) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = t;
        size++;
    }

    /**
     * Получение элемента из массива по индексу
     * @param index - индекс(место) получаемого элемента
     * @return искомый элемент
     */
    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }
    /**
     * Удаление элемента из массива по индексу
     * @param index - индекс(место) удаляемого элемента
     */
    @Override
    public void remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    /**
     * Удаление всех элементов массива
     */
    @Override
    public void clean() {
        this.size = 0;
        this.array = (T[]) new Object[10];
    }

    /**
     * Сортировка элементов массива
     */
    @Override
    public void sort() {
        Arrays.sort(array,this);
    }

    /**
     * Увеличение размерности массива при полной заполненности
     */
    public void changeSize() {
        array = Arrays.copyOf(array, size * 2);
    }

    /**
     * Строковое представление массива
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(array).filter(Objects::nonNull).toArray());
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null && o2 == null)
            return 0;
        else if (o1 == null)
            return 1;
        else if (o2 == null)
            return -1;
        else {
            return ((Comparable<T>) o1).compareTo(o2);
        }
    }
}
