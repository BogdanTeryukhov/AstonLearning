package org.example.list.realizations;
import org.example.list.MyList;

import java.util.Comparator;

/**
 * Двусвязный список. Содержит в узлы, которые хранят ссылки на свои элементы, следующие узлы и предыдущие узлы
 * @param <T>
 */
public class MyLinkedList<T> implements MyList<T> {
    /**
     * Голова списка. Начальный узел
     */
    private Node head;
    private Node tail;

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
        tail = addNode;
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
        tail = addNode;
    }

    /**
     * Получение элемента из списка по индексу
     * @param index - индекс(место) получаемого элемента
     * @return искомый элемент
     */
    @Override
    public T get(int index) {
        if (index == 0){
            return head.getValue();
        }
        else if (index == size() - 1){
            return tail.getValue();
        }

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
                tail = null;
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
        this.tail = null;
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

    public int size(){
        int length = 0;
        Node currentNode = head;
        while (currentNode != null){
            length++;
            currentNode = currentNode.getNextNode();
        }
        return length;
    }

    /**
     * Подкласс, представляющий узел списка. Узел содержит значение, ссылку на следующий и предыдущий узел
     */
    class Node implements Comparator<T> {
        private T value;
        private Node nextNode;
        private Node prevNode;
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

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
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
