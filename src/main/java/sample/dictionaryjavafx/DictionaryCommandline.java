package sample.dictionaryjavafx;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.*;

/**
 * Commandline.
 */
public class DictionaryCommandline {
    /** Print all words in file. */
    public static void showAllWords() {
        System.out.printf("%-10s%-22s%s\n", "No", "English", "Vietnamese");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            String wordTarget = Dictionary.words.get(i).getWord_target();
            String wordExplain = Dictionary.words.get(i).getWord_explain();
            System.out.printf("%-10s%-22s%s\n", i + 1, wordTarget, wordExplain);
        }
    }

    /** Basic function. */
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    /** Advanced function. */
    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile("src\\dictionaries.txt");
        System.out.println("\nSelect option: ");
        System.out.println("1. Show all words");
        System.out.println("2. Search word");
        System.out.println("3. Edit data in library");
        System.out.println("4. Exit");
        do {
            System.out.println("Please select option: ");
            Scanner scanner = new Scanner(System.in);
            String select = scanner.next();
            if (select.equals("1")) {
                showAllWords();
            } else if (select.equals("2")) {
                DictionaryManagement.dictionaryLookup();
            } else if (select.equals("3")) {
                DictionaryManagement.editFile("guest");
                System.out.println("\nSelect option: ");
                System.out.println("1. Show all words");
                System.out.println("2. Search word");
                System.out.println("3. Edit data in library");
                System.out.println("4. Exit");
            } else if (select.equals("4")) {
                System.out.println("Good bye! See you again.");
                break;
            }
        } while (true);
    }


    /** Main. */
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        /*DictionaryAdvancedManagement.insertAdvancedFile("src\\dictionariesFull.txt");
        System.out.printf("%-10s%-22s%-22s%s\n", "No", "English", "Pronunciation", "Vietnamese");
        System.out.println("--------------------------------------------------------------------");
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            String wordTarget = Dictionary.wordsAdvanced.get(i).getWord_target();
            String wordExplain = Dictionary.wordsAdvanced.get(i).getWord_explain();
            String wordPronun = Dictionary.wordsAdvanced.get(i).getWord_pronun();
            System.out.printf("%-10s%-22s%-22s%s\n", i + 1, wordTarget, wordPronun, wordExplain);
        }*/
        dictionaryAdvanced();
    }
}

