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
        List<String> resultWordsList = new LinkedList<>();
        int wordsListIndex = 0;
        while (wordsListIndex < wordsLinkedList.size()){
            String ithElement = wordsLinkedList.get(wordsListIndex);
            if (ithElement.equals(fromWords[0])) {
                boolean isMatch = true;
                for (int j = 0; j < fromWords.length; j++) {
                    if ((wordsListIndex + fromWords.length > wordsLinkedList.size()) || !wordsLinkedList.get(wordsListIndex + j).equals(fromWords[j])) {
                        resultWordsList.add(ithElement);
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    resultWordsList.addAll(Arrays.asList(toWords));
                    wordsListIndex += fromWords.length;
                } else {
                    wordsListIndex++;
                }
            } else {
                resultWordsList.add(ithElement);
                wordsListIndex++;
            }
        }
        wordsLinkedList.clear();
        wordsLinkedList = resultWordsList;
//        if (wordsLinkedList.size() < 30) {
//            System.out.println(wordsLinkedList);
//        }
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
