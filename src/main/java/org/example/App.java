package org.example;


import org.example.list.realizations.MyArrayList;
import org.example.list.realizations.MyLinkedList;
import org.example.list.MyList;


public class App {

    public static void manipulationsWithArrayList(){
        System.out.println("ArrayList");
        MyList<Integer> array = new MyArrayList<>();
        array.add(2);
        array.add(9);
        array.add(5);
        array.add(6);
        System.out.println(array);
        array.sort();
        System.out.println(array);
    }

    public static void manipulationsWithLinkedList(){
        System.out.println("LinkedList");
        MyList<Integer> array = new MyLinkedList<>();
        array.add(2);
        array.add(3);
        array.add(4);

        System.out.println(array);
        array.remove(0);
        System.out.println(array);
        System.out.println(array.get(1));
    }
    public static void main( String[] args ) {
        manipulationsWithArrayList();
        manipulationsWithLinkedList();
    }
}
