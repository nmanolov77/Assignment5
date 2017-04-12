package assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Nick Manolov
 * C202
 * Dr. Hettiarachichi
 * version 1.0
 * The purpose of this program is to load an array of linked lists with words 
 * from a dictionary. second part is comparing the words read from the other file
 * and comparing them to the words from the dictionary.
 */
public class Assignment5 {

    /**
     * @param args
     * @throws FileNotFoundException
     * This method has two parts. The first part is the array of LinkedLists that
     * store words in an alphabetical order. The second part is the random file
     * that we're spell checking. Only words are compared because of the use of 
     * regular expressions.
     */
    public static void main(String[] args) throws FileNotFoundException {
        int foundWords = 0;
        int notFoundWords = 0;
        long foundComparisons = 0;
        int notFoundComparisons = 0;
        int i = 0;
        int dictionaryWordCount = 0;
        int[]count = new int [1];
        
        
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        for (i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<>();
        }//array of LinkedLists

        File dictionaryFile = new File("random_dictionary.txt");
        Scanner input = new Scanner(dictionaryFile);
        while (input.hasNext()) {
           String tempWord = input.nextLine().toLowerCase();
            dictionary[tempWord.charAt(0)-97].insert(tempWord);
            dictionaryWordCount++;
        }//while
 
        //System.out.println(wordCount);
        
        File oliver = new File("oliver.txt");
        Scanner oliverInput = new Scanner(oliver);
         while (oliverInput.hasNext()) {
                String key = oliverInput.next().toLowerCase();
                key = key.replaceAll("[^a-z]", "");//regular expression that replaces anything other than a-z with null.
                if (key.isEmpty() == false) {
                    if(dictionary[key.charAt(0) - 97].search(key,count)) {
                        foundWords++;
                        foundComparisons += count[0];
                    } else {
                        notFoundWords++;
                        notFoundComparisons += count[0];
                    }
                }
            }
        int avgCompsWordsFound = (int)(foundComparisons / foundWords);
        int avgCompsWordsNotFound = (notFoundComparisons / notFoundWords);
        System.out.println("There were " + foundWords +" found words in this file");
        System.out.println("Comparisons made for words found: " + foundComparisons);
        System.out.println("There were " + notFoundWords +" words in this file, that were not found.");
        System.out.println("Comparisons made for words not found: " + notFoundComparisons);
        System.out.println("The average of comparisons of words found: " + avgCompsWordsFound);
        System.out.println("The average of comparisons of words not found:  " +avgCompsWordsNotFound);
            
            

    }//main

}//class
