package com.codecool.thehistory;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//import java.util.ListIterator;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        String[] stringList = text.split("\\s+");
        for (String word : stringList) {
            wordsLinkedList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        List<String > wordToBeRemovedAL = new ArrayList<>();
        wordToBeRemovedAL.add(wordToBeRemoved);
        wordsLinkedList.removeAll(wordToBeRemovedAL);
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (ListIterator<String> myIterator = wordsLinkedList.listIterator(); myIterator.hasNext();) {
            String wordFromFile = myIterator.next();
            if (wordFromFile.equals(from)) {
                myIterator.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        List<String> resultWordsList = new LinkedList<>();
        for (ListIterator<String> i = wordsLinkedList.listIterator(); i.hasNext();) {
            String currentItem = i.next();

            // Checking current element and preventing index out of bounds exception:
            int largestListIndex = wordsLinkedList.size() - 1;
            int lastItemsIndex = i.previousIndex() + fromWords.length - 1;
            boolean mightBeTrue = currentItem.equals(fromWords[0]) && lastItemsIndex <= largestListIndex;
            if (mightBeTrue) {
                // Checking matches of subsequent elements:
                boolean nextMatches = true;
                for (int j = 1; j < fromWords.length; j++) {
                    String fromWord = i.next();
                    if (!(fromWord.equals(fromWords[j]))) {
                        nextMatches = false;
                        for (int k = 0; k < j; k++) {
                            i.previous();
                        }
                        break;
                    }
                }
                // Adding elements to resultArray: if true: all toWords. else: only the current item
                if (nextMatches) {
                    resultWordsList.addAll(Arrays.asList(toWords));
                } else {
                    resultWordsList.add(currentItem);
                }

            } else {
                resultWordsList.add(currentItem);
            }
        }
        wordsLinkedList = resultWordsList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
