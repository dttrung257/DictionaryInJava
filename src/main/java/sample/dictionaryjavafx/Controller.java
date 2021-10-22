package sample.dictionaryjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Dictionary;

public class Controller {
    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void Search(MouseEvent event) {
        ArrayList<String> list = DictionaryCommandline.dictionarySearch(searchBar.getText());
        listView.getItems().clear();
        listView.getItems().addAll(list);
    }
}