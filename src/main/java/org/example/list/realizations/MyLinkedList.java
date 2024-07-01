package org.example.list.realizations;
import org.example.list.MyList;

import java.util.Comparator;

/**
 * Односвязный список. Содержит в узлы, которые хранят ссылки на свои элементы и следующие узлы
 * @param <T>
 */
public class MyLinkedList<T> implements MyList<T> {
    /**
     * Голова списка. Начальный узел
     */
    private Node head;

    /**
     * Добавление элемента в конец списка
     * @param t - добавляемый элемент
     */
    @Override
    public void add(T t) {
        Node addNode = new Node(t);
        if (this.head == null){
            head = addNode;
        }
        else {
            Node currentNode = head;
            while (currentNode.getNextNode() != null){
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(addNode);
        }
    }
    /**
     * Добавление элемента в конец списка по индексу
     * @param index - индекс(место) добавляемого элемента
     * @param t - добавляемый элемент
     */
    @Override
    public void add(int index, T t) {
        Node addNode = new Node(t);
        Node currentNode = head;
        for (int i = 0; i < index - 1; i++) {
            if (currentNode.getNextNode() == null){
                throw new IndexOutOfBoundsException();
            }
            currentNode = currentNode.getNextNode();
        }
        addNode.setNextNode(currentNode.getNextNode());
        currentNode.setNextNode(addNode);
    }

    /**
     * Получение элемента из списка по индексу
     * @param index - индекс(место) получаемого элемента
     * @return искомый элемент
     */
    @Override
    public T get(int index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            if (currentNode.getNextNode() == null){
                throw new IndexOutOfBoundsException();
            }
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getValue();
    }
    /**
     * Удаление элемента из списка по индексу
     * @param index - индекс(место) удаляемого элемента
     */
    @Override
    public void remove(int index) {
        if (index == 0){
            if (head.getNextNode() == null){
                head = null;
            }
            else {
                head = head.getNextNode();
            }
        }
        else {
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                if (currentNode.getNextNode() == null){
                    throw new IndexOutOfBoundsException();
                }
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
        }
    }
    /**
     * Удаление всех элементов списка
     */
    @Override
    public void clean() {
        this.head = null;
    }

    @Override
    public void sort() {
        //TODO
    }

    /**
     * Функция для строкового представления списка
     * @return строковое представление списка
     */
    public String print(){
        StringBuilder builder = new StringBuilder();

        Node currentNode = head;
        builder.append("[");
        while (currentNode != null){
            builder.append(currentNode).append(", ");
            currentNode = currentNode.getNextNode();
        }
        if (builder.length() > 1){
            builder.delete(builder.length() - 2, builder.length());
        }

        builder.append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        return print();
    }

    /**
     * Подкласс, представляющий узел списка. Узел содержит значение и указатель на следующий узел
     */
    class Node implements Comparator<T> {
        private T value;
        private Node nextNode;
        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }


        @Override
        public String toString() {
            return value.toString();
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
}
