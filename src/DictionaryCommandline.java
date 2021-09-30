import java.util.Scanner;

public class DictionaryCommandline
{
    public static void showAllWords()
    {
        System.out.printf("%-7s%-22s%s\n", "No", "English", "Vietnamese");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < Dictionary.words.size(); i++)
            System.out.printf("%-7s%-22s%s\n", i+1, Dictionary.words.get(i).getWord_target(), Dictionary.words.get(i).getWord_explain());
    }
    public static void dictionaryBasic()
    {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    public static void dictionaryAdvanced()
    {
        DictionaryManagement.insertFromFile();
        boolean quit = false;
        do {
            System.out.print("Press 0 to show all words, 1 to insert word or 2 to quit (¬_¬): ");
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if (select == 0) {
                showAllWords();
            } else if (select == 1) {
                DictionaryManagement.dictionaryLookup();
            } else {
                System.out.println("༼ಢ_ಢ༽ Good bye! See you again ༼ಢ_ಢ༽");
                quit = true;
            }
        } while (!quit);
    }
    public static void main(String[] args)
    {
        Dictionary dictionary = new Dictionary();
        //dictionaryBasic();
        dictionaryAdvanced();
    }
}
