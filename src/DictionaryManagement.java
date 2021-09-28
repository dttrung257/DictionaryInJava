import java.util.*;

public class DictionaryManagement
{
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.nextLine();
        for (int i = 0; i < n; i++) {
            Word w = new Word();
            str = sc.nextLine();
            w.setWord_target(str);
            str = sc.nextLine();
            w.setWord_explain(str);
            Dictionary.words[Dictionary.sz++] = w;
        }

    }
}
