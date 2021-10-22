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
    @FXML
    private Pane login;
    private String account;
    private String path;
    @FXML
    private TextField searchBar;

    @FXML
    private Pane MainPane, LoginPane, SignUpPane, SearchPane, SearchAcc, YourWords, WordExplain, AddWord;
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
    private TextField WordType, ProType;
    @FXML
    private TextArea word_explainAd, ExplainType;
    @FXML
    private Label IncorrectAccount;
    @FXML
    private Button edit;
    @FXML
    private Label IncorrectAccountSignUp;
    @FXML
    private ListView<String> listView, yourWords;
    @FXML
    private Label Word_target;
    @FXML
    private Label Word_explain_basic;
    @FXML
    private Label Word_pronunciation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile("src\\dictionaries.txt");
        DictionaryAdvancedManagement.insertAdvancedFile("src\\dictionariesFull.txt");
        DictionaryAdvancedManagement.loadAccount();
    }
    public void ReInitialize() {
        Dictionary.wordsAdvanced.clear();
        DictionaryAdvancedManagement.insertAdvancedFile("src\\dictionariesFull.txt");

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
            IncorrectAccount.setText("Invalid account or password.");
        } else {
            IncorrectAccount.setText("");
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
        if(DictionaryAdvancedManagement.checkAccount(account, "")) {
            IncorrectAccountSignUp.setText("Account name exists.");
        } else if (account.contains(" ") || !password.equals(passwordRetype)) {
            IncorrectAccountSignUp.setText("Invalid account or password.");
        } else {
            path = "src\\accountData\\" + account + ".txt";
            File file = new File(path);
            file.createNewFile();
            IncorrectAccountSignUp.setText("");
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
        ArrayList<String> result = DictionaryAdvancedManagement.AdvancedExplain(selectedText, null);
        word_explainAd.setText(result.get(0));
        Word_pronunciation.setText(result.get(1));
        Word_target.setText(selectedText);
        AddWord.setVisible(false);
        YourWords.setVisible(false);
        WordExplain.setVisible(true);
    }
    @FXML
    void EditWordExplain(MouseEvent event) {
        word_explainAd.setEditable(!word_explainAd.isEditable());
        if (edit.getText().equals("Edit")) {
            edit.setText("Save");
        }
        else {
            DictionaryAdvancedManagement.writeWordToFile(account, Word_target.getText(), Word_pronunciation.getText(), word_explainAd.getText());
            DictionaryAdvancedManagement.EditExplain(Word_target.getText(), word_explainAd.getText());
            edit.setText("Edit");
        }
    }
    @FXML
    void Back(MouseEvent event) {
        ReInitialize();
        searchBar.setText("");
        word_explainAd.setText("");
        Word_target.setText("");
        Word_pronunciation.setText("");
        login.setVisible(true);
        SearchAcc.setVisible(false);
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
        if (selectedText == null) return;
        ArrayList<String> result = DictionaryAdvancedManagement.AdvancedExplain(selectedText, null);
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
        DictionaryAdvancedManagement.AddNewWord(WordType.getText(), ProType.getText(), ExplainType.getText());
        DictionaryAdvancedManagement.writeWordToFile(account, WordType.getText(), ProType.getText(), ExplainType.getText());
        yourWords.getItems().add(WordType.getText());
        WordType.setText("");
        ProType.setText("");
        ExplainType.setText("");
        AddWord.setVisible(false);
        WordExplain.setVisible(true);
    }
    @FXML
    void AddToCollection(MouseEvent event) {
        yourWords.getItems().add(Word_target.getText());
        DictionaryAdvancedManagement.writeWordToFile(account, Word_target.getText(), Word_pronunciation.getText(), word_explainAd.getText());
    }
    @FXML
    void exit() {
        System.exit(0);
    }
}