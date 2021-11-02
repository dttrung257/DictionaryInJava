package sample.dictionaryjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String account;

    private String path;

    @FXML
    private Pane login;

    @FXML
    private TextField searchBar;

    @FXML
    private Pane MainPane;

    @FXML
    private Pane LoginPane;

    @FXML
    private Pane SignUpPane;

    @FXML
    private Pane SearchPane;

    @FXML
    private Pane SearchAcc;

    @FXML
    private Pane YourWords;

    @FXML
    private Pane WordExplain;

    @FXML
    private Pane AddWord;

    @FXML
    private Pane paraTranslate;

    @FXML
    private Pane rightPane;

    @FXML
    private TextField searchBarBasic;

    @FXML
    private TextField AccountLogin;

    @FXML
    private TextField PasswordLogin;

    @FXML
    private TextField AccountSignup;

    @FXML
    private TextField PasswordSignup;

    @FXML
    private TextField PasswordRetype;

    @FXML
    private TextField WordType;

    @FXML
    private TextField ProType;

    @FXML
    private TextArea word_explainAd;

    @FXML
    private TextArea ExplainType;

    @FXML
    private TextArea EngText;

    @FXML
    private TextArea ViText;

    @FXML
    private Button edit;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> yourWords;

    @FXML
    private Label Word_target;

    @FXML
    private Label Word_explain_basic;

    @FXML
    private Label Word_pronunciation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*DictionaryManagement.insertFromFile("src\\dictionaries.txt");
        DictionaryAdvancedManagement.insertAdvancedFile("src\\dictionariesFull.txt");*/
        DictionaryAdvancedManagement.insertDatabase();
        DictionaryAdvancedManagement.insertDatabaseBasic();
        DictionaryAdvancedManagement.loadAccount();
    }

    public void ReInitialize() {
        Dictionary.wordsAdvanced.clear();
        Dictionary.accounts.clear();
        //DictionaryAdvancedManagement.insertAdvancedFile("src\\dictionariesFull.txt");
        DictionaryAdvancedManagement.insertDatabase();
        DictionaryAdvancedManagement.loadAccount();
    }

    @FXML
    void SwitchLoginPane(MouseEvent event) {
        MainPane.setVisible(false);
        SearchPane.setVisible(false);
        SignUpPane.setVisible(false);
        LoginPane.setVisible(true);
    }

    @FXML
    void SwitchSignupPane(MouseEvent event) {
        MainPane.setVisible(false);
        SearchPane.setVisible(false);
        SignUpPane.setVisible(true);
        LoginPane.setVisible(false);
    }

    @FXML
    void SwitchSearchPane(MouseEvent event) {
        MainPane.setVisible(false);
        SearchPane.setVisible(true);
        SignUpPane.setVisible(false);
        LoginPane.setVisible(false);
    }

    @FXML
    void LogInAccount(MouseEvent event) {
        account = AccountLogin.getText();
        String password = PasswordLogin.getText();
        if (password.length() < 6 || !DictionaryAdvancedManagement.checkAccount(account, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid account or password.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Login success.");
            alert.show();
            PasswordLogin.setText("");
            login.setVisible(false);
            YourWords.setVisible(false);
            SearchAcc.setVisible(true);
            path = "src\\accountData\\" + account + ".txt";
            ArrayList<String> list = DictionaryAdvancedManagement.insertAccountFile(path);
            yourWords.getItems().clear();
            yourWords.getItems().addAll(list);
        }
    }

    @FXML
    void SignUpAccount(MouseEvent event) throws IOException {
        account = AccountSignup.getText();
        String password = PasswordSignup.getText();
        String passwordRetype = PasswordRetype.getText();
        if (account.equals("account")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid user name.");
            alert.show();
        } else if (DictionaryAdvancedManagement.checkAccount(account, "")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Account name exists.");
            alert.show();
        } else if (account.contains(" ") || password.length() < 6 || password.length() > 20) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid account or password\n"
                                 + "Account : no spaces\n"
                                 + "Password : 6 - 20 characters");
            alert.show();
        } else if (!password.equals(passwordRetype)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Wrong password.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Congratulations, your account has been successfully created.");
            alert.show();
            path = "src\\accountData\\" + account + ".txt";
            File file = new File(path);
            file.createNewFile();
            AccountSignup.setText("");
            PasswordSignup.setText("");
            PasswordRetype.setText("");
            DictionaryAdvancedManagement.writeAccToFile(account, password);
            login.setVisible(false);
            SearchAcc.setVisible(true);
        }
    }

    @FXML
    void SearchBasic(MouseEvent event) {
        String selectText = searchBarBasic.getText();
        Word_explain_basic.setText(DictionaryAdvancedManagement.dictionarySearchExplain(selectText));
    }

    @FXML
    void Search(KeyEvent event) {
        ArrayList<String> list = DictionaryAdvancedManagement.dictionarySearchAdvanced(searchBar.getText());
        listView.getItems().clear();
        listView.getItems().addAll(list);
        listView.setVisible(true);
    }

    @FXML
    void DisplayExplain(MouseEvent event) {
        String selectedText = listView.getSelectionModel().getSelectedItem();
        if (selectedText == null) return;
        ArrayList<String> result = DictionaryAdvancedManagement.AdvancedExplain(selectedText);
        word_explainAd.setText(result.get(0));
        Word_pronunciation.setText(result.get(1));
        Word_target.setText(selectedText);
        AddWord.setVisible(false);
        YourWords.setVisible(false);
        WordExplain.setVisible(true);
    }

    @FXML
    void EditWordExplain(MouseEvent event) {
        if (DictionaryAdvancedManagement.checkDuplicateWord(account, Word_target.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You cannot edit word in collection.\n" +
                    "You can remove '" + Word_target.getText()
                    + "' from collection and edit this word");
            alert.show();
        } else {
            word_explainAd.setEditable(!word_explainAd.isEditable());
            if (edit.getText().equals("Edit")) {
                edit.setText("Save");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("This word has been edited, you can add it to collection to save.");
                alert.show();
                String explain = word_explainAd.getText();
                String[] tokens = explain.split("\n");
                String temp = "";
                for (int i = 0; i < tokens.length; i++) {
                    if (!tokens[i].equals("")) {
                        temp += tokens[i] + "\n";
                    }
                }
                explain = temp;
                DictionaryAdvancedManagement.editExplain(account, Word_target.getText(), explain);
                edit.setText("Edit");
                word_explainAd.setText(explain);
            }
        }
    }

    @FXML
    void LogOut(MouseEvent event) {
        ReInitialize();
        searchBar.setText("");
        word_explainAd.setText("");
        Word_target.setText("");
        Word_pronunciation.setText("");
        login.setVisible(true);
        SearchAcc.setVisible(false);
        LoginPane.setVisible(true);
        SignUpPane.setVisible(false);
    }

    @FXML
    void YourWordsColl(MouseEvent event) {
        if (YourWords.isVisible()) {
            YourWords.setVisible(false);
            WordExplain.setVisible(true);
        } else {
            WordExplain.setVisible(false);
            AddWord.setVisible(false);
            YourWords.setVisible(true);
        }
    }

    @FXML
    void YourWordsSee(MouseEvent event) {
        String selectedText = yourWords.getSelectionModel().getSelectedItem();
        if (selectedText == null) {
            return;
        }
        ArrayList<String> result = DictionaryAdvancedManagement.AdvancedExplain(selectedText);
        word_explainAd.setText(result.get(0));
        Word_pronunciation.setText(result.get(1));
        Word_target.setText(selectedText);
        AddWord.setVisible(false);
        YourWords.setVisible(false);
        WordExplain.setVisible(true);
    }

    @FXML
    void DisplayAddWord(MouseEvent event) {
        if (AddWord.isVisible()) {
            AddWord.setVisible(false);
            WordExplain.setVisible(true);
        } else {
            WordExplain.setVisible(false);
            YourWords.setVisible(false);
            AddWord.setVisible(true);
        }
    }

    @FXML
    void AddNewWord(MouseEvent event) {
        String newWord = WordType.getText();
        String pronunciation = ProType.getText();
        String explain = ExplainType.getText();
        if (newWord.equals("") || pronunciation.equals("") || explain.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please don't empty any blank.");
            alert.show();
        } else if (DictionaryAdvancedManagement.checkDuplicateAddNewWord(account, newWord)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("'" + newWord + "' exists");
            alert.show();
        } else {
            DictionaryAdvancedManagement.AddNewWord(newWord, ProType.getText(), ExplainType.getText());
            DictionaryAdvancedManagement.writeWordToFile(account, newWord, "/" + ProType.getText() + "/", ExplainType.getText());
            yourWords.getItems().add(WordType.getText());
            WordType.setText("");
            ProType.setText("");
            ExplainType.setText("");
            AddWord.setVisible(false);
            WordExplain.setVisible(true);
        }
    }

    @FXML
    void AddToCollection(MouseEvent event) {
        if (Word_target.getText().equals("")) {
            return;
        }
        if (!DictionaryAdvancedManagement.checkDuplicateWord(account, Word_target.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word has been added to collection.");
            alert.show();
            yourWords.getItems().add(Word_target.getText());
            DictionaryAdvancedManagement.writeWordToFile(account, Word_target.getText(), Word_pronunciation.getText(), word_explainAd.getText());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word already existed in collection.");
            alert.show();
        }
    }

    @FXML
    void removeFromCollection(MouseEvent event) {
        String selectWord = yourWords.getSelectionModel().getSelectedItem();
        yourWords.getItems().remove(selectWord);
        path = "src\\accountData\\" + account + ".txt";
        DictionaryAdvancedManagement.removeFromAccountFile(path, selectWord);
        word_explainAd.setText("");
        Word_target.setText("");
        Word_pronunciation.setText("");
    }

    @FXML
    void Sound(MouseEvent event) {
        DictionaryAdvancedManagement.speakWord(Word_target.getText());
    }

    @FXML
    void paragraphTranslate(MouseEvent event) {
        paraTranslate.setVisible(!paraTranslate.isVisible());
        rightPane.setVisible(!rightPane.isVisible());
    }

    @FXML
    void ClearPara(MouseEvent event) {
        EngText.setText("");
        ViText.setText("");
    }
    @FXML
    void TranslatePara(MouseEvent event) {
        String text = EngText.getText();
        String viText = DictionaryAdvancedManagement.translateAPI(text);
        ViText.setText(viText);
    }

    @FXML
    void exit() {
        System.exit(0);
    }
}