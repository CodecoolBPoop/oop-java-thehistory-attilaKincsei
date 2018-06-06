package com.codecool.thehistory;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] stringArray = text.split("\\s");
        wordsArray = Arrays.copyOf(stringArray, stringArray.length);
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        String[] resultArray = new String[0];
        int startIndex = 0;
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(wordToBeRemoved)) {
                String[] temporaryArray = Arrays.copyOfRange(wordsArray, startIndex, i);
                int previousLength = resultArray.length;
                resultArray = Arrays.copyOf(resultArray, (previousLength + temporaryArray.length));
                System.arraycopy(temporaryArray, 0, resultArray, previousLength, temporaryArray.length);
                startIndex = i + 1;
            }
        }
        wordsArray = resultArray;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        // Checking if fromWords sequence exists in source text
        int randomNumber = (fromWords.length > 2) ? ThreadLocalRandom.current().nextInt(1, fromWords.length - 1) : 0;
        int lastIndexToCheck;
        boolean firstElementEquals;
        boolean lastElementEquals;
        boolean randomElementEquals;

        String[] newWordsArray = new String[0];
        int unchangedSlicesStartIndex = 0;

        if (fromWords.length == toWords.length) {
            for (int i = 0; i < wordsArray.length; i++) {
                try {
                    lastIndexToCheck = i + fromWords.length - 1;
                    firstElementEquals = wordsArray[i].equals(fromWords[0]);
                    lastElementEquals = wordsArray[lastIndexToCheck].equals(fromWords[fromWords.length - 1]);
                    randomElementEquals = wordsArray[i + randomNumber].equals(fromWords[randomNumber]);

                    if (firstElementEquals && lastElementEquals && randomElementEquals) {
                        for (int j = 0; j < fromWords.length; j++) {
                            wordsArray[i + j] = toWords[j];
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException AIOOBE) {
                    continue;
                }
            }
        } else {
            String[] leftOverArray = new String[0];
            for (int i = 0; i < wordsArray.length; i++) {
                try {
                    lastIndexToCheck = i + fromWords.length - 1;
                    firstElementEquals = wordsArray[i].equals(fromWords[0]);
                    lastElementEquals = wordsArray[lastIndexToCheck].equals(fromWords[fromWords.length - 1]);
                    randomElementEquals = wordsArray[i + randomNumber].equals(fromWords[randomNumber]);

                    if (firstElementEquals && lastElementEquals && randomElementEquals) {
                        String[] unchangedSlice = Arrays.copyOfRange(wordsArray, unchangedSlicesStartIndex, i);
                        int newWordsArraysPreviousLength = newWordsArray.length;
                        int newWordsArraysNewLength = newWordsArraysPreviousLength + unchangedSlice.length + toWords.length;
                        newWordsArray = Arrays.copyOf(newWordsArray, newWordsArraysNewLength);
                        System.arraycopy(unchangedSlice, 0, newWordsArray, newWordsArraysPreviousLength, unchangedSlice.length);
                        System.arraycopy(toWords, 0, newWordsArray, (newWordsArraysPreviousLength + unchangedSlice.length), toWords.length);
                        unchangedSlicesStartIndex = i + fromWords.length;
                        leftOverArray = Arrays.copyOfRange(wordsArray, unchangedSlicesStartIndex, wordsArray.length);
                        i += (fromWords.length - 1);
                    }
                } catch (ArrayIndexOutOfBoundsException AIOOBE) {
                    continue;
                }
            }
            if (leftOverArray.length > 0) {
                int oldLength = newWordsArray.length;
                newWordsArray = Arrays.copyOf(newWordsArray, (newWordsArray.length + leftOverArray.length));
                System.arraycopy(leftOverArray, 0, newWordsArray, oldLength, leftOverArray.length);
            }
            wordsArray = newWordsArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
