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

    private void moreWordsEqual(String[] fromWords, String[] toWords) {
        List<String> resultWordsList = new LinkedList<>();
        for (ListIterator<String> i = wordsLinkedList.listIterator(); i.hasNext();) {
            String ithItem = i.next();

            // Checking current element ANDDDD possibility of index out of bounds exception:
            int largestListIndex = wordsLinkedList.size() - 1;
            int lastItemsIndex = i.previousIndex() + fromWords.length - 1;
            boolean mightBeTrue = ithItem.equals(fromWords[0]) && lastItemsIndex <= largestListIndex;
            if (mightBeTrue) {
                boolean nextMatches = true;
                List<String> missedItemsList = new LinkedList<>();
//                missedItemsList.clear();
                missedItemsList.add(ithItem);

                // Checking matches of subsequent elements:
                for (int j = 1; j < fromWords.length; j++) {
                    String fromWord = i.next();
                    missedItemsList.add(fromWord);

                    if (!(fromWord.equals(fromWords[j]))) {
                        nextMatches = false;
                        resultWordsList.addAll(missedItemsList);
                        for (int k = 1; k < j; k++) {
                            i.previous();
                        }
                        break;
                    }
                }
                if (nextMatches) {
                    resultWordsList.addAll(Arrays.asList(toWords));
//                    for (int l = 0; l < fromWords.length - 1; l++) {
//                        i.next();
//                    }
                }
            } else {
                resultWordsList.add(ithItem);
            }
        }
        wordsLinkedList = resultWordsList;
    }

    private void moreWordsInsert(String[] fromWords, String[] toWords) {
        ;
    }

    private void moreWordsDelete(String[] fromWords, String[] toWords) {
        ;
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        if (fromWords.length == toWords.length) {
            moreWordsEqual(fromWords, toWords);
        } else if (toWords.length > fromWords.length) {
            moreWordsInsert(fromWords, toWords);
        } else {
            moreWordsDelete(fromWords, toWords);
        }
        if (wordsLinkedList.size() < 30) {
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
