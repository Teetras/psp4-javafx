package com.example.psp4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private ListView<String> selectionListView;

    public void addSelectedItems() {
        ObservableList<String> selectedItems = selectionListView.getSelectionModel().getSelectedItems();

        String joinedText = String.join(" ", selectedItems);

        if (joinedText.length() > 100) {
            // Если выше 100, выводим сообщение
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание");
            alert.setHeaderText(null);
            alert.setContentText("Суммарное количество символов превышает 100.");
            alert.showAndWait();
        } else {
            // Иначе добавляем текст в outputListView
            outputListView.getItems().add(joinedText);
        }
    }
    @FXML
    void initialize(){
        MultipleSelectionModel<String> selectionModel = selectionListView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> items = FXCollections.observableArrayList(
                "Элемент 1", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "dddddddddddddddddddddddddddddddddddddddd",
                "Элемент 4", "Элемент 5", "Элемент 6", "Элемент 7", "Элемент 8", "Элемент 9");


        // Установка списка элементов в SelectionListView
        selectionListView.setItems(items);
    }

}
