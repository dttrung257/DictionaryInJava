import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryManagement
{
    public static void insertFromCommandline()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.nextLine();
        for (int i = 0; i < n; i++)
        {
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
    public static void  insertFromFile() {
        try {
            File MyDictionaries = new File("src\\dictionaries.txt");
            Scanner scanFile = new Scanner(MyDictionaries);
            String str = new String();
            while (scanFile.hasNext()) {
                Word wordFromFile = new Word();
                str = scanFile.next();
                String firstLetterE = str.substring(0, 1);
                String remainingLettersE = str.substring(1, str.length());
                str = firstLetterE.toUpperCase() + remainingLettersE.toLowerCase();
                wordFromFile.setWord_target(str);
                str = scanFile.nextLine();
                String firstLetterV = str.substring(0, 1);
                String remainingLettersV = str.substring(1, str.length());
                str = firstLetterV.toUpperCase() + remainingLettersV.toLowerCase();
                wordFromFile.setWord_explain(str);
                Dictionary.words.add(wordFromFile);
            }
            scanFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot open file!");
        }
    }
    public static void dictionaryLookup() {
        System.out.print("Insert word to lookup: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String firstLetterE = str.substring(0, 1);
        String remainingLettersE = str.substring(1, str.length());
        str = firstLetterE.toUpperCase() + remainingLettersE.toLowerCase();
        int count = 0;
        System.out.printf("%-7s%-22s%s\n", "No", "English", "Vietnamese");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            String strTarget = Dictionary.words.get(i).getWord_target();
            if (strTarget.contains(str)) {
                System.out.printf("%-7s%-22s%s\n", ++count, strTarget, Dictionary.words.get(i).getWord_explain());
            }
        }
        if ( count == 0) {
            System.out.println("No exact match found for " + str + " in dictionary!");
        }
    }
}
