package sample.dictionaryjavafx;

import java.io.*;
import java.util.ArrayList;

public class DictionaryAdvancedManagement {
    /**
     * Search exact word.
     *
     * @param str english word
     * @return vietnamese word
     */
    public static String dictionarySearchExplain(String str) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWord_target().equals(str)) {
                return Dictionary.words.get(i).getWord_explain();
            }
        }
        return "No exact match found for " + str;
    }
    public static void insertAdvancedFile(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
                while (line != null) {
                        if (line.charAt(0) == '@') {
                            line = line.substring(1, line.length());
                            Word w = new Word();
                            if (line.contains("/")) {
                                String[] tokens = line.split("/");
                                w.setWord_target(tokens[0]);
                                w.setWord_pronun("/" + tokens[1] + "/");
                            } else {
                                w.setWord_target(line);
                                w.setWord_pronun("");
                            }
                            String temp = "";
                            while ((line = bufferedReader.readLine())  != null && line.charAt(0) != '@') {
                                temp = temp + line + "\n";
                            }
                            w.setWord_explain(temp);
                            Dictionary.wordsAdvanced.add(w);
                        }
                    }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void loadAccount() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\accountData\\account.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                Account w = new Account();
                w.setAccount(tokens[0]);
                w.setPassword(tokens[1]);
                Dictionary.accounts.add(w);
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeAccToFile(String account, String password) {
        try {
            String str = account + " " + password + "\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\accountData\\account.txt", true));
            writer.append(str);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean checkAccount(String account, String password) {
        for (int i = 0; i < Dictionary.accounts.size(); i++) {
            if (Dictionary.accounts.get(i).getAccount().equals(account)) {
                if (password.equals("")) {
                    return true;
                }
                if (Dictionary.accounts.get(i).getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public static ArrayList<String> dictionarySearch(String inputWord) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Dictionary.words.size(); i++) {
            String strTarget = Dictionary.words.get(i).getWord_target();
            if (strTarget.length() >= inputWord.length()) {
                if (inputWord.equals(strTarget.substring(0, inputWord.length()))) {
                    String strExplain = Dictionary.words.get(i).getWord_explain();
                    result.add(strTarget);
                }
            }
        }
        return result;
    }
    public static ArrayList<String> dictionarySearchAdvanced(String inputWord) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            String strTarget = Dictionary.wordsAdvanced.get(i).getWord_target();
            if (strTarget.length() >= inputWord.length()) {
                if (inputWord.equals(strTarget.substring(0, inputWord.length()))) {
                    result.add(strTarget);
                }
            }
        }
        return result;
    }
    public static ArrayList<String> AdvancedExplain(String str, String edit) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            String strTarget = Dictionary.wordsAdvanced.get(i).getWord_target();
            if (str.equals(strTarget)) {
                if (edit != null) {
                    result.add(edit);
                } else result.add(Dictionary.wordsAdvanced.get(i).getWord_explain());
                result.add(Dictionary.wordsAdvanced.get(i).getWord_pronun());
                return result;
            }
        }
        result.add("Not found");
        result.add("Not found");
        return result;
    }
    public static void writeWordToFile(String account, String word, String pronun, String explain) {
        try {
            String str = "@" + word + "/" + pronun + "/\n" + explain + "\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\accountData\\" + account + ".txt", true));
            writer.append(str);
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<String> insertAccountFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.charAt(0) == '@') {
                    boolean check = false;
                    int temp = 0;
                    line = line.substring(1, line.length() - 1);
                    Word w = new Word();
                    if (line.contains("/")) {
                        String[] tokens = line.split("/");
                        w.setWord_target(tokens[0]);
                        w.setWord_pronun("/" + tokens[1] + "/");
                    } else {
                        w.setWord_target(line);
                        w.setWord_pronun("");
                    }
                    result.add(w.getWord_target());
                    String tmp = "";
                    while ((line = bufferedReader.readLine())  != null && line.charAt(0) != '@') {
                        tmp = tmp + line + "\n";
                    }
                    w.setWord_explain(tmp);
                    for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
                        if(Dictionary.wordsAdvanced.get(i).getWord_target().charAt(0) < w.getWord_target().charAt(0)) {
                            i++;
                        } else if (Dictionary.wordsAdvanced.get(i).getWord_target().charAt(0) > w.getWord_target().charAt(0)) {
                            break;
                        }
                        if (Dictionary.wordsAdvanced.get(i).getWord_target().equals(w.getWord_target())) {
                            check = true;
                            temp = i;
                            break;
                        }
                    }
                    if (check) {
                        Dictionary.wordsAdvanced.get(temp).setWord_pronun(w.getWord_pronun());
                        Dictionary.wordsAdvanced.get(temp).setWord_explain(w.getWord_explain());
                    } else {
                        Dictionary.wordsAdvanced.add(w);
                    }
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static void EditExplain(String word, String explain) {
        for (int i = 0; i < Dictionary.wordsAdvanced.size(); i++) {
            if(Dictionary.wordsAdvanced.get(i).getWord_target().equals(word)) {
                Dictionary.wordsAdvanced.get(i).setWord_explain(explain);
                break;
            }
        }
    }
    public static void AddNewWord(String word, String pronun, String explain) {
        Word w = new Word();
        w.setWord_explain(explain);
        w.setWord_target(word);
        w.setWord_pronun(pronun);
        Dictionary.wordsAdvanced.add(w);
    }
}
