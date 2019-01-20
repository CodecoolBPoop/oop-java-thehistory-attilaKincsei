package com.codecool.thehistory;

import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class NewMain {
    String testString = "zero one two three four one five one six seven one eight";
    String sourceText = "replace replace me replace me me me replace me me";
    String fromExp = "replace";
    String[] fromArr = {"replace"};

    String toExp = "AWE SUPER HAPPY FUN";
    String[] toArr = {"AWE", "SUPER", "HAPPY", "FUN"};

    private String[] testWordsArray;
    private List<String> myArrayList = new ArrayList<>();
    private List<String > fromList = new ArrayList<>();
    private List<String > toList = new ArrayList<>();

    private List add(String text) {
        String[] stringArray = text.split("\\s");
        testWordsArray = Arrays.copyOf(stringArray, stringArray.length);
        Collections.addAll(myArrayList, testWordsArray);
        Collections.addAll(fromList, fromArr);
        Collections.addAll(toList, toArr);
        myArrayList.removeAll(fromList);
        myArrayList.addAll(2, toList);
        return myArrayList;
    }


    public static void main(String[] args) {
        NewMain myNewMain = new NewMain();

        // Create a new LinkedList
        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> fromList = new LinkedList<String>();

        fromList.add("2eee");
        fromList.add("ADDED");
        fromList.add("4333");

        // Add Items to the array list
        list.add("0___0");
        list.add("1www");
        list.add("2eee");
        list.add("3rrrr");
        list.add("4333");
        list.add("5dddd");
        list.add("6qqqqq");
        list.add("7ssssss");
        list.add("8yyy");
        list.add("9xxx");

//        System.out.println(list.get(0));
//        for (ListIterator<String> i = list.listIterator(); i.hasNext();) {
//            String element = i.next();
//            i.next();
//            i.previous();
//            i.next();
//            i.previous();
//            i.next();
//            if (i.hasNext()) {
//                i.previous();
//            }
//            System.out.println(element);
//            System.out.println(i.previousIndex());
//        }

        try {

            ListIterator<String> i = list.listIterator();
            System.out.println(list); //
            String element = i.next();
            System.out.print("first: "); //
            System.out.println(i.previousIndex()); //
            System.out.println(element); //
            i.next();
            i.next();
//            String lastItered = i.next();
            i.previous();
            i.previous();
            i.previous();
//            list.pop();
            i.next();
//            list.add("laaaast");
            i.remove();
            i.next();
//            System.out.printf("polled: %s", polled);
            System.out.println();
            System.out.println(i.previousIndex());
            System.out.println();
//            ConcurrentModificationException
        } catch (ConcurrentModificationException CMEerror) {
            System.out.println(CMEerror);
            System.out.println(CMEerror.getMessage());
        } finally {
            System.out.println(list);
        }
    }
}
