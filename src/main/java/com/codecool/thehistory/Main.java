package com.codecool.thehistory;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    String sourceText = "replace replace me replace me me me replace me me";
    int myInt;
    int myInt2;
    int result;
    boolean myBoolean;
    private static char myChar = 'c';
    String myString;
    int[] myIntegerArray;

    private Main(int myInt, int myInt2) {
        this.myInt = myInt;
        this.myInt2 = myInt2;
    }

    private void square() {
        result *= result;
    }

    private void add() {
        this.result = myInt + myInt2;
    }

    private int squareAdd() {
        this.add();
        this.square();
        return this.result;
    }

    public static void main(String[] args) {
        String testString = "zero one two three four one five one six seven one eight";
        String fromExp = "me replace me";
        String toExp = "AWE SUPER HAPPY FUN";

//        TheHistoryArray myTheHistoryArray = new TheHistoryArray();
//        Main newMain = new Main(6, 10);
//        myTheHistoryArray.add(newMain.sourceText);
//        myTheHistoryArray.replace(fromExp, toExp);
//        myTheHistoryArray.clear();

        TheHistoryArrayList testArrayListObject = new TheHistoryArrayList();
        testArrayListObject.add(testString);
        testArrayListObject.removeWord("one");
        System.out.println(testArrayListObject.size());
        testArrayListObject.clear();
    }
}
