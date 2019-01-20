package com.codecool.thehistory;

import java.util.*;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        String[] stringArray = text.split("\\s+");
        for (String word : stringArray) {
            wordsArrayList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        ArrayList<String> word2BRemovedAL = new ArrayList<>();
        word2BRemovedAL.add(wordToBeRemoved);
        wordsArrayList.removeAll(word2BRemovedAL);
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {

        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (wordsArrayList.get(i).equals(from)) {
                wordsArrayList.set(i, to);
            }
        }
    }

    private boolean aListSequenceExists(String[] sequence, int index) {
        /**
         * Searches for an array of strings in another array
         * @param baseArray: an array to be examinded;
         * @param sequence: an Array of strings to be searched for in baseArray
         * @return: true if all 3 elements of sampleArray match the sequence in the baseTextArray
         * */
        int lastIndexToCheck = index + sequence.length - 1;
        if(lastIndexToCheck >= wordsArrayList.size()) {
            return false;
        }
        int randomNumber = (sequence.length > 2) ? ThreadLocalRandom.current().nextInt(1, sequence.length - 1) : 0;
        boolean lastElementEquals = wordsArrayList.get(lastIndexToCheck).equals(sequence[sequence.length - 1]);
        boolean randomElementEquals = wordsArrayList.get(index + randomNumber).equals(sequence[randomNumber]);
        return lastElementEquals && randomElementEquals;
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        List<String> toWordsList = new ArrayList<>();
        Collections.addAll(toWordsList, toWords);
        for (int i = 0; i < wordsArrayList.size(); i++) {
            boolean isMatch = false;
            if (wordsArrayList.get(i).equals(fromWords[0])) {
                isMatch = aListSequenceExists(fromWords, i);
            }
            if (isMatch) {
                for (int j = 0; j < fromWords.length; j++) {
                    wordsArrayList.remove(i);
                }
                wordsArrayList.addAll(i, toWordsList);
                i += toWords.length - 1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
