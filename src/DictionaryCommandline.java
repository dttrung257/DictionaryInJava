public class DictionaryCommandline {

    public static void showAllWords() {
        System.out.printf("%-4s%-15s%s\n", "No", "Tieng Anh", "Tieng Viet");
        System.out.println("-------------------------------------");
        for (int i = 0; i < Dictionary.sz; i++) {
            System.out.printf("%-4s%-15s%s\n", i+1, Dictionary.words[i].getWord_target(), Dictionary.words[i].getWord_explain());
        }
    }
    public static void dictionaryBasic()
    {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String args[]) {
        Dictionary dict = new Dictionary();
        dictionaryBasic();
    }
}
