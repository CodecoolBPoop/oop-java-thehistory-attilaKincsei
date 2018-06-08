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

    private boolean lListSequenceExists(String[] sequence, int index, String firstElement) {
        /**
         * Searches for an array of strings in another array
         * @param baseArray: an array to be examinded;
         * @param sequence: an Array of strings to be searched for in baseArray
         * @return: true if all 3 elements of sampleArray match the sequence in the baseTextArray
         * */
        int lastIndexToCheck = index + sequence.length - 1;
        boolean firstElementEquals = firstElement.equals(sequence[0]);
        if(lastIndexToCheck >= wordsLinkedList.size() || !firstElementEquals) {
            return false;
        }
        List<String> wSubList = wordsLinkedList.subList(index, lastIndexToCheck + 1);
        boolean lastElementEquals = wSubList.get(sequence.length - 1).equals(sequence[sequence.length - 1]);
//        int randomNumber = (sequence.length > 2) ? ThreadLocalRandom.current().nextInt(1, sequence.length - 1) : 0;
//        ListIterator<String> wSLRandom = wSubList.listIterator(randomNumber);
//        boolean randomElementEquals = wSLRandom.next().equals(sequence[randomNumber]);
        return lastElementEquals;
    }


    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        List<Integer> insertionIndexList = new LinkedList<Integer>();
        if (fromWords.length <= toWords.length) {
            insertionIndexList.clear();
            for (ListIterator<String> wordIterator = wordsLinkedList.listIterator(); wordIterator.hasNext();) {
                String eachElement = wordIterator.next();
                int currentIndex = wordIterator.previousIndex();
                boolean isMatch = lListSequenceExists(fromWords, currentIndex, eachElement);
                if (isMatch) {
                    List<String> subList = wordsLinkedList.subList(currentIndex, (currentIndex + fromWords.length));
                    for (ListIterator<String> subIter = subList.listIterator(); subIter.hasNext();) {
                        subIter.next();
                        subIter.set(toWords[subIter.previousIndex()]);
                    }
                    insertionIndexList.add(wordIterator.nextIndex());
                }
            }
        }
        if (fromWords.length < toWords.length) {
            List<String> toWordsToInsert = new ArrayList<String>();
            for (int i = fromWords.length; i < toWords.length; i++) {
                toWordsToInsert.add(toWords[i]);
            }
            for (int j = 0; j < insertionIndexList.size(); j++) {
                int indexToInsert = insertionIndexList.get(j);
                wordsLinkedList.addAll(indexToInsert, toWordsToInsert);
                int k = j;
                while (k + 1 < insertionIndexList.size()) {
                    k++;
                    int element = insertionIndexList.get(k);
                    element += toWordsToInsert.size();
                    insertionIndexList.set(k, element);

                }
            }
        } else {
            insertionIndexList.clear();
        }
        if (wordsLinkedList.size() < 20) {
            System.out.println(wordsLinkedList);
        }
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
