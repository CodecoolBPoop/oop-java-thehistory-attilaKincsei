package com.codecool.thehistory;

import java.lang.Math;
import java.util.Arrays;
import java.util.InputMismatchException;
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
                int previousLength = resultArray.length;
                String[] increaseLengthArray = new String[previousLength + i - startIndex];
                System.arraycopy(resultArray, 0, increaseLengthArray, 0, resultArray.length);
                resultArray = increaseLengthArray;
                System.arraycopy(wordsArray, startIndex, resultArray, previousLength, (i - startIndex));
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

    private boolean sequenceExists(String[] sequence, int index) {
        /**
         * Searches for an array of strings in another array
         * @param baseArray: an array to be examinded;
         * @param sequence: an Array of strings to be searched for in baseArray
         * @return: true if all 3 elements of sampleArray match the sequence in the baseTextArray
         * */
        int lastIndexToCheck = index + sequence.length - 1;
        if(lastIndexToCheck >= wordsArray.length) {
            return false;
        }
        int randomNumber = (sequence.length > 2) ? ThreadLocalRandom.current().nextInt(1, sequence.length - 1) : 0;
        boolean lastElementEquals = wordsArray[lastIndexToCheck].equals(sequence[sequence.length - 1]);
        boolean randomElementEquals = wordsArray[index + randomNumber].equals(sequence[randomNumber]);
        return lastElementEquals && randomElementEquals;
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        String[] newWordsArray = new String[0];
        int unchangedSlicesStartIndex = 0;

        if (fromWords.length == toWords.length) {
            for (int i = 0; i < wordsArray.length; i++) {
                boolean isMatch = false;
                if (wordsArray[i].equals(fromWords[0])) {
                    isMatch = sequenceExists(fromWords, i);
                }

                if (isMatch) {
                    for (int j = 0; j < fromWords.length; j++) {
                        wordsArray[i + j] = toWords[j];
                    }
                }
            }
        } else {
            String[] leftOverArray = new String[0];
            for (int i = 0; i < wordsArray.length; i++) {

                boolean isMatch = false;
                if (wordsArray[i].equals(fromWords[0])) {
                    isMatch = sequenceExists(fromWords, i);
                }

                if (isMatch) {
                    // unchangedSlice: Creating the last unchanged slice since the last replacement
                    int unchangedSliceLength = i - unchangedSlicesStartIndex;
                    String[] unchangedSlice = new String[unchangedSliceLength];
                    System.arraycopy(wordsArray, unchangedSlicesStartIndex, unchangedSlice, 0, unchangedSliceLength);
                    // Expanding newWordsArray length with unchanged slice length and toWords length
                    int newWordsArraysPreviousLength = newWordsArray.length;
                    int newWordsArraysNewLength = newWordsArraysPreviousLength + unchangedSlice.length + toWords.length;
                    String[] temporaryArray = new String[newWordsArraysNewLength];
                    System.arraycopy(newWordsArray, 0, temporaryArray, 0, newWordsArraysPreviousLength);
                    newWordsArray = temporaryArray;
                    // Adding unchanged elements and toWords
                    System.arraycopy(unchangedSlice, 0, newWordsArray, newWordsArraysPreviousLength, unchangedSlice.length);
                    System.arraycopy(toWords, 0, newWordsArray, (newWordsArraysPreviousLength + unchangedSlice.length), toWords.length);
                    unchangedSlicesStartIndex = i + fromWords.length;
                    i += (fromWords.length - 1);
                }
                if (i == wordsArray.length - 1) {
                    leftOverArray = Arrays.copyOfRange(wordsArray, unchangedSlicesStartIndex, wordsArray.length);
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
