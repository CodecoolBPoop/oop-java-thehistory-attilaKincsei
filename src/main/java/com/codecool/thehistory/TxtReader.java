package com.codecool.thehistory;

import java.io.*;

public class TxtReader {
    String filename;
    TxtReader(String filename){
        this.filename = filename;
    }

    String getTextFromFile() {
        return this.filename;
    }
}
