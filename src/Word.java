public class Word
{
    private String word_target;
    private String word_explain;
    public void print() {
        System.out.println(word_target + ": " + word_explain);
    }
    public void setWord_target(String str) {
        word_target = str;
    }
    public void setWord_explain(String str) {
        word_explain = str;
    }
    public String getWord_target() {
        return word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }
}
