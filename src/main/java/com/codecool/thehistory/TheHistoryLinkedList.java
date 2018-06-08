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
        String[] stringList = text.split("\\s");
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
        int randomNumber = (sequence.length > 2) ? ThreadLocalRandom.current().nextInt(1, sequence.length - 1) : 0;
        // TODO: put the whole thing in the main function
        List<String> wSubList = wordsLinkedList.subList(index, lastIndexToCheck + 1);
        ListIterator<String> wSLast = wSubList.listIterator(sequence.length - 1);
        boolean lastElementEquals = wSLast.next().equals(sequence[sequence.length - 1]);
        ListIterator<String> wSLRandom = wSubList.listIterator(randomNumber);
        boolean randomElementEquals = wSLRandom.next().equals(sequence[randomNumber]);
        return lastElementEquals && randomElementEquals;
    }


    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
        if (fromWords.length == toWords.length) {
            int forIndex = 0;
            for (String textWord : wordsLinkedList) {
                boolean isMatch = lListSequenceExists(fromWords, forIndex, textWord);
                if (isMatch) {
                    List<String> subList = wordsLinkedList.subList(forIndex, (forIndex + toWords.length));
                    for (ListIterator<String> subIter = subList.listIterator(); subIter.hasNext();) {
                        subIter.next();
                        subIter.set(toWords[subIter.previousIndex()]);

                    }
                }
                forIndex++;
            }
        } else if (fromWords.length < toWords.length) {
            int forIndex2 = 0;
            for (String textWord : wordsLinkedList) {
                boolean isMatch = lListSequenceExists(fromWords, forIndex2, textWord);
                if (isMatch) {
                    List<String> toArrayList = new ArrayList<String>(Arrays.asList(toWords));
                    List<String> subList = wordsLinkedList.subList(forIndex2, (forIndex2 + fromWords.length));
                    for (ListIterator<String> subIter = subList.listIterator(); subIter.hasNext();) {
                        subIter.next();
                        subIter.set(toWords[subIter.previousIndex()]);
                        toArrayList.remove(0);
                    }
                    wordsLinkedList.addAll((forIndex2 + fromWords.length - 1), toArrayList);
//                    forIndex2++;
                }
                forIndex2++;
            }

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
