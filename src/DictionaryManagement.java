import java.io.*;
import java.util.*;

/**
 * Functions manage dictionary.
 */
public class DictionaryManagement {
    /** Insert words directly from commandline. */
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.nextLine();
        for (int i = 0; i < n; i++) {
            Word w = new Word();
            System.out.println("Add English words: ");
            str = sc.nextLine();
            String firstLetterE = str.substring(0, 1);
            String remainingLettersE = str.substring(1, str.length());
            str = firstLetterE.toUpperCase() + remainingLettersE.toLowerCase();
            w.setWord_target(str);

            System.out.println("Add words explain: ");
            str = sc.nextLine();
            String firstLetterV = str.substring(0, 1);
            String remainingLettersV = str.substring(1, str.length());
            str = firstLetterV.toUpperCase() + remainingLettersV.toLowerCase();
            w.setWord_explain(str);
            Dictionary.words.add(w);
        }
    }

    /** Get data from file. */
    public static void insertFromFile(final String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\t");
                Word w = new Word();
                w.setWord_target(tokens[0]);
                w.setWord_explain(tokens[1]);
                Dictionary.words.add(w);
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** Write data from dictionary list to file. */
    public static void dictionaryExportToFile(final String path, ArrayList<Word> newWords) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word w : newWords) {
                String line = w.getWord_target() + "\t" + w.getWord_explain();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** Add a new word to dictionary list in order a-z. */
    public static void addWordToFile(final String wordInput) {
        boolean isExisted = false;
        for (Word word : Dictionary.words) {
            if (word.getWord_target().equals(wordInput)) {
                isExisted = true;
            }
        }
        if (isExisted) {
            System.out.println("This word is existed!");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Add word explain: ");
            String newWordV = scanner.nextLine();
            Word newWord = new Word();
            newWord.setWord_target(wordInput);
            newWord.setWord_explain(newWordV);

            int positionInsert = -1, size = Dictionary.words.size(); // size of Dictionary
            for (int i = 0; i < size - 1; i++) {
                String wTarget1 = Dictionary.words.get(i).getWord_target();
                String wTarget2 = Dictionary.words.get(i + 1).getWord_target();
                if (wordInput.compareTo(wTarget1) > 0 && wordInput.compareTo(wTarget2) < 0) {
                    positionInsert = i + 1;
                    break;
                }
            }
            if (wordInput.compareTo(Dictionary.words.get(0).getWord_target()) < 0) {
                positionInsert = 0;
            }
            if (wordInput.compareTo(Dictionary.words.get(size - 1).getWord_target()) > 0) {
                Dictionary.words.add(newWord);
            } else {
                Dictionary.words.add(positionInsert, newWord);
            }
            System.out.println("This word is added to library.");
        }
    }

    /** Delete a word from dictionary list. */
    public static void deleteWordFromFile(final String wordInput) {
        boolean isExisted = false;
        int position = 0;
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWord_target().equals(wordInput)) {
                isExisted = true;
                position = i;
            }
        }
        if (isExisted) {
            Dictionary.words.remove(position);
            System.out.println("The word " + wordInput + " is deleted.");
        } else {
            System.out.println("The word " + wordInput + " is not existed!");
        }
    }

    /** Change data in file. */
    public static void editFile(final String authority) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect option: ");
        System.out.println("1. Delete a word");
        System.out.println("2. Add a new word");
        System.out.println("3. Exit");
        do {
            System.out.print("Please select option: ");
            String option = scanner.next();
            if (option.equals("1")) {
                System.out.println("Delete word: ");
                String deleteWord = scanner.next();

                deleteWordFromFile(deleteWord);
                if (authority.equals("admin")) {
                    dictionaryExportToFile("src\\dictionaries.txt", Dictionary.words);
                }
            } else if (option.equals("2")) {
                System.out.println("Add English word: ");
                String newWordE = scanner.next();

                addWordToFile(newWordE);
                if (authority.equals("admin")) {
                    dictionaryExportToFile("src\\dictionaries.txt", Dictionary.words);
                }
            } else if (option.equals("3")) {
                System.out.println("End the process.");
                break;
            }
        } while (true);
    }

    /** Search word in file. */
    public static void dictionaryLookup() {
        System.out.print("Insert word to lookup: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int count = 0;
        System.out.printf("%-13s%-27s%s\n", "No", "English", "Vietnamese");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            String strTarget = Dictionary.words.get(i).getWord_target();
            if (strTarget.length() >= str.length()) {
                if (str.equals(strTarget.substring(0, str.length()))) {
                    String strExplain = Dictionary.words.get(i).getWord_explain();
                    System.out.printf("%-13s%-27s%s\n", ++count, strTarget, strExplain);
                }
            }
        }
        if (count == 0) {
            System.out.println("No exact match found for " + str + " in dictionary!");
        }
    }
}

