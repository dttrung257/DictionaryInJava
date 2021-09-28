public class DictionaryCommandline {

    /** Show all words. */
    public static void showAllWords() {
        System.out.printf("%-4s%-15s%s\n", "No", "Tieng Anh", "Tieng Viet");
        System.out.println("-------------------------------------");
        for (int i = 0; i < Dictionary.words.size(); i++) {
            System.out.printf("%-4s%-15s%s\n", i+1, Dictionary.words.get(i).getWord_target(), Dictionary.words.get(i).getWord_explain());
        }
    }

    /** Basic function. */
    public static void dictionaryBasic()
    {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    /** DictionaryAdvanced. */
    
    /** Main. */
    public static void main(String args[]) {
        Dictionary dict = new Dictionary();
        dictionaryBasic();
    }
}
