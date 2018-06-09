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
        String[] stringArray = text.split("\\s+");
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
        String[] resultWordsArray = new String[wordsArray.length * toWords.length];
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
        } else if (fromWords.length > toWords.length) {
            int lengthDifference = fromWords.length - toWords.length;
            int differenceTimes = 0;
            int indexDifference = lengthDifference * differenceTimes;
            for (int i = 0; i < wordsArray.length; i++) { // might need to go backwards
                if (wordsArray[i].equals(fromWords[0])) {
                    boolean isMatch = true;
                    for (int j = 0; j < fromWords.length; j++) {
                        if ((i + fromWords.length > wordsArray.length) || !wordsArray[i + j].equals(fromWords[j])) {
                            resultWordsArray[i - indexDifference] = wordsArray[i];
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) {
                        for (int k = 0; k < toWords.length; k++) {
                            resultWordsArray[i - indexDifference + k] = toWords[k];
                            differenceTimes++;
                            i += lengthDifference;
                        }
                    }
                } else {
                    resultWordsArray[i - indexDifference] = wordsArray[i];
                }
            }
            wordsArray = resultWordsArray;
        }
        System.out.println(Arrays.toString(wordsArray));
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
