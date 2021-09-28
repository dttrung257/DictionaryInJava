import java.util.*;

public class DictionaryManagement
{
    /** Insert word from commandline.*/
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.nextLine();
        for (int i = 0; i < n; i++) {
            Word word = new Word();
            str = sc.nextLine();
            String firstLetterE = str.substring(0, 1);
            String remainingLettersE = str.substring(1, str.length());
            str = firstLetterE.toUpperCase() + remainingLettersE.toLowerCase();
            word.setWord_target(str);
            str = sc.nextLine();
            String firstLetterV = str.substring(0, 1);
            String remainingLettersV = str.substring(1, str.length());
            str = firstLetterV.toUpperCase() + remainingLettersV.toLowerCase();
            word.setWord_explain(str);
            Dictionary.words.add(word);
        }

    }

    /**Insert from file. */
}
