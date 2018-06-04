package com.codecool.thehistory;

import com.codecool.thehistory.*;

public class Main {
    public static void main(String[] args) {
        TxtReader myIliad = new TxtReader("Iliad.txt");
        System.out.println(myIliad.getTextFromFile());
    }
}
