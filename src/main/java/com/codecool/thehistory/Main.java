package com.codecool.thehistory;

import java.util.Arrays;

public class Main {
//    public void makeTheHistoryInstance(TheHistory theHistory) {
//        theHistory.add();
//    }

    public static void main(String[] args) {
        String testString = "zero one two three four one five one six seven one eight";
        TheHistoryArray myTheHistoryArray = new TheHistoryArray();
        myTheHistoryArray.add(testString);
        myTheHistoryArray.removeWord("one");
        myTheHistoryArray.clear();
    }
}
